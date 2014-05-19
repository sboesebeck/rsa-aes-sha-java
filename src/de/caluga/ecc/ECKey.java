package de.caluga.ecc;

import de.caluga.rsa.BigInteger;

import java.io.*;

public class ECKey {
    /**
     * There are to kinds of keys secret and public
     */
    protected boolean secret;
    protected BigInteger sk;
    protected ECPoint beta;
    protected EllipticCurve mother;

    /**
     * ECKey generates a random secret key (contains also the public key)
     */
    public ECKey(EllipticCurve ec) {
        mother = ec;
        secret = true;
        sk = new BigInteger(ec.getp().bitLength() + 17, Rand.om);
        if (mother.getOrder() != null) sk = sk.mod(mother.getOrder());
        beta = (mother.getGenerator()).multiply(sk);
        beta.fastCache();
    }

    public String toString() {
        if (secret) return ("Secret key: " + sk + " " + beta + " " + mother);
        else return ("Public key:" + beta + " " + mother);
    }

    public boolean isPublic() {
        return (!secret);
    }

    public void writeKey(OutputStream out) throws IOException {
        DataOutputStream output = new DataOutputStream(out);
        mother.writeCurve(output);
        output.writeBoolean(secret);
        if (secret) {
            byte[] skb = sk.toByteArray();
            output.writeInt(skb.length);
            output.write(skb);
        }
        byte[] betab = beta.compress();
        output.writeInt(betab.length);
        output.write(betab);
    }

    public ECKey readKey(InputStream in) throws IOException {
        DataInputStream input = new DataInputStream(in);
        ECKey k = new ECKey(new EllipticCurve(input));
        k.secret = input.readBoolean();
        if (k.secret) {
            byte[] skb = new byte[input.readInt()];
            input.read(skb);
            k.sk = new BigInteger(skb);
        }
        byte[] betab = new byte[input.readInt()];
        input.read(betab);
        k.beta = new ECPoint(betab, k.mother);
        return k;
    }

    /**
     * Turns this key into a public key (does nothing if this key is public)
     */
    public ECKey getPublic() {
        ECKey temp = new ECKey(mother);
        ((ECKey) temp).beta = beta;
        ((ECKey) temp).sk = new BigInteger(0);
        ((ECKey) temp).secret = false;
        System.gc();
        return temp;
    }
}
