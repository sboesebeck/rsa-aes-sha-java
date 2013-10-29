package de.caluga.rsa;

import org.junit.Test;

import java.nio.charset.Charset;
import java.security.SecureRandom;

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
        RSA serverKeyPair = new RSA(2048);
        RSA clientKeypair = new RSA(512);

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
