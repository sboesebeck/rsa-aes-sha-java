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
    public void testAdd() {
        BigInteger tst1 = new BigInteger("34CB953482A3533C48847E93ABDF8050215", 16);
        BigInteger tst2 = new BigInteger("34CB953482A3533C48847E93ABD1AC4F6C2", 16);
        BigInteger tst3 = tst2.add(tst1);
        int cmp = tst1.compareTo(tst2);
    }


    @Test
    public void primeTest() {
        BigInteger int1 = new BigInteger("F1DA144956AFD98AEF578E45D99BF86D", 16);
        BigInteger m = new BigInteger("3C76851255ABF662BBD5E3917666FE1B", 16);
        BigInteger res1 = new BigInteger("3").modPow(m, int1);
        BigInteger res2 = res1.mod(m);

        System.out.println("PRimne: " + int1);

        int1.isProbablePrime(100);
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
        for (int i = 0; i < 100; i++) {
            BigInteger int1 = new BigInteger(512, rnd);
            byte[] dat = int1.toByteArray(); //just random data
            dat[0] = 0;
            List<BigInteger> lst = BigInteger.getIntegersOfBitLength(dat, 128);
            System.out.println("List length: " + lst.size());
            byte[] dat2 = BigInteger.dataFromBigIntArray(lst);
            assert (Utils.getHex(dat).equals(Utils.getHex(dat2)));
        }
    }

    @Test
    public void testModInverse() {
        BigInteger int1 = new BigInteger("E10271E7CFF5BD11EC53B5D5C6718E486A8154148488546192672A6E7D700D08ABB3D08E973DC612ACBFD12FF0AA2802B737C74EE599423D4789FD55EA0C080649567EBBA7F791F10C5CD90124D67157E0E0B69519BF1135A295E985137A67C0", 16);
        BigInteger int2 = new BigInteger("18FF86790796D5B318D6C11EAEF5AA20E8D9F4875513624ECA5C5A50D00387707D3D65C971B7F8FFDF5F1E47896C1080072B48FF793233D8EEB784A7C066513EF030146619F974AA6BB1DB564A1D23C67176CE588BC92F0A476C30B276D130E5", 16);
        BigInteger d = int1.modInverse(int2);

    }

    @Test
    public void testRemainder() {
        BigInteger int1 = new BigInteger(128, new SecureRandom());
        BigInteger add = BigInteger.valueOf(0);
        for (int i = 0; i < 17; i++) {
            add = add.add(int1);
        }
        BigInteger div = add.divide(int1);
        assert (div.toString().equals("11"));
        BigInteger int2 = int1.subtract(BigInteger.valueOf(1));
        add = add.add(int2);
        BigInteger[] res = add.divideAndRemainder(int1);
        assert (res[0].equals(div));
        assert (res[1].equals(int2));
    }

}
