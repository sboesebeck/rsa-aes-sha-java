package de.caluga.rsa;

import java.nio.charset.Charset;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;

public class Main {

    public static void main(String[] args) throws NoSuchAlgorithmException {

        RSA rsa = new RSA(128);

        byte[] b = rsa.bytes();
        System.out.println("RSA1: " + rsa);
        System.out.println("rsa bytes: " + Utils.getHex(b));
        RSA rsa2 = RSA.fromBytes(b);
        System.out.println("RSA2: " + rsa2);

        SecureRandom rnd = new SecureRandom();
//        BigInteger i=new BigInteger(128,100, rnd);
        BigInteger e = new BigInteger("6A32DA763FCE536C7D4A9E8A7749DB0B", 16);
        BigInteger n = new BigInteger("5A194CAB9ED4DCB84505C19F73C4E64F", 16);
        BigInteger d = new BigInteger("0", 16);
        BigInteger m = new BigInteger("5A194CAB9ED4DCB7154640E617248300", 16);
        System.out.println(Utils.getHex(m.bytes()));

        String tst = "this is a test";
        byte[] clear = tst.getBytes(Charset.forName("UTF8"));
        System.out.println("Cleartext: " + Utils.getHex(clear));
        byte[] enc = rsa.encrypt(clear);
        byte[] dec = rsa.decrypt(enc);
        System.out.println("decrypted: " + Utils.getHex(dec));
        String tstDec = new String(dec, Charset.forName("UTF8"));
        System.out.println("Text decoded: " + tstDec);

        byte[] signature = rsa.sign(clear);

        if (rsa.isValidSigned(signature, clear)) {
            System.out.println("YES!");
        }

        //fail signature test
        tst = "This is different!";
        clear = tst.getBytes(Charset.forName("UTF8"));
        if (rsa.isValidSigned(signature, clear)) {
            System.out.println("OH NO - should fail!");
        } else {
            System.out.println("YEA!");
        }


        /// Test kommunication between two...

        RSA r1 = new RSA(512);
        r1.setId("Client1 Keypair");
        RSA r2 = new RSA(512);
        r2.setId("Client2 keypair");

        System.out.println("Client1 keypair: " + r1);
        System.out.println("Client2 keypair: " + r2);


        RSA publicKey1 = new RSA();
        publicKey1.setId("PubKey client1");
        publicKey1.setPublicKey(r1.getPublicKey());

        System.out.println("PubKey1:" + publicKey1);
        RSA publicKey2 = new RSA();
        publicKey2.setId("PubKey client2");
        publicKey2.setPublicKey(r2.getPublicKey());

        //Sending Message from clien1 to client2
        //Starting on client1
        String message = "Testmessage!";
        byte[] encryptedMessage = publicKey2.encrypt(message);

        //on client2
        byte[] msg = r2.decrypt(encryptedMessage);
        String msgClear = new String(msg, Charset.forName("UTF8"));
        System.out.println("client 2 got Message: " + msgClear);


    }

}
