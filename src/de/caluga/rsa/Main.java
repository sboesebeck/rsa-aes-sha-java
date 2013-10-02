package de.caluga.rsa;

import java.util.List;

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
        RSA rsa = new RSA(new BigInteger("95B20798A03CC279", 16),
                new BigInteger("1DF067EB03DF11CD", 16),
                new BigInteger("5", 16));
        BigInteger m = new BigInteger("6700000000000000", 16);
        BigInteger enc = rsa.encrypt(m);
        BigInteger dec = rsa.decrypt(enc);
        System.out.println(rsa);

        String testStr = "0123456789abcdefgh";
        List<BigInteger> lst = BigInteger.getIntegers(testStr.getBytes(), 63);
        for (BigInteger b : lst) {
            System.out.println("Got " + b);
        }
        byte[] b = BigInteger.getBytes(lst, 63);
        String s = new String(b);
        System.out.println("Got String '" + s + "'");
//
        System.out.println("plain " + m + " encrypted: " + enc + " decrypted: " + dec);
    }

}
