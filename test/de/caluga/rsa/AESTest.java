package de.caluga.rsa;

import org.junit.Test;

/**
 * Created with IntelliJ IDEA.
 * User: stephan
 * Date: 07.01.14
 * Time: 16:54
 * To change this template use File | Settings | File Templates.
 */
public class AESTest {

    @Test
    public void AESBlockTest() {
        byte b[] = new byte[16];
        for (int i = 1; i <= 16; i++) b[i - 1] = (byte) i;
        AES aes = new AES();
        aes.setKey("the secret key!!the secret key!!");
        System.out.println("Cleartext: " + Utils.getHex(b));
        byte enc[] = aes.encryptBlock(b);
        System.out.println("Encrypted: " + Utils.getHex(enc));
        byte dec[] = aes.decryptBlock(enc);
        System.out.println("Decrypted: " + Utils.getHex(dec));

        assert (Utils.getHex(b).equalsIgnoreCase(Utils.getHex(dec)));
        System.out.println();
        b = new byte[16];
        for (int i = 10; i <= 16; i++) b[i - 1] = (byte) i;
        System.out.println("Cleartext: " + Utils.getHex(b));
        enc = aes.encryptBlock(b);
        System.out.println("Encrypted: " + Utils.getHex(enc));
        dec = aes.decryptBlock(enc);
        System.out.println("Decrypted: " + Utils.getHex(dec));

        assert (Utils.getHex(b).equalsIgnoreCase(Utils.getHex(dec)));
        System.out.println();

        b = new byte[16];
        for (int i = 1; i <= 5; i++) b[i - 1] = (byte) i;
        System.out.println("Cleartext: " + Utils.getHex(b));
        enc = aes.encryptBlock(b);
        System.out.println("Encrypted: " + Utils.getHex(enc));
        dec = aes.decryptBlock(enc);
        System.out.println("Decrypted: " + Utils.getHex(dec));

        assert (Utils.getHex(b).equalsIgnoreCase(Utils.getHex(dec)));
        System.out.println();
    }

    @Test
    public void AESTestDifferentLength() {
        AES aes = new AES();
        aes.setKey("secret1234512345");
        String data = "12345";

        byte[] enc1 = aes.encrypt(data);
        System.out.println(Utils.getHex(enc1));
        enc1 = aes.encrypt(data.getBytes());
        System.out.println(Utils.getHex(enc1));
    }
    @Test
    public void AESTest() {
        AES aes = new AES();
        aes.setKey("the secret key!!the secret key!!");

        String data = "0123456789abcdef0123456789abcdef887698769";

        byte[] enc1 = aes.encrypt(data.getBytes());
        byte[] enc2 = aes.encrypt(data);
        byte[] dec1 = aes.decrypt(enc1);
        byte[] dec2 = aes.decrypt(enc2);
        System.out.println(new String(dec1));
        System.out.println(new String(dec2));
        System.out.println(Utils.getHex(dec1));
        System.out.println(Utils.getHex(dec2));
        System.out.println(Utils.getHex(data.getBytes()));
        assert (new String(dec1).equals(new String(dec2)));
        assert (new String(dec1).equals(data));
        assert (new String(dec2).equals(data));

    }
}
