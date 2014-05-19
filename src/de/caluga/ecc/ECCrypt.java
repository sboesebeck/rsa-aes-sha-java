package de.caluga.ecc;

import de.caluga.rsa.BigInteger;
import de.caluga.rsa.SHA2;

public class ECCrypt {
    private EllipticCurve ec;

    public ECCrypt(EllipticCurve ec) {
        this.ec = ec;

    }

    public int blockSize() {
        return 20;
    }


    public byte[] encryptBlock(byte[] input, ECKey key) {
        if (input.length != 64) throw new IllegalArgumentException("block must be of size 64 bytes");
        ECKey ek = (ECKey) key;
        byte[] res = new byte[ek.mother.getPCS() + input.length];


        BigInteger rk = new BigInteger(ek.mother.getp().bitLength() + 17, Rand.om);
        if (ek.mother.getOrder() != null) {
            rk = rk.mod(ek.mother.getOrder());
        }
        ECPoint gamma = ek.mother.getGenerator().multiply(rk);
        ECPoint sec = ek.beta.multiply(rk);
        System.arraycopy(gamma.compress(), 0, res, 0, ek.mother.getPCS());
        SHA2 sha2 = new SHA2();
        sha2.engineUpdate(sec.getx().toByteArray(), 0, sec.getx().toByteArray().length);
        sha2.engineUpdate(sec.gety().toByteArray(), 0, sec.gety().toByteArray().length);
        byte[] digest = sha2.engineDigest();
        for (int j = 0; j < input.length; j++) {
            res[j + ek.mother.getPCS()] = (byte) (input[j] ^ digest[j]);
        }
        return res;
    }

    public byte[] decryptBlock(byte[] input, ECKey key) {
        ECKey dk = (ECKey) key;
        byte[] res = new byte[input.length - dk.mother.getPCS()];
        byte[] gammacom = new byte[dk.mother.getPCS()];

        System.arraycopy(input, 0, gammacom, 0, dk.mother.getPCS());
        ECPoint gamma = new ECPoint(gammacom, dk.mother);
        ECPoint sec = gamma.multiply(dk.sk);
        SHA2 sha2 = new SHA2();
        if (sec.isZero()) {
            sha2.engineUpdate(new BigInteger(0).toByteArray(), 0, new BigInteger(0).toByteArray().length);
            sha2.engineUpdate(new BigInteger(0).toByteArray(), 0, new BigInteger(0).toByteArray().length);
        } else {
            sha2.engineUpdate(sec.getx().toByteArray(), 0, sec.getx().toByteArray().length);
            sha2.engineUpdate(sec.gety().toByteArray(), 0, sec.gety().toByteArray().length);

        }
        byte[] digest = sha2.engineDigest();
        for (int j = 0; j < input.length - dk.mother.getPCS(); j++) {
            res[j] = (byte) (input[j + dk.mother.getPCS()] ^ digest[j]);
        }
        return res;
    }


    /**
     * This method generates a new key for the cryptosystem.
     *
     * @return the new key generated
     */
    public ECKey generateKey() {
        return new ECKey(ec);
    }

    public String toString() {
        return "ECC - " + ec.toString();
    }

}
