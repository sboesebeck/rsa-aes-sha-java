package de.caluga.rsa;

import java.util.Random;

/**
 * User: Stephan Bösebeck
 * Date: 24.09.13
 * Time: 23:38
 * <p/>
 * TODO: Add documentation here
 */
public class RSA {
    private BigInteger n, d, e;

    public RSA(int bitlen)
    {
        Random r = new Random();
        BigInteger p = new BigInteger(bitlen / 2, 100, r);
        BigInteger q = new BigInteger(bitlen / 2, 100, r);
        n = p.multiply(q);
        BigInteger m = (p.subtract(BigInteger.ONE))
                .multiply(q.subtract(BigInteger.ONE));
        e = new BigInteger("3");
        while(m.gcd(e).intValue() > 1) e = e.add(new BigInteger("2"));
        d = e.modInverse(m);
    }
    public BigInteger encrypt(BigInteger message)
    {
        return message.modPow(e, n);
    }
    public BigInteger decrypt(BigInteger message)
    {
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
