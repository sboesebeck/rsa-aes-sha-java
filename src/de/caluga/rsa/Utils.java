package de.caluga.rsa;

import java.nio.charset.Charset;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

/**
 * User: Stephan Bösebeck
 * Date: 24.10.13
 * Time: 22:20
 * <p/>
 * TODO: Add documentation here
 */
public class Utils {
    private static String[] chars = {"0", "1", "2", "3", "4", "5", "6", "7", "8", "9", "A", "B", "C", "D", "E", "F",
//            "G","H","I","J","K","L","M","N","O","P","Q","R","S","T","U","V","W","X","Y","Z",
//            "Ä","Ö","Ü","ß"
    };

    public static String getHex(byte[] arr) {
        StringBuilder b = new StringBuilder();
        for (byte by : arr) {
            int idx = (by >>> 4) & 0x0f;
            b.append(chars[idx]);
            idx = by & 0x0f;
            b.append(chars[idx]);
        }
        return b.toString();
    }


    public static String getMd5String(String str) {
        return getMd5String(str.getBytes(Charset.forName("UTF8")));
    }

    public static byte[] getMd5(byte b[]) {
        MessageDigest digest = null;
        try {
            digest = MessageDigest.getInstance("MD5");
        } catch (NoSuchAlgorithmException e1) {
            throw new RuntimeException(e1);
        }
        digest.update(b);
        return digest.digest();
    }

    public static String getMd5String(byte[] b) {
        byte[] hash = getMd5(b);
        return Utils.getHex(hash);
    }
}
