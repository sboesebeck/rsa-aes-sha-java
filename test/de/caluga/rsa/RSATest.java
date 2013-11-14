package de.caluga.rsa;

import org.junit.Test;

import java.nio.charset.Charset;
import java.security.SecureRandom;
import java.util.ArrayList;
import java.util.List;

/**
 * User: Stephan Bösebeck
 * Date: 29.10.13
 * Time: 13:33
 * <p/>
 * TODO: Add documentation here
 */
public class RSATest {
    @Test
    public void encryptDecryptSingleTest() {
        RSA r = new RSA(512);
        BigInteger message = new BigInteger(128, new SecureRandom());
        BigInteger enc = r.encrypt(message);
        BigInteger dec = r.decrypt(enc);
        assert (dec.equals(message));
    }

    @Test
    public void encryptDecryptTest() {
        for (int n = 0; n < 10; n++) {
            System.out.println("Test no " + n);
            RSA r = new RSA(512);
            String message = "A testmessage! A Testmessage!";
            for (int i = 0; i < 0; i++) {
                message += message;
            }
            byte[] enc = r.encrypt(message);
            byte[] dec = r.decrypt(enc);
            String decoded = new String(dec, Charset.forName("UTF8"));
            assert (decoded.equals(message)) : decoded + "!=" + message;
        }
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
    public void longTextTest() {
        RSA r = new RSA(1024);
        String message = "A testmessage! A Testmessage!";
        for (int i = 0; i < 10; i++) message = message + message;
        byte[] enc = r.encrypt(message);
        byte[] dec = r.decrypt(enc);
        String str = new String(dec);
        assert (str.equals(message));
    }

    @Test
    public void conversionTest() {
        List<Byte> bytes = new ArrayList<>();
        for (int i = 0; i < 112; i++) {
            bytes.add((byte) (Math.random() * 256));
        }
        byte[] arr = new byte[bytes.size()];
        int cnt = 0;
        for (Byte b : bytes) {
            arr[cnt++] = b;
        }
        System.out.println("Data: " + Utils.getHex(arr));
        //got random data
        List<BigInteger> bis = BigInteger.getIntegersOfBitLength(arr, 120);
        System.out.println("Got bigIntegers: " + bis.size());
        byte res[] = BigInteger.dataFromBigIntArray(bis);
        assert (res.length == arr.length) : "Array sizes differ!";
        for (int i = 0; i < res.length; i++) {
            assert (res[i] == arr[i]);
        }
        System.out.println("res : " + Utils.getHex(res));
        System.out.println("done");
    }

    @Test
    public void communicationTest() {
        for (int c = 0; c < 500; c += 25) {
            RSA serverKeyPair = new RSA(512 + c);
            RSA clientKeypair = new RSA(128 + c);

            //server Sends public-Key:
            byte[] serverPubKey = serverKeyPair.getPublicKeyBytes();

            //client reads them
            RSA serverPubKeyRsa = RSA.publicKey(serverPubKey);

            //now encoding clients publicKey
            byte[] publicKeyBytes = clientKeypair.getPublicKeyBytes();
            byte[] pubkEncoded = serverPubKeyRsa.encrypt(publicKeyBytes);

            //server gets encoded Public KEy => decoding
            byte[] decoededPublicKey = serverKeyPair.decrypt(pubkEncoded);
            RSA clientPubKey = RSA.publicKey(decoededPublicKey);
            assert (clientPubKey.getN().equals(clientKeypair.getN()));
            assert (clientPubKey.getE().equals(clientKeypair.getE()));

            String aMessage = "This is a simple Message for the client, sent by server";
            for (int i = 0; i < 10; i++) aMessage += aMessage;

            byte[] encodedMessage = clientPubKey.encrypt(aMessage);

            //on client, receive answer
            byte[] decodedMessage = clientKeypair.decrypt(encodedMessage);
            String receivedMessage = new String(decodedMessage, Charset.forName("UTF8"));
            assert (receivedMessage.equals(aMessage));
        }
    }

}
