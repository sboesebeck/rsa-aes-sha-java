package de.caluga.rsa;

import org.junit.Test;

import java.nio.charset.Charset;
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
    public void encryptDecryptTest() {
        RSA r = new RSA(512);
        String message = "A testmessage! A Testmessage!";
        for (int i = 0; i < 10; i++) {
            message += message;
        }
        byte[] enc = r.encrypt(message);
        byte[] dec = r.decrypt(enc);
        String decoded = new String(dec, Charset.forName("UTF8"));
        assert (decoded.equals(message));
    }

    @Test
    public void signingTest() {
        RSA r = new RSA(512);
        String message = "A testmessage! A Testmessage!";

        byte[] sign = r.sign(message.getBytes(Charset.forName("UTF8")));
        assert (r.isValidSigned(sign, message.getBytes(Charset.forName("UTF8"))));
        message = "different";
        assert (!r.isValidSigned(sign, message.getBytes(Charset.forName("UTF8"))));
    }

    @Test
    public void communicationTest() {
        RSA client1 = new RSA(512);
        RSA client2 = new RSA(512);
//        byte[] client1PublicKey=client1.getPublicKey();

        //client1 sends message to client2
        byte[] client2PublicKey = client2.getPublicKey();

        //client1 has publickey
        RSA pubKeyClient2 = new RSA();
        pubKeyClient2.setPublicKey(client2PublicKey);

        //encrypt message
        String message = "The secret message";
        byte enc[] = pubKeyClient2.encrypt(message);

        ///===>> message then transferred to client2
        byte[] dec = client2.decrypt(enc);
        String decoded = new String(dec, Charset.forName("UTF8"));

        assert (decoded.equals(message));


    }
}
