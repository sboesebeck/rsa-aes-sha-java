package de.caluga.rsa;

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
//        RSA rsa=new RSA(64);
        RSA rsa = new RSA(new BigInteger("95B20798A03CC279", 16),
                new BigInteger("1DF067EB03DF11CD", 16),
                new BigInteger("5", 16));
//        RSA rsa= new RSA(2048);
//
        BigInteger p = new BigInteger("E0A5CE7244453F9B", 16);
        BigInteger q = new BigInteger("CA7571B01E2E0833", 16);
        BigInteger e = new BigInteger("476BBBCF61ACDAEB30052C9DBDA288D", 16);
        BigInteger m = new BigInteger("B1A9E47140B209B4A16EFA38F92F3C14", 16);

        BigInteger n = p.multiply(q);
        BigInteger d = e.modInverse(m);


        BigInteger tst = new BigInteger("D3D670AD80A99DDB1C581CBA0E0CAD93", 16);
        BigInteger enc = rsa.encrypt(tst);
        BigInteger dec = rsa.decrypt(enc);

        SecureRandom rnd = new SecureRandom();
//        BigInteger int2=new BigInteger(128,100, rnd);

//        BigInteger z=BigInteger.valueOf(2).modPow(new BigInteger("278C9F23A14A38F6278F110572D50155",16),int2);
//        BigInteger t = new BigInteger("255D6297C65315DEC2D1CC4AE88DAF622BE917B0FF1C59470BF463FA4DE46E39", 16);
//        t = t.mod(int2); //// <<<<<< errror
//        BigInteger rem = new BigInteger("0", 16);
//        BigInteger.divide(int2, BigInteger.valueOf(5), null, rem, 3);
//        System.out.println("Rnd: "+int2);
        boolean ispr = tst.isProbablePrime(100);
        if (ispr) {
            System.out.println("PRIME!");
        }
//        int1= int2.gcd(new BigInteger("12345512",16));
//        int2.modInverse(int1);
//        System.out.println(" took " + (System.currentTimeMillis() - start) + " ms");
//        System.out.println("Prime: " + int2);


    }

}
