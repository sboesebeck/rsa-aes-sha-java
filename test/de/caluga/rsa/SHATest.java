package de.caluga.rsa;

import org.junit.Test;

/**
 * Created with IntelliJ IDEA.
 * User: stephan
 * Date: 07.01.14
 * Time: 16:35
 * To change this template use File | Settings | File Templates.
 */
public class SHATest {
    @Test
    public void testSHA3() {
        SHA3 sha = new SHA3();
        sha.init();
        byte[] b = "Test".getBytes();
        System.out.println("Plain: " + Utils.getHex(b));
        sha.engineUpdate(b, 0, b.length);
        byte[] dig = sha.engineDigest();
        System.out.println("SHA3(384): " + Utils.getHex(dig));

        b = "Test".getBytes();
        sha.engineUpdate(b, 0, b.length);
        dig = sha.engineDigest();
        System.out.println("SHA3(384): " + Utils.getHex(dig));

        sha = new SHA3(64);
        sha.engineUpdate(b, 0, b.length);
        dig = sha.engineDigest();
        System.out.println("SHA3(512): " + Utils.getHex(dig));

        sha = new SHA3(32);
        sha.engineUpdate(b, 0, b.length);
        dig = sha.engineDigest();
        System.out.println("SHA3(256): " + Utils.getHex(dig));
    }


    @Test
    public void testSHA5() {
        SHA5 sha = new SHA5();
        sha.init();
        byte[] b = "Test".getBytes();
        System.out.println("Plain: " + Utils.getHex(b));
        sha.engineUpdate(b, 0, b.length);
        byte[] dig = sha.engineDigest();
        System.out.println("SHA512: " + Utils.getHex(dig));


    }

    @Test
    public void testLFDelta() {
        long w = -6739068508724883454l;
        long ret = SHA5.lf_delta1(w);
        System.out.println(ret);
    }
}
