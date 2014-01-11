package de.caluga.rsa;

import de.caluga.rsa.base64.Base64Encoder;
import org.junit.Test;

/**
 * User: Stephan Bösebeck
 * Date: 11.01.14
 * Time: 17:42
 * <p/>
 * TODO: Add documentation here
 */


public class CreateKeysTest {

    @Test
    public void createShortKey() {
        System.out.println("Creating DEV-Keys....");
        createKeys(1768);
        System.out.println("\nDone...\n\n");
    }

    @Test
    public void createMidKey() {
        System.out.println("Creating DEV-Keys....");
        createKeys(2560);
        System.out.println("\nDone...\n\n");
    }

    @Test
    public void createLongKey() {
        System.out.println("long keys...");
        createKeys(4192);
        System.out.println("\nDone...\n\n");
    }

    @Test
    public void createExtremeKey() {
        System.out.println("EXTREME keys...");
        createKeys(8192);
        System.out.println("\nDone...\n\n");

    }


    private void createKeys(int bitLen) {
        RSA r = new RSA(bitLen);
        byte[] byt = r.bytes();
        AES aes = new AES();
        SHA2 sha = new SHA2();
        byte[] key = "CalugaChatSecure-2014-1".getBytes();
        sha.engineUpdate(key, 0, key.length);
        aes.setEncryptionKey(sha.engineDigest(256));
        byte[] encrypted = aes.encrypt(byt);
        Base64Encoder enc = new Base64Encoder();
        String txt = enc.encode(encrypted);
        System.out.println("Keypair:");
        System.out.println(txt);

        System.out.println("\npublicKey:");
        byt = r.getPublicKeyBytes();
        encrypted = aes.encrypt(byt);
        txt = enc.encode(encrypted);
        System.out.println(txt);


    }
}
