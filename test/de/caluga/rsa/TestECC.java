package de.caluga.rsa;

import de.caluga.ecc.*;
import org.junit.Test;

import java.util.Arrays;

/**
 * User: Stephan Bösebeck
 * Date: 19.05.14
 * Time: 22:05
 * <p/>
 * TODO: Add documentation here
 */
public class TestECC {

    @Test
    public void testBase() throws Exception {

        ECDomainFactory ecDomainFactory = new ECDomainFactory();

        for (Integer bits : ecDomainFactory.getParamsByBitLen().keySet()) {

            for (ECDomainParameters p : ecDomainFactory.getParamsByBitLen().get(bits)) {
                System.out.println("Encrypting with bitlen " + bits);
                EllipticCurve ec = new EllipticCurve(p);

                ECCrypt cs = new ECCrypt(ec);

                ECKey sk = cs.generateKey();
                ECKey pk = sk.getPublic();

                byte[] test1 = new byte[64];
                for (int i = 0; i < test1.length; i++) {
                    test1[i] = (byte) i;
                }
                byte[] test2 = cs.decryptBlock(cs.encryptBlock(test1, test1.length, pk), sk);
                if (Arrays.equals(test1, test1)) System.out.println("Testing...");
                if (Arrays.equals(test1, test2)) {
                    System.out.println("Succes");
                } else {
                    System.out.print("Fail\n{");
                    for (int i = 0; i < 20; i++) {
                        System.out.print(test2[i] + ",");
                    }
                    System.out.println("}");
                }
            }
        }
    }
}
