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
//        BigInteger int1=new BigInteger(2048, rnd);
        BigInteger int1 = new BigInteger("C0F520678AE1644F67BC03CB124D3F127800E8C539BC6F9B15076B5182BBCEA8969F5D8F8A1D51E65A3FABFDA2A30939D6514BBAA72CCECE05261B2558A9BBEC73335957842BDB8B01FEFF48962A95506B57687DCAF2A21DBAA45A1C1122B2531D11230C2310DD13C905BEF608904D32955D7031D88698C5A35FFCE5F07FB40B", 16);
        BigInteger int2 = new BigInteger("213421341234123412341234AFDE234D", 16);
        long start = System.currentTimeMillis();
//        RSA rsa=new RSA(1024);
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
        RSA rsa = new RSA(4096);
        System.out.println(" took " + (System.currentTimeMillis() - start) + " ms");
        System.out.println("Prime: " + int2);

    }

}
