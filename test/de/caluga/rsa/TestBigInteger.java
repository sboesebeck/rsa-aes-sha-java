package de.caluga.rsa;

import org.junit.Test;

import java.security.SecureRandom;
import java.util.List;

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
        assert (resStr.equalsIgnoreCase("1E0"));
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


    @Test
    public void testByteArrayConversion() {
        BigInteger int1 = new BigInteger("0123456789ABCDEF0123456789ABCDEF0123456789ABCDEF01fefe23456789ABCDEF0123456789ABCDEF00000", 16);
        byte[] dat = int1.serialize();
        BigInteger int2 = new BigInteger("0");
        int offset = BigInteger.fromBytes(int2, dat, 0);
        byte[] dat2 = int2.serialize();
        assert (Utils.getHex(dat).equals(Utils.getHex(dat2)));
        assert (int1.equals(int2)) : "Values different: " + int1 + " != " + int2;
//        System.out.println("int1: "+int1+" int1.ival: "+int1.getIval()+"  int1.words.length:"+int1.getWords().length);
//        System.out.println("int2: "+int2+" int2.ival: "+int2.getIval()+"  int2.words.length:"+int2.getWords().length);
    }


    @Test
    public void testSerializationToFromArray() {
        SecureRandom rnd = new SecureRandom();
        BigInteger int1 = new BigInteger(148, rnd);
        byte[] dat = int1.toByteArray(); //just random data
        List<BigInteger> lst = BigInteger.getIntegersOfBitLength(dat, 128);
        System.out.println("List length: " + lst.size());
        byte[] dat2 = BigInteger.dataFromBigIntArray(lst, 128);
        assert (Utils.getHex(dat).equals(Utils.getHex(dat2)));
    }


}
