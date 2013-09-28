package de.caluga.rsa;

public class Main {

    public static void main(String[] args) {

//        Random rnd = new Random();
//        int[] ar=new int[]{538344319,234521319,17};
//        BigInteger int1=new BigInteger(ar,3);
//        ar=new int[]{1368356677,1545563740};
//        BigInteger int2=new BigInteger(ar,2);

//        BigInteger tst=new BigInteger(32,new Random());
//        System.out.println(tst);
        BigInteger tst = new BigInteger("BFA3008151E5A1C1", 16);
        BigInteger rem = tst.mod(new BigInteger("102892A30", 16));
        System.out.println("Rem: " + rem);
        if (tst.isProbablePrime(100)) {
            System.out.println("is prime");
        } else {
            System.out.println("NO PRIME");
        }
//        int2=int2.negate();
//        BigInteger int3=int1.divide(int2);
//        System.out.println(int1.toString(16)+" / "+int2.toString(16)+" = "+int3.toString(16));

//        RSA r=new RSA(512);
//        BigInteger enc = r.encrypt(int1);
//        System.out.println(int1.toString(16)+" encrypted =>  "+enc.toString(16));
//        BigInteger dec= r.decrypt(enc);
//        System.out.println(enc.toString(16)+" decrypted =>  "+dec.toString(16));

//        BigInteger int3=new BigInteger(63,100,rnd);
//        if (int2.isProbablePrime(100)) { System.out.println("is prime"); };

//        RSA rsa=new RSA(new BigInteger("673607FCD578BB79",16),new BigInteger("47742CE93C53FDC5",16),new BigInteger("0D",16));
//        System.out.println("RSA: "+rsa);
//        RSA rsa=new RSA(64);
//        RSA rsa=new RSA(new BigInteger("63BDDB687AC0C74FF8BE5",16),new BigInteger("1CB3CC27DB3D0F21",16),new BigInteger("7",16));
//        BigInteger enc=rsa.encrypt(int2);
//        BigInteger dec=rsa.decrypt(enc);
//        System.out.println("plain: "+int2);
//        System.out.println(" Enc : "+enc);
//        System.out.println(" Dec : "+dec);
    }
}
