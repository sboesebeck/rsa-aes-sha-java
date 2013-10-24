package de.caluga.rsa;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;
import java.util.ArrayList;
import java.util.List;

/**
 * User: Stephan Bösebeck
 * Date: 24.09.13
 * Time: 23:38
 * <p/>
 * TODO: Add documentation here
 */
public class RSA {
    private BigInteger n, d, e;
    private int bitLen;


    public RSA(int bitlen) {
        SecureRandom r = new SecureRandom();
        BigInteger p = new BigInteger(bitlen / 2, 100, r);
        BigInteger q = new BigInteger(bitlen / 2, 100, r);
        n = p.multiply(q);
        BigInteger m = (p.subtract(BigInteger.ONE))
                .multiply(q.subtract(BigInteger.ONE));
        while (true) {
            e = new BigInteger(bitlen, new SecureRandom());
            while (m.gcd(e).intValue() > 1) e = e.add(new BigInteger(bitlen, new SecureRandom()));
            try {
                d = e.modInverse(m);
                System.out.println("BitLength d: " + d.bitLength());
                break;
            } catch (Exception e) {
            }
        }
        this.bitLen = bitlen;
    }

    public int getBitLen() {
        return bitLen;
    }

    public void setBitLen(int bitLen) {
        this.bitLen = bitLen;
    }


    public RSA(BigInteger n, BigInteger d, BigInteger e, int bits) {
        this.n = n;
        this.d = d;
        this.e = e;
        this.bitLen = bits;
    }

    /**
     * encrypt using public key
     *
     * @param message
     * @return
     */
    public byte[] encrypt(byte[] message) {
        return encrypt(message, e, n);
    }

    private byte[] encrypt(byte[] message, BigInteger mp, BigInteger mod) {
        List<BigInteger> bi = BigInteger.getIntegersOfBitLength(message, bitLen);
        List<Byte> ret = new ArrayList<Byte>();
        for (BigInteger b : bi) {
            BigInteger enc = crypt(b, mp, mod);
            byte[] encB = enc.bytes();
            for (byte eb : encB) {
                ret.add(eb);
            }
        }

        byte[] bytes = new byte[ret.size()];
        for (int i = 0; i < bytes.length; i++) {
            bytes[i] = ret.get(i);
        }
        return bytes;
    }


    public byte[] decrypt(byte[] message) {
        return decrypt(message, d, n);

    }

    private byte[] decrypt(byte[] message, BigInteger mp, BigInteger mod) {
        List<BigInteger> bi = BigInteger.deSerializeInts(message);
        List<Byte> ret = new ArrayList<Byte>();
        List<BigInteger> decrypted = new ArrayList<BigInteger>();
        for (BigInteger toDec : bi) {
            BigInteger dec = crypt(toDec, mp, mod);
            decrypted.add(dec);
        }
        return BigInteger.dataFromBigIntArray(decrypted, bitLen - 4);
    }


    private BigInteger crypt(BigInteger message, BigInteger mp, BigInteger mod) {
        return message.modPow(mp, mod);
    }

    public BigInteger encrypt(BigInteger message) {
        return crypt(message, e, n);
    }

    public BigInteger decrypt(BigInteger message) {
        return crypt(message, d, n);
    }

    public byte[] getPrivateKey() {
        List<Byte> ret = new ArrayList<Byte>();
        for (byte b : BigInteger.valueOf(getBitLen()).bytes()) {
            ret.add(b);
        }
        for (byte b : n.bytes()) {
            ret.add(b);
        }
//        for (byte b : e.bytes()) {
//            ret.add(b);
//        }
        for (byte b : d.bytes()) {
            ret.add(b);
        }
        byte[] bytes = new byte[ret.size()];
        for (int i = 0; i < bytes.length; i++) {
            bytes[i] = ret.get(i);
        }
        return bytes;

    }

    public byte[] getPublicKey() {
        List<Byte> ret = new ArrayList<Byte>();
        for (byte b : BigInteger.valueOf(getBitLen()).bytes()) {
            ret.add(b);
        }
        for (byte b : n.bytes()) {
            ret.add(b);
        }
        for (byte b : e.bytes()) {
            ret.add(b);
        }
//        for (byte b : d.bytes()) {
//            ret.add(b);
//        }
        byte[] bytes = new byte[ret.size()];
        for (int i = 0; i < bytes.length; i++) {
            bytes[i] = ret.get(i);
        }
        return bytes;

    }

    public boolean isValidSigned(byte[] signature, byte[] message) {
        //TODO: think
        return false;
    }

    public byte[] sign(byte[] message) throws NoSuchAlgorithmException {
        MessageDigest digest = java.security.MessageDigest.getInstance("MD5");
        digest.update(message);
        byte[] hash = digest.digest();

        return encrypt(hash, d, n);
    }

    @Override
    public String toString() {
        return "RSA{" +
                "n=" + n +
                ", d=" + d +
                ", e=" + e +
                '}';
    }

    public static RSA fromBytes(byte[] data) {
        List<BigInteger> lst = BigInteger.deSerializeInts(data);
        if (lst.size() != 4) {
            throw new IllegalArgumentException("byte array mismatch - " + lst.size());
        }
        BigInteger bitLen = lst.get(0);
        BigInteger n = lst.get(1);
        BigInteger e = lst.get(2);
        BigInteger d = lst.get(3);
        RSA ret = new RSA(n, d, e, bitLen.intValue());
        return ret;
    }


    public byte[] bytes() {
        List<Byte> ret = new ArrayList<Byte>();
        for (byte b : BigInteger.valueOf(getBitLen()).bytes()) {
            ret.add(b);
        }
        for (byte b : n.bytes()) {
            ret.add(b);
        }
        for (byte b : e.bytes()) {
            ret.add(b);
        }
        for (byte b : d.bytes()) {
            ret.add(b);
        }
        byte[] bytes = new byte[ret.size()];
        for (int i = 0; i < bytes.length; i++) {
            bytes[i] = ret.get(i);
        }
        return bytes;
    }


}
