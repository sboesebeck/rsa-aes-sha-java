package de.caluga.rsa;

import java.util.Random;

/**
 * Created with IntelliJ IDEA.
 * User: stephan
 * Date: 23.05.14
 * Time: 08:43
 * To change this template use File | Settings | File Templates.
 */
public class DefaultPrimeGenerator implements PrimeGenerator {
    private Random r;

    public DefaultPrimeGenerator(Random r) {
        this.r = r;
    }

    @Override
    public BigInteger getNexProbablePrime(int bitlen, int probability) {
        return new BigInteger(bitlen, probability, r);
    }
}