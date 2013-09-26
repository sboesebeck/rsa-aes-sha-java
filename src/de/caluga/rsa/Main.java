package de.caluga.rsa;

import java.util.Random;

public class Main {

    public static void main(String[] args) {

        Random rnd = new Random();
        int[] ar=new int[]{538344319,234521319,17};
        BigInteger int1=new BigInteger(ar,3);
        ar=new int[]{1368356677,1545563740};
        BigInteger int2=new BigInteger(ar,2);
//        int2=int2.negate();
//        BigInteger int3=int1.divide(int2);
//        System.out.println(int1.toString(16)+" / "+int2.toString(16)+" = "+int3.toString(16));

//        RSA r=new RSA(512);
//        BigInteger enc = r.encrypt(int1);
//        System.out.println(int1.toString(16)+" encrypted =>  "+enc.toString(16));
//        BigInteger dec= r.decrypt(enc);
//        System.out.println(enc.toString(16)+" decrypted =>  "+dec.toString(16));

//        BigInteger int3=new BigInteger(63,100,rnd);
        if (int2.isProbablePrime(100)) { System.out.println("is prime"); };
    }
}
