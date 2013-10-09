package de.caluga.rsa;

import java.security.SecureRandom;

public class Main {

    public static void main(String[] args) {

//      +
//        BigInteger m=new BigInteger("2EFD21E70A4F55238",16);
//        BigInteger e=new BigInteger("3",16);
//        while (m.gcd(e).intValue()>1) {
//            e=e.add(new BigInteger("2",16));
//        }
//        System.out.println("E: "+e);
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
//        BigInteger e=new BigInteger(128,100, rnd);
        BigInteger m = new BigInteger("13C64F91D0A51C7B13C78882B96A80AA", 16);
        BigInteger e = new BigInteger("9E327C8E8528E3D89E3C4415CB540555", 16);
        BigInteger sr = m.shiftRight(1);
        sr = sr.shiftRight(1);
        if (e.isProbablePrime(100)) {
            System.out.println("Joooo! " + e.toString());
        }
//        BigInteger tmp = new BigInteger("1F0352197BD7827F800000000000000", 16);
//        BigInteger m = new BigInteger("C1F95BCD0850FB01", 16);
//        BigInteger s = tmp.mod(m);

        long start = System.currentTimeMillis();
//        for (int i=0; i<1000;i++) {
        RSA rsa = new RSA(4096);
//            e=BigInteger.probablePrime(64,rnd);
//            if (e.isProbablePrime(100)) {
//                System.out.println("Jo "+e);
//            }
//            BigInteger integer=new BigInteger(512,rnd);
//            if (integer.isNegative()) {
//                System.out.println("Found it: "+integer);
//                break;
//            }

//        }
//         int2=BigInteger.probablePrime(1024, rnd);
//           int1.isProbablePrime(100);
//        int xlen = int1.getIval();
//        int ylen = int2.getIval();
//        int[] xwords = int1.getWords();
//        int[] ywords = int2.getWords();
//        BigInteger m = int2;
//        for (int i = 0; i < 100000; i++) {
//            BigInteger s = BigInteger.ONE;
//            BigInteger t = BigInteger.valueOf(13);
//            BigInteger u = int1;
//            int count=0;
////            while (!u.isZero()) {
////                count++;
//                if (u.and(BigInteger.ONE).isOne()) {
//                    BigInteger tmp = BigInteger.times(s, t);
//                    s = tmp.mod(m);
//                }
//                u = u.shiftRight(1);
//                t = BigInteger.times(t, t).mod(m);
////            }
////            System.out.println(i+" - "+count);
//        }
//        RSA rsa = new RSA(4096);


//        e.modInverse(m);
        System.out.println(" took " + (System.currentTimeMillis() - start) + " ms");
//        System.out.println("Prime: " + int2);


    }

}
