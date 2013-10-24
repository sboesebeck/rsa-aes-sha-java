package de.caluga.rsa;

import java.nio.charset.Charset;
import java.security.SecureRandom;

public class Main {

    public static void main(String[] args) {

//      +
//        BigInteger int1=new BigInteger("2EFD21E70A4F55238",16);
//        BigInteger int2=new BigInteger("3",16);
//        while (int1.gcd(int2).intValue()>1) {
//            int2=int2.add(new BigInteger("2",16));
//        }
//        System.out.println("E: "+int2);
//        BigInteger tst=new BigInteger("1B079047D",16);
//        BigInteger bi=tst.subtract(BigInteger.ONE);
//        System.out.println("Bi: "+bi);
//        RSA rsa=new RSA(128);
//        RSA rsa = new RSA(new BigInteger("B60F47FCE1CDEDD5C8F9D8E0AF781A0D", 16),
//                new BigInteger("3FFAA06A337D84B26A542EB2DD6618C9", 16),
//                new BigInteger("866D0F53920C4295", 16));
////
        BigInteger a = new BigInteger("8417B6F5E3B0324E", 16);
//        BigInteger b = new BigInteger("12666F791BF7E8A3", 16);
//        BigInteger rem = new BigInteger("0", 16);
//        BigInteger quot = new BigInteger("0", 16);
//
//        BigInteger.divide(a, b, quot, rem, 1);
//        BigInteger[] bis = new BigInteger[2];
//        BigInteger.euclidInv(b, rem, new BigInteger("1", 16), bis);
//        BigInteger small = new BigInteger("1234567890", 16);
        RSA rsa = new RSA(128);

        byte[] b = rsa.bytes();
        System.out.println("RSA1: " + rsa);
        System.out.println("rsa bytes: " + Utils.getHex(b));
        RSA rsa2 = RSA.fromBytes(b);
        System.out.println("RSA2: " + rsa2);

//        BigInteger enc = rsa.encrypt(small);
//        BigInteger dec = rsa.decrypt(enc);

//        small=BigInteger.add(small,new BigInteger("f1234214",16),-1);
//        small=BigInteger.times(small,new BigInteger("ff",16));


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

//        d = e.modInverse(m);

////        m.gcd(new BigInteger("12345", 16));
//        for (int j=0;j<128;j++) {
//            i=i.shiftRight(1);
//            System.out.println("Shifted... "+j+": "+i);
//        }

//        BigInteger slf = BigInteger.valueOf(1).shiftLeft(127);
//        boolean prm = i.isProbablePrime(100);
//        RSA r = new RSA(128);
//        System.out.println("RSA: " + r);

//        BigInteger z=BigInteger.valueOf(2).modPow(new BigInteger("278C9F23A14A38F6278F110572D50155",16),int2);
//        BigInteger t = new BigInteger("255D6297C65315DEC2D1CC4AE88DAF622BE917B0FF1C59470BF463FA4DE46E39", 16);
//        t = t.mod(int2); //// <<<<<< errror
//        BigInteger rem = new BigInteger("0", 16);
//        BigInteger.divide(int2, BigInteger.valueOf(5), null, rem, 3);
//        System.out.println("Rnd: "+int2);
//        boolean ispr = tst.isProbablePrime(100);
//        if (ispr) {
//            System.out.println("PRIME!");
//        }
//        int1= int2.gcd(new BigInteger("12345512",16));
//        int2.modInverse(int1);
//        System.out.println(" took " + (System.currentTimeMillis() - start) + " ms");
//        System.out.println("Prime: " + int2);


    }

}
