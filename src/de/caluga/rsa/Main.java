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
//        RSA rsa=new RSA(128);
//        RSA rsa = new RSA(new BigInteger("95B20798A03CC279", 16),
//                new BigInteger("1DF067EB03DF11CD", 16),
//                new BigInteger("5", 16));
////        RSA rsa= new RSA(2048);
////
//        BigInteger small=new BigInteger("fffffff",16);
//        small=BigInteger.add(small,new BigInteger("f1234214",16),-1);
//        small=BigInteger.times(small,new BigInteger("ff",16));


        SecureRandom rnd = new SecureRandom();
//        BigInteger i=new BigInteger(128,100, rnd);
        BigInteger i = new BigInteger("C77C8C2AB24A14665419AF06ACDCEFB5", 16);
        BigInteger m = new BigInteger("8594623A6BB5A8FBC4A3ACFDB5E742FB", 16);

        m.gcd(new BigInteger("12345", 16));
//        for (int j=0;j<128;j++) {
//            i=i.shiftRight(1);
//            System.out.println("Shifted... "+j+": "+i);
//        }

        BigInteger slf = BigInteger.valueOf(1).shiftLeft(127);
        boolean prm = i.isProbablePrime(100);

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
