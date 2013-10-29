package de.caluga.rsa;

import org.junit.Test;

import java.security.SecureRandom;

/**
 * User: Stephan Bösebeck
 * Date: 26.10.13
 * Time: 21:53
 * <p/>
 * TODO: Add documentation here
 */
public class TestBigInteger {

    @Test
    public void testSimple() {
        BigInteger int1 = new BigInteger("f0", 16);
        BigInteger int2 = new BigInteger("2", 16);
        BigInteger res = int1.multiply(int2);
        String resStr = res.toString(16);
        assert (resStr.equals("1E0"));
        BigInteger res2 = res.divide(int2);
        assert (res2.equals(int1));
    }

    @Test
    public void testBig() {
        SecureRandom rnd = new SecureRandom();
        BigInteger int1 = new BigInteger(512, rnd);
        BigInteger int2 = new BigInteger(512, rnd);
        BigInteger res = int1.multiply(int2);
        BigInteger res2 = res.divide(int2);
        assert (res2.equals(int1));
    }

    @Test
    public void testMultShift() {
        SecureRandom rnd = new SecureRandom();
        BigInteger int1 = new BigInteger(512, rnd);
        BigInteger int2 = int1.multiply(BigInteger.valueOf(2));
        BigInteger int3 = int1.shiftLeft(1);
        assert (int2.equals(int3));
    }


    @Test
    public void testDivShift() {
        SecureRandom rnd = new SecureRandom();
        BigInteger int1 = new BigInteger(512, rnd);
        BigInteger int2 = int1.divide(BigInteger.valueOf(2));
        BigInteger int3 = int1.shiftRight(1);
        assert (int2.equals(int3));
    }

    @Test
    public void testPow() {
        BigInteger int1 = new BigInteger("affe", 16);
        BigInteger int2 = int1.pow(2);
        BigInteger int3 = int1.multiply(int1);

        BigInteger result = new BigInteger("78FD4004", 16);
        assert (int2.equals(int3));
        assert (int3.equals(result));
    }

}
