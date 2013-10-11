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
        BigInteger int1 = new BigInteger("A90DF1147BE5C8D98382CD9B26B47D22DC768E3A78D7939C72497A3C2CD55C61", 16);
        BigInteger int2 = new BigInteger("9D7B7D37F53666B7FBDC237DD7BA5B1F", 16);
        BigInteger a = new BigInteger("B9D1BC03144B64F89C272EC435553A764EB12A1222FB8593804B5EE287E093E89631FB8D8DF74FE73CAA4098", 16);
        BigInteger b = new BigInteger("B221272CD69D065FF7766DEECC2BFAA003C663A49CCCA103FAA654AB69DF53E1F2E144257A5279E00BD1791D", 16);
        BigInteger rem = new BigInteger("7B094D63DAE5E98A4B0C0D569293FD64AEAC66D862EE48F85A50A371E014006A350B76813A4D60730D8C77B", 16);
        BigInteger arr[]=new BigInteger[2];
        BigInteger.euclidInv(b,rem,BigInteger.valueOf(1),arr); //<<== something's wrong here!
        BigInteger mi=a.modInverse(b);
//        BigInteger z=BigInteger.valueOf(2).modPow(new BigInteger("278C9F23A14A38F6278F110572D50155",16),int2);
//        BigInteger t = new BigInteger("255D6297C65315DEC2D1CC4AE88DAF622BE917B0FF1C59470BF463FA4DE46E39", 16);
//        t = t.mod(int2); //// <<<<<< errror
//        BigInteger rem = new BigInteger("0", 16);
//        BigInteger.divide(int2, BigInteger.valueOf(5), null, rem, 3);
//        System.out.println("Rnd: "+int2);
//        boolean ispr = int1.isProbablePrime(100);
//        if (ispr) {
//            System.out.println("PRIME!");
//        }
//        int1= int2.gcd(new BigInteger("12345512",16));
//        int2.modInverse(int1);
//        System.out.println(" took " + (System.currentTimeMillis() - start) + " ms");
//        System.out.println("Prime: " + int2);


    }

}
