package de.caluga.rsa;

/**
 * Created with IntelliJ IDEA.
 * User: stephan
 * Date: 23.05.14
 * Time: 08:32
 * To change this template use File | Settings | File Templates.
 */
public interface PrimeGenerator {

    public BigInteger getNexProbablePrime(int bitlen, int certainty);
}
