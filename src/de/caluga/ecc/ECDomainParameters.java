package de.caluga.ecc;

/** Specifications completely defining an elliptic curve. Used to define an
 *elliptic curve by EllipticCurve.define(ECParamters ecp).
 *NOTE: This is designed for an elliptic curve on the form:
 *      y^2 = x^3 + ax + b (mod p)
 *--with fixed generator and precomputed order.
 */

import de.caluga.rsa.BigInteger;

public class ECDomainParameters {

    private BigInteger a;
    private BigInteger b;

    private BigInteger p;
    private BigInteger x;
    private BigInteger y;
    private BigInteger q;

    public ECDomainParameters(BigInteger p, BigInteger a, BigInteger b, BigInteger x, BigInteger y, BigInteger q) {
        this.a = a;
        this.b = b;
        this.p = p;
        this.x = x;
        this.y = y;
        this.q = q;
    }

    public BigInteger getA() {
        return a;
    }

    public void setA(BigInteger a) {
        this.a = a;
    }

    public BigInteger getB() {
        return b;
    }

    public void setB(BigInteger b) {
        this.b = b;
    }

    public BigInteger getP() {
        return p;
    }

    public void setP(BigInteger p) {
        this.p = p;
    }

    public BigInteger getX() {
        return x;
    }

    public void setX(BigInteger x) {
        this.x = x;
    }

    public BigInteger getY() {
        return y;
    }

    public void setY(BigInteger y) {
        this.y = y;
    }

    public BigInteger getQ() {
        return q;
    }

    public void setQ(BigInteger q) {
        this.q = q;
    }

//    public BigInteger a();
//
//    public BigInteger b();
//
//    public BigInteger p();
//
//    /** returns the x value of the generator*/
//    public BigInteger generatorX();
//
//    /** returns the y value of the generator*/
//    public BigInteger generatorY();
//
//    public BigInteger order();
//
//    public String toString();
}
