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
    public void AESTest() {
        AES aes = new AES();
        aes.setKey("the secret key!!the secret key!!");
        System.out.println(aes.traceInfo);
        String enc = aes.encrypt("0123456789acbdef");
        System.out.println("Encrypted: " + enc);
        String dec = aes.decrypt(enc);
        System.out.println("Decrypted: " + dec);
    }
}
