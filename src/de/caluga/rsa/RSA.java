package de.caluga.rsa;

import java.security.SecureRandom;

/**
 * User: Stephan Bösebeck
 * Date: 24.09.13
 * Time: 23:38
 * <p/>
 * TODO: Add documentation here
 */
public class RSA {
    private BigInteger n, d, e;

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
                break;
            } catch (Exception e) {
            }
        }
    }

    public RSA(BigInteger n, BigInteger d, BigInteger e) {
        this.n = n;
        this.d = d;
        this.e = e;
    }

    public BigInteger encrypt(BigInteger message) {
        return message.modPow(e, n);
    }

    public BigInteger decrypt(BigInteger message) {
        return message.modPow(d, n);
    }

    @Override
    public String toString() {
        return "RSA{" +
                "n=" + n +
                ", d=" + d +
                ", e=" + e +
                '}';
    }
}
