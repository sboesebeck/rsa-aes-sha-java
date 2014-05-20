package de.caluga.ecc;

import de.caluga.rsa.BigInteger;
import de.caluga.rsa.SHA2;

import java.util.ArrayList;
import java.util.List;

public class ECCrypt {
    public final static int BLOCK_SIZE = 64;
    private EllipticCurve ec;

    public ECCrypt(EllipticCurve ec) {
        this.ec = ec;

    }


    public byte[] encrypt(byte[] input, ECKey key) {
        int idx = 0;
        byte[] data = new byte[input.length + 4];
        data[0] = (byte) (input.length >> 24 & 0xff);
        data[1] = (byte) (input.length >> 16 & 0xff);
        data[2] = (byte) (input.length >> 8 & 0xff);
        data[3] = (byte) (input.length & 0xff);

        byte[] block = new byte[BLOCK_SIZE];
        List<Byte> ret = new ArrayList<>();
        while (idx < data.length) {
            int len = BLOCK_SIZE;
            if (idx + len > data.length) {
                len = data.length - idx;
                System.arraycopy(data, idx, block, 0, len);
                for (int i = idx + len; i < data.length; i++) {
                    block[i] = 0;
                }
            }
            byte enc[] = encryptBlock(block, key);
            for (byte b : enc) {
                ret.add(b);
            }
        }

        byte[] result = new byte[ret.size()];
        idx = 0;
        for (Byte b : ret) {
            result[idx++] = b;
        }
        return result;
    }


    public byte[] encryptBlock(byte[] input, ECKey key) {
        if (input.length != BLOCK_SIZE) throw new IllegalArgumentException("block must be of size 64 bytes");
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
