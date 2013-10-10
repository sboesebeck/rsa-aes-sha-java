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
//        RSA rsa = new RSA(new BigInteger("95B20798A03CC279", 16),
//                new BigInteger("1DF067EB03DF11CD", 16),
//                new BigInteger("5", 16));
//        RSA rsa= new RSA(2048);
//
//        BigInteger tst=new BigInteger("0789456",16);
//        BigInteger enc=rsa.encrypt(tst);
//        BigInteger dec=rsa.decrypt(enc);

        SecureRandom rnd = new SecureRandom();
//        BigInteger int2=new BigInteger(128,100, rnd);
//        BigInteger int1 = new BigInteger("13C64F91D0A51C7B13C78882B96A80AA", 16);
        BigInteger int2 = new BigInteger("9E327C8E8528E3D89E3C4415CB540555", 16);
//        BigInteger z=BigInteger.valueOf(2).modPow(new BigInteger("278C9F23A14A38F6278F110572D50155",16),int2);
//        BigInteger t=new BigInteger("255D6297C65315DEC2D1CC4AE88DAF622BE917B0FF1C59470BF463FA4DE46E39",16);
//        t=t.mod(int2);
            int2.isProbablePrime(100);
//        int2.modInverse(int1);
//        System.out.println(" took " + (System.currentTimeMillis() - start) + " ms");
//        System.out.println("Prime: " + int2);


    }

}
