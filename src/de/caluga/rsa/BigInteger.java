package de.caluga.rsa;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

/**
 * User: Stephan Bösebeck
 * Date: 24.09.13
 * Time: 22:21
 * <p/>
 * TODO: Add documentation here
 */
public class BigInteger {


    /* Rounding modes: */
    private static final int FLOOR = 1;
    private static final int CEILING = 2;
    private static final int TRUNCATE = 3;
    private static final int ROUND = 4;
    /**
     * When checking the probability of primes, it is most efficient to
     * first check the factoring of small primes, so we'll use this array.
     */
    private static final int[] primes =
            {2, 3, 5, 7, 11, 13, 17, 19, 23, 29, 31, 37, 41, 43,
                    47, 53, 59, 61, 67, 71, 73, 79, 83, 89, 97, 101, 103, 107,
                    109, 113, 127, 131, 137, 139, 149, 151, 157, 163, 167, 173, 179, 181,
                    191, 193, 197, 199, 211, 223, 227, 229, 233, 239, 241, 251, 257, 263, 269, 271, 277, 281,
            /*Primes*/       283, 293, 307, 311, 313, 317, 331, 337, 347, 349,
/*Primes*/       353, 359, 367, 373, 379, 383, 389, 397, 401, 409,
/*Primes*/       419, 421, 431, 433, 439, 443, 449, 457, 461, 463,
/*Primes*/       467, 479, 487, 491, 499, 503, 509, 521, 523, 541,
/*Primes*/       547, 557, 563, 569, 571, 577, 587, 593, 599, 601,
/*Primes*/       607, 613, 617, 619, 631, 641, 643, 647, 653, 659,
/*Primes*/       661, 673, 677, 683, 691, 701, 709, 719, 727, 733,
/*Primes*/       739, 743, 751, 757, 761, 769, 773, 787, 797, 809,
/*Primes*/       811, 821, 823, 827, 829, 839, 853, 857, 859, 863,
/*Primes*/       877, 881, 883, 887, 907, 911, 919, 929, 937, 941,
/*Primes*/       947, 953, 967, 971, 977, 983, 991, 997, 1009, 1013,
/*Primes*/       1019, 1021, 1031, 1033, 1039, 1049, 1051, 1061, 1063, 1069,
/*Primes*/       1087, 1091, 1093, 1097, 1103, 1109, 1117, 1123, 1129, 1151,
/*Primes*/       1153, 1163, 1171, 1181, 1187, 1193, 1201, 1213, 1217, 1223,
/*Primes*/       1229, 1231, 1237, 1249, 1259, 1277, 1279, 1283, 1289, 1291,
/*Primes*/       1297, 1301, 1303, 1307, 1319, 1321, 1327, 1361, 1367, 1373,
/*Primes*/       1381, 1399, 1409, 1423, 1427, 1429, 1433, 1439, 1447, 1451,
/*Primes*/       1453, 1459, 1471, 1481, 1483, 1487, 1489, 1493, 1499, 1511,
/*Primes*/       1523, 1531, 1543, 1549, 1553, 1559, 1567, 1571, 1579, 1583,
/*Primes*/       1597, 1601, 1607, 1609, 1613, 1619, 1621, 1627, 1637, 1657,
/*Primes*/       1663, 1667, 1669, 1693, 1697, 1699, 1709, 1721, 1723, 1733,
/*Primes*/       1741, 1747, 1753, 1759, 1777, 1783, 1787, 1789, 1801, 1811,
/*Primes*/       1823, 1831, 1847, 1861, 1867, 1871, 1873, 1877, 1879, 1889,
/*Primes*/       1901, 1907, 1913, 1931, 1933, 1949, 1951, 1973, 1979, 1987,
/*Primes*/       1993, 1997, 1999, 2003, 2011, 2017, 2027, 2029, 2039, 2053,
/*Primes*/       2063, 2069, 2081, 2083, 2087, 2089, 2099, 2111, 2113, 2129,
/*Primes*/       2131, 2137, 2141, 2143, 2153, 2161, 2179, 2203, 2207, 2213,
/*Primes*/       2221, 2237, 2239, 2243, 2251, 2267, 2269, 2273, 2281, 2287,
/*Primes*/       2293, 2297, 2309, 2311, 2333, 2339, 2341, 2347, 2351, 2357,
/*Primes*/       2371, 2377, 2381, 2383, 2389, 2393, 2399, 2411, 2417, 2423,
/*Primes*/       2437, 2441, 2447, 2459, 2467, 2473, 2477, 2503, 2521, 2531,
/*Primes*/       2539, 2543, 2549, 2551, 2557, 2579, 2591, 2593, 2609, 2617,
/*Primes*/       2621, 2633, 2647, 2657, 2659, 2663, 2671, 2677, 2683, 2687,
/*Primes*/       2689, 2693, 2699, 2707, 2711, 2713, 2719, 2729, 2731, 2741,
/*Primes*/       2749, 2753, 2767, 2777, 2789, 2791, 2797, 2801, 2803, 2819,
/*Primes*/       2833, 2837, 2843, 2851, 2857, 2861, 2879, 2887, 2897, 2903,
/*Primes*/       2909, 2917, 2927, 2939, 2953, 2957, 2963, 2969, 2971, 2999,
/*Primes*/       3001, 3011, 3019, 3023, 3037, 3041, 3049, 3061, 3067, 3079,
/*Primes*/       3083, 3089, 3109, 3119, 3121, 3137, 3163, 3167, 3169, 3181,
/*Primes*/       3187, 3191, 3203, 3209, 3217, 3221, 3229, 3251, 3253, 3257,
/*Primes*/       3259, 3271, 3299, 3301, 3307, 3313, 3319, 3323, 3329, 3331,
/*Primes*/       3343, 3347, 3359, 3361, 3371, 3373, 3389, 3391, 3407, 3413,
/*Primes*/       3433, 3449, 3457, 3461, 3463, 3467, 3469, 3491, 3499, 3511,
/*Primes*/       3517, 3527, 3529, 3533, 3539, 3541, 3547, 3557, 3559, 3571,
/*Primes*/       3581, 3583, 3593, 3607, 3613, 3617, 3623, 3631, 3637, 3643,
/*Primes*/       3659, 3671, 3673, 3677, 3691, 3697, 3701, 3709, 3719, 3727,
/*Primes*/       3733, 3739, 3761, 3767, 3769, 3779, 3793, 3797, 3803, 3821,
/*Primes*/       3823, 3833, 3847, 3851, 3853, 3863, 3877, 3881, 3889, 3907,
/*Primes*/       3911, 3917, 3919, 3923, 3929, 3931, 3943, 3947, 3967, 3989,
/*Primes*/       4001, 4003, 4007, 4013, 4019, 4021, 4027, 4049, 4051, 4057,
/*Primes*/       4073, 4079, 4091, 4093, 4099, 4111, 4127, 4129, 4133, 4139,
/*Primes*/       4153, 4157, 4159, 4177, 4201, 4211, 4217, 4219, 4229, 4231,
/*Primes*/       4241, 4243, 4253, 4259, 4261, 4271, 4273, 4283, 4289, 4297,
/*Primes*/       4327, 4337, 4339, 4349, 4357, 4363, 4373, 4391, 4397, 4409,
/*Primes*/       4421, 4423, 4441, 4447, 4451, 4457, 4463, 4481, 4483, 4493,
/*Primes*/       4507, 4513, 4517, 4519, 4523, 4547, 4549, 4561, 4567, 4583,
/*Primes*/       4591, 4597, 4603, 4621, 4637, 4639, 4643, 4649, 4651, 4657,
/*Primes*/       4663, 4673, 4679, 4691, 4703, 4721, 4723, 4729, 4733, 4751,
/*Primes*/       4759, 4783, 4787, 4789, 4793, 4799, 4801, 4813, 4817, 4831,
/*Primes*/       4861, 4871, 4877, 4889, 4903, 4909, 4919, 4931, 4933, 4937,
/*Primes*/       4943, 4951, 4957, 4967, 4969, 4973, 4987, 4993, 4999, 5003,
/*Primes*/       5009, 5011, 5021, 5023, 5039, 5051, 5059, 5077, 5081, 5087,
/*Primes*/       5099, 5101, 5107, 5113, 5119, 5147, 5153, 5167, 5171, 5179,
/*Primes*/       5189, 5197, 5209, 5227, 5231, 5233, 5237, 5261, 5273, 5279,
/*Primes*/       5281, 5297, 5303, 5309, 5323, 5333, 5347, 5351, 5381, 5387,
/*Primes*/       5393, 5399, 5407, 5413, 5417, 5419, 5431, 5437, 5441, 5443,
/*Primes*/       5449, 5471, 5477, 5479, 5483, 5501, 5503, 5507, 5519, 5521};
    /**
     * HAC (Handbook of Applied Cryptography), Alfred Menezes & al. Table 4.4.
     */
    private static final int[] k =
            {100, 150, 200, 250, 300, 350, 400, 500, 600, 800, 1250, Integer.MAX_VALUE};
    private static final int[] t =
            {27, 18, 15, 12, 9, 8, 7, 6, 5, 4, 3, 2};
    // bit4count[I] is number of '1' bits in I.
    private static final byte[] bit4_count = {0, 1, 1, 2, 1, 2, 2, 3,
            1, 2, 2, 3, 2, 3, 3, 4};
    /**
     * All integers are stored in 2's-complement form.
     * If words == null, the ival is the value of this BigInteger.
     * Otherwise, the first ival elements of words make the value
     * of this BigInteger, stored in little-endian order, 2's-complement form.
     */
    private transient int ival;
    private transient int[] words;

    private BigInteger() {
    }

    public BigInteger(int value) {
        ival = value;
        words = null;
    }

    public BigInteger(String val, int radix) {
        BigInteger result = valueOf(val, radix);
        this.ival = result.ival;
        this.words = result.words;
    }

    public BigInteger(String val) {
        this(val, 10);
    }

    /* Create a new (non-shared) BigInteger, and initialize from a byte array. */
    public BigInteger(byte[] val) {
        if (val == null || val.length < 1)
            throw new NumberFormatException();

        words = byteArrayToIntArray(val, val[0] < 0 ? -1 : 0);
        BigInteger result = make(words, words.length);
        this.ival = result.ival;
        this.words = result.words;
    }

    public BigInteger(int signum, byte[] magnitude) {
        if (magnitude == null || signum > 1 || signum < -1)
            throw new NumberFormatException();

        if (signum == 0) {
            int i;
            for (i = magnitude.length - 1; i >= 0 && magnitude[i] == 0; --i)
                ;
            if (i >= 0)
                throw new NumberFormatException();
            return;
        }

        // Magnitude is always positive, so don't ever pass a sign of -1.
        words = byteArrayToIntArray(magnitude, 0);
        BigInteger result = make(words, words.length);
        this.ival = result.ival;
        this.words = result.words;

        if (signum < 0)
            setNegative();
    }

    public BigInteger(int numBits, Random rnd) {
        if (numBits < 0)
            throw new IllegalArgumentException();

        init(numBits, rnd);
    }

    public BigInteger(int bitLength, int certainty, Random rnd) {
        this(bitLength, rnd);
        int tries = 0;
        // Keep going until we find a probable prime.
        BigInteger result;
        while (true) {
            tries++;
            // ...but first ensure that BI has bitLength bits
            result = setBit(bitLength - 1);
            this.ival = result.ival;
            this.words = result.words;
            if (isProbablePrime(certainty)) {
//                System.out.println("Tries for prime: " + tries);
                return;
            }

            init(bitLength, rnd);
        }
    }

    public BigInteger(int[] words, int iVal) {
        this.words = words;
        this.ival = iVal;
    }

    /**
     * Return a BigInteger that is bitLength bits long with a
     * probability < 2^-100 of being composite.
     *
     * @param bitLength length in bits of resulting number
     * @param rnd       random number generator to use
     * @throws ArithmeticException if bitLength < 2
     * @since 1.4
     */
    public static BigInteger probablePrime(int bitLength, Random rnd) {
        if (bitLength < 2)
            throw new ArithmeticException();

        return new BigInteger(bitLength, 100, rnd);
    }

    /**
     * Return a (possibly-shared) BigInteger with a given long value.
     */
    public static BigInteger valueOf(long val) {
        int i = (int) val;
        if ((long) i == val)
            return new BigInteger(i);
        BigInteger result = alloc(2);
        result.ival = 2;
        result.words[0] = i;
        result.words[1] = (int) (val >> 32);
        return result;
    }

    /**
     * Make a canonicalized BigInteger from an array of words.
     * The array may be reused (without copying).
     */
    private static BigInteger make(int[] words, int len) {
        if (words == null)
            return valueOf(len);
        len = BigInteger.wordsNeeded(words, len);
        if (len <= 1)
            return len == 0 ? new BigInteger(0) : valueOf(words[0]);
        BigInteger num = new BigInteger();
        num.words = words;
        num.ival = len;
        return num;
    }

    /**
     * Convert a big-endian byte array to a little-endian array of words.
     */
    private static int[] byteArrayToIntArray(byte[] bytes, int sign) {
        // Determine number of words needed.
        int[] words = new int[bytes.length / 4 + 1];
        int nwords = words.length;

        // Create a int out of modulo 4 high order serialize.
        int bptr = 0;
        int word = sign;
        for (int i = bytes.length % 4; i > 0; --i, bptr++)
            word = (word << 8) | (bytes[bptr] & 0xff);
        words[--nwords] = word;

        // Elements remaining in byte[] are a multiple of 4.
        while (nwords > 0)
            words[--nwords] = bytes[bptr++] << 24 |
                    (bytes[bptr++] & 0xff) << 16 |
                    (bytes[bptr++] & 0xff) << 8 |
                    (bytes[bptr++] & 0xff);
        return words;
    }

    /**
     * Allocate a new non-shared BigInteger.
     *
     * @param nwords number of words to allocate
     */
    private static BigInteger alloc(int nwords) {
        BigInteger result = new BigInteger();
        if (nwords > 1)
            result.words = new int[nwords];
        return result;
    }

    private static int compareTo(BigInteger x, BigInteger y) {
        if (x.words == null && y.words == null)
            return x.ival < y.ival ? -1 : x.ival > y.ival ? 1 : 0;
        boolean x_negative = x.isNegative();
        boolean y_negative = y.isNegative();
        if (x_negative != y_negative)
            return x_negative ? -1 : 1;
        int x_len = x.words == null ? 1 : x.ival;
        int y_len = y.words == null ? 1 : y.ival;
        if (x_len != y_len)
            return (x_len > y_len) != x_negative ? 1 : -1;
        return MPN.cmp(x.words, y.words, x_len);
    }

    /**
     * Calculate how many words are significant in words[0:len-1].
     * Returns the least value x such that x>0 && words[0:x-1]==words[0:len-1],
     * when words is viewed as a 2's complement integer.
     */
    private static int wordsNeeded(int[] words, int len) {
        int i = len;
        if (i > 0) {
            int word = words[--i];
            if (word == -1) {
                while (i > 0 && (word = words[i - 1]) < 0) {
                    i--;
                    if (word != -1) break;
                }
            } else {
                while (word == 0 && i > 0 && (word = words[i - 1]) >= 0) i--;
            }
        }
        return i + 1;
    }

    /**
     * Add two ints, yielding a BigInteger.
     */
    private static BigInteger add(int x, int y) {
        return valueOf((long) x + (long) y);
    }

    /**
     * Add a BigInteger and an int, yielding a new BigInteger.
     */
    private static BigInteger add(BigInteger x, int y) {
        if (x.words == null)
            return BigInteger.add(x.ival, y);
        BigInteger result = new BigInteger(0);
        result.setAdd(x, y);
        return result.canonicalize();
    }

    /**
     * Add two BigIntegers, yielding their sum as another BigInteger.
     */
    public static BigInteger add(BigInteger x, BigInteger y, int k) {
        if (x.words == null && y.words == null)
            return valueOf((long) k * (long) y.ival + (long) x.ival);
        if (k != 1) {
            if (k == -1)
                y = BigInteger.neg(y);
            else
                y = BigInteger.times(y, valueOf(k));
        }
        if (x.words == null)
            return BigInteger.add(y, x.ival);
        if (y.words == null)
            return BigInteger.add(x, y.ival);
        // Both are big
        if (y.ival > x.ival) { // Swap so x is longer then y.
            BigInteger tmp = x;
            x = y;
            y = tmp;
        }
        BigInteger result = alloc(x.ival + 1);
        int i = y.ival;
        long carry = MPN.add_n(result.words, x.words, y.words, i);
        long y_ext = y.words[i - 1] < 0 ? 0xffffffffL : 0;
        for (; i < x.ival; i++) {
            carry += ((long) x.words[i] & 0xffffffffL) + y_ext;
            result.words[i] = (int) carry;
            carry >>>= 32;
        }
        if (x.words[i - 1] < 0)
            y_ext--;
        result.words[i] = (int) (carry + y_ext);
        result.ival = i + 1;
        return result.canonicalize();
    }

    private static BigInteger times(BigInteger x, int y) {
        if (y == 0)
            return new BigInteger(0);
        if (y == 1)
            return x;
        int[] xwords = x.words;
        int xlen = x.ival;
        if (xwords == null)
            return valueOf((long) xlen * (long) y);
        boolean negative;
        BigInteger result = BigInteger.alloc(xlen + 1);
        if (xwords[xlen - 1] < 0) {
            negative = true;
            negate(result.words, xwords, xlen);
            xwords = result.words;
        } else
            negative = false;
        if (y < 0) {
            negative = !negative;
            y = -y;
        }
        result.words[xlen] = MPN.mul_1(result.words, xwords, xlen, y);
        result.ival = xlen + 1;
        if (negative)
            result.setNegative();
        return result.canonicalize();
    }

    public static BigInteger times(BigInteger x, BigInteger y) {
        if (y.words == null)
            return times(x, y.ival);
        if (x.words == null)
            return times(y, x.ival);
        boolean negative = false;
        int[] xwords;
        int[] ywords;
        int xlen = x.ival;
        int ylen = y.ival;
        if (x.isNegative()) {
            negative = true;
            xwords = new int[xlen];
            negate(xwords, x.words, xlen);
        } else {
            negative = false;
            xwords = x.words;
        }
        if (y.isNegative()) {
            negative = !negative;
            ywords = new int[ylen];
            negate(ywords, y.words, ylen);
        } else
            ywords = y.words;
        // Swap if x is shorter then y.
        if (xlen < ylen) {
            int[] twords = xwords;
            xwords = ywords;
            ywords = twords;
            int tlen = xlen;
            xlen = ylen;
            ylen = tlen;
        }
        BigInteger result = BigInteger.alloc(xlen + ylen);
        MPN.mul(result.words, xwords, xlen, ywords, ylen);
        result.ival = xlen + ylen;
        if (negative)
            result.setNegative();
        return result.canonicalize();
    }

    private static void divide(long x, long y,
                               BigInteger quotient, BigInteger remainder,
                               int rounding_mode) {
        boolean xNegative, yNegative;
        if (x < 0) {
            xNegative = true;
            if (x == Long.MIN_VALUE) {
                divide(valueOf(x), valueOf(y),
                        quotient, remainder, rounding_mode);
                return;
            }
            x = -x;
        } else
            xNegative = false;

        if (y < 0) {
            yNegative = true;
            if (y == Long.MIN_VALUE) {
                if (rounding_mode == TRUNCATE) { // x != Long.Min_VALUE implies abs(x) < abs(y)
                    if (quotient != null)
                        quotient.set(0);
                    if (remainder != null)
                        remainder.set(x);
                } else
                    divide(valueOf(x), valueOf(y),
                            quotient, remainder, rounding_mode);
                return;
            }
            y = -y;
        } else
            yNegative = false;

        long q = x / y;
        long r = x % y;
        boolean qNegative = xNegative ^ yNegative;

        boolean add_one = false;
        if (r != 0) {
            switch (rounding_mode) {
                case TRUNCATE:
                    break;
                case CEILING:
                case FLOOR:
                    if (qNegative == (rounding_mode == FLOOR))
                        add_one = true;
                    break;
                case ROUND:
                    add_one = r > ((y - (q & 1)) >> 1);
                    break;
            }
        }
        if (quotient != null) {
            if (add_one)
                q++;
            if (qNegative)
                q = -q;
            quotient.set(q);
        }
        if (remainder != null) {
            // The remainder is by definition: X-Q*Y
            if (add_one) {
                // Subtract the remainder from Y.
                r = y - r;
                // In this case, abs(Q*Y) > abs(X).
                // So sign(remainder) = -sign(X).
                xNegative = !xNegative;
            } else {
                // If !add_one, then: abs(Q*Y) <= abs(X).
                // So sign(remainder) = sign(X).
            }
            if (xNegative)
                r = -r;
            remainder.set(r);
        }
    }

    /**
     * Divide two integers, yielding quotient and remainder.
     *
     * @param x             the numerator in the division
     * @param y             the denominator in the division
     * @param quotient      is set to the quotient of the result (iff quotient!=null)
     * @param remainder     is set to the remainder of the result
     *                      (iff remainder!=null)
     * @param rounding_mode one of FLOOR, CEILING, TRUNCATE, or ROUND.
     */
    public static void divide(BigInteger x, BigInteger y,
                              BigInteger quotient, BigInteger remainder,
                              int rounding_mode) {
        if ((x.words == null || x.ival <= 2)
                && (y.words == null || y.ival <= 2)) {
            long x_l = x.longValue();
            long y_l = y.longValue();
            if (x_l != Long.MIN_VALUE && y_l != Long.MIN_VALUE) {
                divide(x_l, y_l, quotient, remainder, rounding_mode);
                return;
            }
        }

        boolean xNegative = x.isNegative();
        boolean yNegative = y.isNegative();
        boolean qNegative = xNegative ^ yNegative;

        int ylen = y.words == null ? 1 : y.ival;
        int[] ywords = new int[ylen];
        y.getAbsolute(ywords);
        while (ylen > 1 && ywords[ylen - 1] == 0) ylen--;

        BigInteger ydebug = new BigInteger(ywords, ylen);

        int xlen = x.words == null ? 1 : x.ival;
        int[] xwords = new int[xlen + 2];
        x.getAbsolute(xwords);
        while (xlen > 1 && xwords[xlen - 1] == 0) xlen--;

        int qlen, rlen;

        int cmpval = MPN.cmp(xwords, xlen, ywords, ylen);
        if (cmpval < 0)  // abs(x) < abs(y)
        { // quotient = 0;  remainder = num.
            int[] rwords = xwords;
            xwords = ywords;
            ywords = rwords;
            rlen = xlen;
            qlen = 1;
            xwords[0] = 0;
        } else if (cmpval == 0)  // abs(x) == abs(y)
        {
            xwords[0] = 1;
            qlen = 1;  // quotient = 1
            ywords[0] = 0;
            rlen = 1;  // remainder = 0;
        } else if (ylen == 1) {
            qlen = xlen;
            // Need to leave room for a word of leading zeros if dividing by 1
            // and the dividend has the high bit set.  It might be safe to
            // increment qlen in all cases, but it certainly is only necessary
            // in the following case.
            if (ywords[0] == 1 && xwords[xlen - 1] < 0)
                qlen++;
            rlen = 1;
            ywords[0] = MPN.divmod_1(xwords, xwords, xlen, ywords[0]);
        } else  // abs(x) > abs(y)
        {
            // Normalize the denominator, i.e. make its most significant bit set by
            // shifting it normalization_steps bits to the left.  Also shift the
            // numerator the same number of steps (to keep the quotient the same!).

            int nshift = MPN.count_leading_zeros(ywords[ylen - 1]);
            if (nshift != 0) {
                // Shift up the denominator setting the most significant bit of
                // the most significant word.
                MPN.lshift(ywords, 0, ywords, ylen, nshift);

                // Shift up the numerator, possibly introducing a new most
                // significant word.
                int x_high = MPN.lshift(xwords, 0, xwords, xlen, nshift);
                xwords[xlen++] = x_high;
            }

            if (xlen == ylen)
                xwords[xlen++] = 0;
            MPN.divide(xwords, xlen, ywords, ylen);
            rlen = ylen;
            MPN.rshift0(ywords, xwords, 0, rlen, nshift);

            qlen = xlen + 1 - ylen;
            if (quotient != null) {
                for (int i = 0; i < qlen; i++)
                    xwords[i] = xwords[i + ylen];
            }
        }

        if (ywords[rlen - 1] < 0) {
            ywords[rlen] = 0;
            rlen++;
        }

        // Now the quotient is in xwords, and the remainder is in ywords.

        boolean add_one = false;
        if (rlen > 1 || ywords[0] != 0) { // Non-zero remainder i.e. in-exact quotient.
            switch (rounding_mode) {
                case TRUNCATE:
                    break;
                case CEILING:
                case FLOOR:
                    if (qNegative == (rounding_mode == FLOOR))
                        add_one = true;
                    break;
                case ROUND:
                    // int cmp = compareTo(remainder<<1, abs(y));
                    BigInteger tmp = remainder == null ? new BigInteger() : remainder;
                    tmp.set(ywords, rlen);
                    tmp = shift(tmp, 1);
                    if (yNegative)
                        tmp.setNegative();
                    int cmp = compareTo(tmp, y);
                    // Now cmp == compareTo(sign(y)*(remainder<<1), y)
                    if (yNegative)
                        cmp = -cmp;
                    add_one = (cmp == 1) || (cmp == 0 && (xwords[0] & 1) != 0);
            }
        }
        if (quotient != null) {
            quotient.set(xwords, qlen);
            if (qNegative) {
                if (add_one)  // -(quotient + 1) == ~(quotient)
                    quotient.setInvert();
                else
                    quotient.setNegative();
            } else if (add_one)
                quotient.setAdd(1);
        }
        if (remainder != null) {
            // The remainder is by definition: X-Q*Y
            remainder.set(ywords, rlen);
            if (add_one) {
                // Subtract the remainder from Y:
                // abs(R) = abs(Y) - abs(orig_rem) = -(abs(orig_rem) - abs(Y)).
                BigInteger tmp;
                if (y.words == null) {
                    tmp = remainder;
                    tmp.set(yNegative ? ywords[0] + y.ival : ywords[0] - y.ival);
                } else
                    tmp = BigInteger.add(remainder, y, yNegative ? 1 : -1);
                // Now tmp <= 0.
                // In this case, abs(Q) = 1 + floor(abs(X)/abs(Y)).
                // Hence, abs(Q*Y) > abs(X).
                // So sign(remainder) = -sign(X).
                if (xNegative)
                    remainder.setNegative(tmp);
                else
                    remainder.set(tmp);
            } else {
                // If !add_one, then: abs(Q*Y) <= abs(X).
                // So sign(remainder) = sign(X).
                if (xNegative)
                    remainder.setNegative();
            }
        }
    }

    private static int[] euclidInv(int a, int b, int prevDiv) {
        if (b == 0)
            throw new ArithmeticException("not invertible");

        if (b == 1)
            // Success:  values are indeed invertible!
            // Bottom of the recursion reached; start unwinding.
            return new int[]{-prevDiv, 1};

        int[] xy = euclidInv(b, a % b, a / b);    // Recursion happens here.
        a = xy[0]; // use our local copy of 'a' as a work var
        xy[0] = a * -prevDiv + xy[1];
        xy[1] = a;
        return xy;
    }

    public static void euclidInv(BigInteger a, BigInteger b,
                                 BigInteger prevDiv, BigInteger[] xy) {
        if (b.isZero())
            throw new ArithmeticException("not invertible");

        if (b.isOne()) {
            // Success:  values are indeed invertible!
            // Bottom of the recursion reached; start unwinding.
            xy[0] = neg(prevDiv);
            xy[1] = new BigInteger(1);
            return;
        }

        // Recursion happens in the following conditional!

        // If a just contains an int, then use integer math for the rest.
        if (a.words == null) {
            int[] xyInt = euclidInv(b.ival, a.ival % b.ival, a.ival / b.ival);
            xy[0] = new BigInteger(xyInt[0]);
            xy[1] = new BigInteger(xyInt[1]);
        } else {
            BigInteger rem = new BigInteger();
            BigInteger quot = new BigInteger();
            if (a.toString(16).toUpperCase().equals("4A0ED6F3EEA410A05")) {
                System.out.println("Found it!");
            }
            divide(a, b, quot, rem, FLOOR);
//            System.out.println("A:" + a + " / b:" + b + " = " + quot + " rem: " + rem + " iVal:" + rem.getIval());
            // quot and rem may not be in canonical form. ensure
            rem.canonicalize();
            quot.canonicalize();
            euclidInv(b, rem, quot, xy);
        }

        BigInteger t = xy[0];
        xy[0] = add(xy[1], times(t, prevDiv), -1);
        xy[1] = t;
    }

    /**
     * Calculate Greatest Common Divisor for non-negative ints.
     */
    private static int gcd(int a, int b) {
        // Euclid's algorithm, copied from libg++.
        int tmp;
        if (b > a) {
            tmp = a;
            a = b;
            b = tmp;
        }
        for (; ; ) {
            if (b == 0)
                return a;
            if (b == 1)
                return b;
            tmp = b;
            b = a % b;
            a = tmp;
        }
    }

    private static BigInteger shift(BigInteger x, int count) {
        if (x.words == null) {
            if (count <= 0)
                return valueOf(count > -32 ? x.ival >> (-count) : x.ival < 0 ? -1 : 0);
            if (count < 32)
                return valueOf((long) x.ival << count);
        }
        if (count == 0)
            return x;
        BigInteger result = new BigInteger(0);
        result.setShift(x, count);
        return result.canonicalize();
    }

    /* Assumes x and y are both canonicalized. */
    private static boolean equals(BigInteger x, BigInteger y) {
        if (x.words == null && y.words == null)
            return x.ival == y.ival;
        if (x.words == null || y.words == null || x.ival != y.ival)
            return false;
        for (int i = x.ival; --i >= 0; ) {
            if (x.words[i] != y.words[i])
                return false;
        }
        return true;
    }

    private static BigInteger valueOf(String s, int radix)
            throws NumberFormatException {
        int len = s.length();
        // Testing (len < MPN.chars_per_word(radix)) would be more accurate,
        // but slightly more expensive, for little practical gain.
        if (len <= 15 && radix <= 16)
            return valueOf(Long.parseLong(s, radix));

        int i, digit;
        boolean negative;
        byte[] bytes;
        char ch = s.charAt(0);
        if (ch == '-') {
            negative = true;
            i = 1;
            bytes = new byte[len - 1];
        } else {
            negative = false;
            i = 0;
            bytes = new byte[len];
        }
        int byte_len = 0;
        for (; i < len; i++) {
            ch = s.charAt(i);
            digit = Character.digit(ch, radix);
            if (digit < 0)
                throw new NumberFormatException();
            bytes[byte_len++] = (byte) digit;
        }
        return valueOf(bytes, byte_len, negative, radix);
    }

    private static BigInteger valueOf(byte[] digits, int byte_len,
                                      boolean negative, int radix) {
        int chars_per_word = MPN.chars_per_word(radix);
        int[] words = new int[byte_len / chars_per_word + 1];
        int size = MPN.set_str(words, digits, byte_len, radix);
        if (size == 0)
            return new BigInteger(0);
        if (words[size - 1] < 0)
            words[size++] = 0;
        if (negative)
            negate(words, words, size);
        return make(words, size);
    }

    /**
     * Set dest[0:len-1] to the negation of src[0:len-1].
     * Return true if overflow (i.e. if src is -2**(32*len-1)).
     * Ok for src==dest.
     */
    private static boolean negate(int[] dest, int[] src, int len) {
        long carry = 1;
        boolean negative = src[len - 1] < 0;
        for (int i = 0; i < len; i++) {
            carry += ((long) (~src[i]) & 0xffffffffL);
            dest[i] = (int) carry;
            carry >>= 32;
        }
        return (negative && dest[len - 1] < 0);
    }

    private static BigInteger abs(BigInteger x) {
        return x.isNegative() ? neg(x) : x;
    }

    private static BigInteger neg(BigInteger x) {
        if (x.words == null && x.ival != Integer.MIN_VALUE)
            return valueOf(-x.ival);
        BigInteger result = new BigInteger(0);
        result.setNegative(x);
        return result.canonicalize();
    }

    /**
     * Return the boolean opcode (for bitOp) for swapped operands.
     * I.e. bitOp(swappedOp(op), x, y) == bitOp(op, y, x).
     */
    private static int swappedOp(int op) {
        return
                "\000\001\004\005\002\003\006\007\010\011\014\015\012\013\016\017"
                        .charAt(op);
    }

    /**
     * Do one the the 16 possible bit-wise operations of two BigIntegers.
     */
    private static BigInteger bitOp(int op, BigInteger x, BigInteger y) {
        switch (op) {
            case 0:
                return new BigInteger(0);
            case 1:
                return x.and(y);
            case 3:
                return x;
            case 5:
                return y;
            case 15:
                return valueOf(-1);
        }
        BigInteger result = new BigInteger();
        setBitOp(result, op, x, y);
        return result.canonicalize();
    }

    /**
     * Do one the the 16 possible bit-wise operations of two BigIntegers.
     */
    private static void setBitOp(BigInteger result, int op,
                                 BigInteger x, BigInteger y) {
        if ((y.words != null) && (x.words == null || x.ival < y.ival)) {
            BigInteger temp = x;
            x = y;
            y = temp;
            op = swappedOp(op);
        }
        int xi;
        int yi;
        int xlen, ylen;
        if (y.words == null) {
            yi = y.ival;
            ylen = 1;
        } else {
            yi = y.words[0];
            ylen = y.ival;
        }
        if (x.words == null) {
            xi = x.ival;
            xlen = 1;
        } else {
            xi = x.words[0];
            xlen = x.ival;
        }
        if (xlen > 1)
            result.realloc(xlen);
        int[] w = result.words;
        int i = 0;
        // Code for how to handle the remainder of x.
        // 0:  Truncate to length of y.
        // 1:  Copy rest of x.
        // 2:  Invert rest of x.
        int finish = 0;
        int ni;
        switch (op) {
            case 0:  // clr
                ni = 0;
                break;
            case 1: // and
                for (; ; ) {
                    ni = xi & yi;
                    if (i + 1 >= ylen) break;
                    w[i++] = ni;
                    xi = x.words[i];
                    yi = y.words[i];
                }
                if (yi < 0) finish = 1;
                break;
            case 2: // andc2
                for (; ; ) {
                    ni = xi & ~yi;
                    if (i + 1 >= ylen) break;
                    w[i++] = ni;
                    xi = x.words[i];
                    yi = y.words[i];
                }
                if (yi >= 0) finish = 1;
                break;
            case 3:  // copy x
                ni = xi;
                finish = 1;  // Copy rest
                break;
            case 4: // andc1
                for (; ; ) {
                    ni = ~xi & yi;
                    if (i + 1 >= ylen) break;
                    w[i++] = ni;
                    xi = x.words[i];
                    yi = y.words[i];
                }
                if (yi < 0) finish = 2;
                break;
            case 5: // copy y
                for (; ; ) {
                    ni = yi;
                    if (i + 1 >= ylen) break;
                    w[i++] = ni;
                    xi = x.words[i];
                    yi = y.words[i];
                }
                break;
            case 6:  // xor
                for (; ; ) {
                    ni = xi ^ yi;
                    if (i + 1 >= ylen) break;
                    w[i++] = ni;
                    xi = x.words[i];
                    yi = y.words[i];
                }
                finish = yi < 0 ? 2 : 1;
                break;
            case 7:  // ior
                for (; ; ) {
                    ni = xi | yi;
                    if (i + 1 >= ylen) break;
                    w[i++] = ni;
                    xi = x.words[i];
                    yi = y.words[i];
                }
                if (yi >= 0) finish = 1;
                break;
            case 8:  // nor
                for (; ; ) {
                    ni = ~(xi | yi);
                    if (i + 1 >= ylen) break;
                    w[i++] = ni;
                    xi = x.words[i];
                    yi = y.words[i];
                }
                if (yi >= 0) finish = 2;
                break;
            case 9:  // eqv [exclusive nor]
                for (; ; ) {
                    ni = ~(xi ^ yi);
                    if (i + 1 >= ylen) break;
                    w[i++] = ni;
                    xi = x.words[i];
                    yi = y.words[i];
                }
                finish = yi >= 0 ? 2 : 1;
                break;
            case 10:  // c2
                for (; ; ) {
                    ni = ~yi;
                    if (i + 1 >= ylen) break;
                    w[i++] = ni;
                    xi = x.words[i];
                    yi = y.words[i];
                }
                break;
            case 11:  // orc2
                for (; ; ) {
                    ni = xi | ~yi;
                    if (i + 1 >= ylen) break;
                    w[i++] = ni;
                    xi = x.words[i];
                    yi = y.words[i];
                }
                if (yi < 0) finish = 1;
                break;
            case 12:  // c1
                ni = ~xi;
                finish = 2;
                break;
            case 13:  // orc1
                for (; ; ) {
                    ni = ~xi | yi;
                    if (i + 1 >= ylen) break;
                    w[i++] = ni;
                    xi = x.words[i];
                    yi = y.words[i];
                }
                if (yi >= 0) finish = 2;
                break;
            case 14:  // nand
                for (; ; ) {
                    ni = ~(xi & yi);
                    if (i + 1 >= ylen) break;
                    w[i++] = ni;
                    xi = x.words[i];
                    yi = y.words[i];
                }
                if (yi < 0) finish = 2;
                break;
            default:
            case 15:  // set
                ni = -1;
                break;
        }
        // Here i==ylen-1; w[0]..w[i-1] have the correct result;
        // and ni contains the correct result for w[i+1].
        if (i + 1 == xlen)
            finish = 0;
        switch (finish) {
            case 0:
                if (i == 0 && w == null) {
                    result.ival = ni;
                    return;
                }
                w[i++] = ni;
                break;
            case 1:
                w[i] = ni;
                while (++i < xlen) w[i] = x.words[i];
                break;
            case 2:
                w[i] = ni;
                while (++i < xlen) w[i] = ~x.words[i];
                break;
        }
        result.ival = i;
    }

    /**
     * Return the logical (bit-wise) "and" of a BigInteger and an int.
     */
    private static BigInteger and(BigInteger x, int y) {
        if (x.words == null)
            return valueOf(x.ival & y);
        if (y >= 0)
            return valueOf(x.words[0] & y);
        int len = x.ival;
        int[] words = new int[len];
        words[0] = x.words[0] & y;
        while (--len > 0)
            words[len] = x.words[len];
        return make(words, x.ival);
    }

    private static int bitCount(int i) {
        int count = 0;
        while (i != 0) {
            count += bit4_count[i & 15];
            i >>>= 4;
        }
        return count;
    }

    private static int bitCount(int[] x, int len) {
        int count = 0;
        while (--len >= 0)
            count += bitCount(x[len]);
        return count;
    }

    private static int getIntFrom(byte[] buffer, int idx) {
        long v = 0;
        v |= ((long) buffer[idx + 0] & 0xff) << 24;
        v |= ((long) buffer[idx + 1] & 0xff) << 16;
        v |= ((long) buffer[idx + 2] & 0xff) << 8;
        v |= ((long) buffer[idx + 3] & 0xff);

        return (int) (v & 0xffffffff);
    }

    private static void fillInteger(int v, byte[] buffer) {
        fillInteger(v, buffer, 0);
    }

    private static void fillInteger(int v, byte[] buffer, int position) {
        buffer[0 + position] = (byte) (((v) >>> 24) & 0xff);
        buffer[1 + position] = (byte) (((v) >>> 16) & 0xff);
        buffer[2 + position] = (byte) (((v) >>> 8) & 0xff);
        buffer[3 + position] = (byte) (((v) & 0xff));
    }

    public static List<BigInteger> deSerialize(byte[] data) {
        List<BigInteger> ret = new ArrayList<BigInteger>();
        for (int i = 0; i < data.length; ) {
            BigInteger bi = new BigInteger();
            i = BigInteger.fromBytes(bi, data, i);
            ret.add(bi);
        }
        return ret;
    }

    public static byte[] dataFromBigIntArray(List<BigInteger> lst) {
        return dataFromBigIntArray(lst, true);
    }

    public static byte[] dataFromBigIntArray(List<BigInteger> lst, boolean prefixed) {
        ArrayList<Byte> ret = new ArrayList<Byte>();

        for (int i = 0; i < lst.size(); i++) {
            BigInteger integer = lst.get(i);
            //stepping through integers
            if (integer.words == null || integer.words.length == 0) {
                if (prefixed) {
                    throw new IllegalArgumentException("Cannot create prefixed data from simple bigInteger!");
                }
                boolean skip = true;
                //simple
                long v = integer.ival;
                char val = (char) ((v >> 24) & 0xff);
                if (val != 0 || !skip) {
                    ret.add((byte) (val & 0xff));
                    skip = false;
                }

                val = (char) ((v >> 16) & 0xff);
                if (val != 0 || !skip) {
                    ret.add((byte) (val & 0xff));
                    skip = false;
                }

                val = (char) ((v >> 8) & 0xff);
                if (val != 0 || !skip) {
                    ret.add((byte) (val & 0xff));
                    skip = false;
                }

                val = (char) ((v) & 0xff);
                if (val != 0 || !skip) {
                    ret.add((byte) (val & 0xff));
                    skip = false;
                }
            } else {
                int j = integer.ival - (prefixed ? 2 : 1);
                int skipBytes = 0;
                if (prefixed) {
                    int pr = integer.words[integer.ival - 1];
                    if (pr < (integer.ival - 1) * 4) {
                        skipBytes = (integer.ival - 1) * 4 - pr;
                    }
                }
                int skip = skipBytes;
                for (; j >= 0; j--) {
                    long v = integer.words[j];
                    char val = 0;
                    if (skip > 0) {
                        skip--;
                    } else {
                        val = (char) ((v >> 24) & 0xff);
                        ret.add((byte) (val & 0xff));
                    }

                    if (skip > 0) {
                        skip--;
                    } else {
                        val = (char) ((v >> 16) & 0xff);
                        ret.add((byte) (val & 0xff));
                    }

                    if (skip > 0) {
                        skip--;
                    } else {
                        val = (char) ((v >> 8) & 0xff);
                        ret.add((byte) (val & 0xff));
                    }

                    if (skip > 0) {
                        skip--;
                    } else {
                        val = (char) ((v) & 0xff);
                        ret.add((byte) (val & 0xff));
                    }

                    if (skipBytes > 4) {
                        skipBytes = skipBytes - 4;
                    } else {
                        skipBytes = 0;
                    }
                }
            }

        }
        byte[] bytes = new byte[ret.size()];
        for (int i = 0; i < ret.size(); i++) {
            bytes[i] = ret.get(i);
        }
        return bytes;
    }

    public static int fromBytes(BigInteger toSet, byte[] data, int offset) {
        int length = getIntFrom(data, offset);
        int nums[] = new int[length / 4];
        int numIdx = nums.length;
        for (int idx = offset + 4; idx < offset + length + 4; idx += 4) {
            int v = getIntFrom(data, idx);
            nums[--numIdx] = v;
        }
        toSet.words = nums;
        toSet.ival = nums.length;
        return offset + length + 4;
    }

    public static List<BigInteger> getIntegersOfBitLength(byte[] data, int bitLen) {
        int dataSize = (bitLen - 1) / 32; //serialize for this bitlength allowdd
        if ((bitLen - 1) % 32 != 0) {
            dataSize++;
        }
        int numBis = data.length / dataSize / 4;
        if (data.length % (dataSize * 4) != 0) {
            numBis++;
        }
        int rangeLocation = 0;
        int rangeLength = 0;
        int skip = 0;
        List<BigInteger> ret = new ArrayList<BigInteger>();

//        while (ret.size() < numBis) {
        //creating numBis integers
        for (int loc = 0; loc < data.length; loc += (dataSize * 4)) {

            int numDatIdx = 0;
            rangeLocation = loc;
            if (loc + dataSize * 4 > data.length) {
                rangeLength = data.length - loc;
                dataSize = rangeLength / 4;
                if ((rangeLength) % 4 != 0) dataSize++;
            } else {
                rangeLength = dataSize * 4;
            }

            int[] numDat = new int[dataSize + 1];
            numDat[dataSize] = dataSize * 4;
            numDatIdx = dataSize - 1;

//            NSLog(@"Got buffer %d, %d    %@", range.location, range.length, [[NSData dataWithBytes:(buffer + range.location) length:range.length] hexDump:NO]);
            int i = 0;
            for (i = rangeLocation; i < rangeLocation + rangeLength; i += 4) {
                byte c = data[i];
//                NSLog(@"Processing idx %d-%d", i, i + 4);
                long v = (long) (c & 0xff) << 24;
                if (i + 1 >= rangeLocation + rangeLength) {
                    numDat[numDatIdx--] = (int) (v & 0xffffffff);
                    skip = 24;
                    break;
                }
                c = data[i + 1];
                v |= (long) (c & 0xff) << 16;

                if (i + 2 >= rangeLocation + rangeLength) {
                    numDat[numDatIdx--] = (int) (v & 0xffffffff);
                    skip = 16;
                    break;
                }
                c = data[i + 2];
                v |= (long) (c & 0xff) << 8;

                if (i + 3 >= rangeLocation + rangeLength) {
                    numDat[numDatIdx--] = (int) (v & 0xffffffff);
                    skip = 8;
                    break;
                }
                c = data[i + 3];
                v |= (long) (c & 0xff);
                numDat[numDatIdx--] = (int) (v & 0xffffffff);
                skip = 0;
            }
            BigInteger bi = new BigInteger(numDat, dataSize + 1);
            if (numDatIdx > -1) {
                numDatIdx += 1;
                int arr[] = new int[bi.ival - numDatIdx];
                System.arraycopy(bi.words, numDatIdx, arr, 0, arr.length);
                bi.words = arr;
                bi.ival = arr.length;
            }

            bi.pack();
            ret.add(bi);
        }


//        }
        if (skip > 0) {
            BigInteger bi = ret.get(ret.size() - 1);
            ret.remove(ret.size() - 1);

            //removing prefix
            int len = bi.words[bi.words.length - 1];
            len = len - skip / 8;
            bi.words[bi.words.length - 1] = 0;

            if (!bi.isZero()) {
                bi = bi.shiftRight(skip);
//                bi.pack();
                if (bi.words == null) {
                    System.out.println("Bi.words is null - value: " + bi.toString(16));
                    int[] dat = new int[2];
                    dat[1] = len;
                    dat[0] = bi.ival;
                    bi.words = dat;
                    bi.ival = 2;
                    if (!bi.isZero()) {
                        ret.add(bi);
                    }
                } else {
                    int[] dat = new int[bi.ival + 1];
                    dat[bi.ival] = len;

                    for (int i = bi.ival - 1; i >= 0; i--) {
                        dat[i] = bi.words[i];
                    }
                    bi.words = dat;
                    bi.ival++;
                    if (!bi.isZero()) {
                        ret.add(bi);
                    }
                }


            }
        }

        return ret;
    }

    public int getIval() {
        return ival;
    }

    public void setIval(int ival) {
        this.ival = ival;
    }

    public int[] getWords() {
        return words;
    }

    public void setWords(int[] words) {
        this.words = words;
    }

    private void init(int numBits, Random rnd) {
        int highbits = numBits & 31;
        // minimum number of serialize to store the above number of bits
        int highBitByteCount = (highbits + 7) / 8;
        // number of bits to discard from the last byte
        int discardedBitCount = highbits % 8;
        if (discardedBitCount != 0)
            discardedBitCount = 8 - discardedBitCount;
        byte[] highBitBytes = new byte[highBitByteCount];
        if (highbits > 0) {
            rnd.nextBytes(highBitBytes);
            highbits = (highBitBytes[highBitByteCount - 1] & 0xFF) >>> discardedBitCount;
            for (int i = highBitByteCount - 2; i >= 0; i--)
                highbits = (highbits << 8) | (highBitBytes[i] & 0xFF);
        }
        int nwords = numBits / 32;

        while (highbits == 0 && nwords > 0) {
            highbits = rnd.nextInt();
            --nwords;
        }
        if (nwords == 0 && highbits >= 0) {
            ival = highbits;
            words = null;
        } else {
            ival = highbits < 0 ? nwords + 2 : nwords + 1;
            words = new int[ival];
            words[nwords] = highbits;
            while (--nwords >= 0)
                words[nwords] = rnd.nextInt();
        }
    }

    /**
     * Change words.length to nwords.
     * We allow words.length to be upto nwords+2 without reallocating.
     */
    private void realloc(int nwords) {
        if (nwords == 0) {
            if (words != null) {
                if (ival > 0)
                    ival = words[0];
                words = null;
            }
        } else if (words == null
                || words.length < nwords
                || words.length > nwords + 2) {
            int[] new_words = new int[nwords];
            if (words == null) {
                new_words[0] = ival;
                ival = 1;
            } else {
                if (nwords < ival)
                    ival = nwords;
                System.arraycopy(words, 0, new_words, 0, ival);
            }
            words = new_words;
        }
    }

    protected boolean isNegative() {
        return (words == null ? ival : words[ival - 1]) < 0;
    }

    /**
     * Destructively set this to the negative of x.
     * It is OK if x==this.
     */
    private void setNegative(BigInteger x) {
        int len = x.ival;
        if (x.words == null) {
            if (len == Integer.MIN_VALUE)
                set(-(long) len);
            else
                set(-len);
            return;
        }
        realloc(len + 1);
        if (negate(words, x.words, len))
            words[len++] = 0;
        ival = len;
    }

    public int signum() {
        if (ival == 0 && words == null)
            return 0;
        int top = words == null ? ival : words[ival - 1];
        return top < 0 ? -1 : 1;
    }

    /**
     * @since 1.2
     */
    public int compareTo(BigInteger val) {
        return compareTo(this, val);
    }

    public BigInteger min(BigInteger val) {
        return compareTo(this, val) < 0 ? this : val;
    }

    public BigInteger max(BigInteger val) {
        return compareTo(this, val) > 0 ? this : val;
    }

    public boolean isZero() {
        return words == null && ival == 0;
    }

    public boolean isOne() {
        return words == null && ival == 1;
    }

    private BigInteger canonicalize() {
        if (words != null
                && (ival = BigInteger.wordsNeeded(words, ival)) <= 1) {
            if (ival == 1)
                ival = words[0];
            words = null;
        }
//        if (words == null && ival >= minFixNum && ival <= maxFixNum)
//            return smallFixNums[ival - minFixNum];
        return this;
    }

    /**
     * Set this to the sum of x and y.
     * OK if x==this.
     */
    private void setAdd(BigInteger x, int y) {
        if (x.words == null) {
            set((long) x.ival + (long) y);
            return;
        }
        int len = x.ival;
        realloc(len + 1);
        long carry = y;
        for (int i = 0; i < len; i++) {
            carry += ((long) x.words[i] & 0xffffffffL);
            words[i] = (int) carry;
            carry >>= 32;
        }
        if (x.words[len - 1] < 0)
            carry--;
        words[len] = (int) carry;
        ival = wordsNeeded(words, len + 1);
    }

    /**
     * Destructively add an int to this.
     */
    private void setAdd(int y) {
        setAdd(this, y);
    }

    /**
     * Destructively set the value of this to a long.
     */
    private void set(long y) {
        int i = (int) y;
        if ((long) i == y) {
            ival = i;
            words = null;
        } else {
            realloc(2);
            words[0] = i;
            words[1] = (int) (y >> 32);
            ival = 2;
        }
    }

    /**
     * Destructively set the value of this to the given words.
     * The words array is reused, not copied.
     */
    private void set(int[] words, int length) {
        this.ival = length;
        this.words = words;
    }

    /**
     * Destructively set the value of this to that of y.
     */
    private void set(BigInteger y) {
        if (y.words == null)
            set(y.ival);
        else if (this != y) {
            realloc(y.ival);
            System.arraycopy(y.words, 0, words, 0, y.ival);
            ival = y.ival;
        }
    }

    public BigInteger add(BigInteger val) {
        return add(this, val, 1);
    }

    public BigInteger subtract(BigInteger val) {
        return add(this, val, -1);
    }

    public BigInteger multiply(BigInteger y) {
        return times(this, y);
    }

    public BigInteger divide(BigInteger val) {
        if (val.isZero())
            throw new ArithmeticException("divisor is zero");

        BigInteger quot = new BigInteger();
        divide(this, val, quot, null, TRUNCATE);
        return quot.canonicalize();
    }

    public BigInteger remainder(BigInteger val) {
        if (val.isZero())
            throw new ArithmeticException("divisor is zero");

        BigInteger rem = new BigInteger();
        divide(this, val, null, rem, TRUNCATE);
        return rem.canonicalize();
    }

    public BigInteger[] divideAndRemainder(BigInteger val) {
        if (val.isZero())
            throw new ArithmeticException("divisor is zero");

        BigInteger[] result = new BigInteger[2];
        result[0] = new BigInteger();
        result[1] = new BigInteger();
        divide(this, val, result[0], result[1], TRUNCATE);
        result[0].canonicalize();
        result[1].canonicalize();
        return result;
    }

    public BigInteger mod(BigInteger m) {
        if (m.isNegative() || m.isZero())
            throw new ArithmeticException("non-positive modulus");

        BigInteger rem = new BigInteger();
        divide(this, m, null, rem, FLOOR);
        return rem.canonicalize();
    }

    /**
     * Calculate the integral power of a BigInteger.
     *
     * @param exponent the exponent (must be non-negative)
     */
    public BigInteger pow(int exponent) {
        if (exponent <= 0) {
            if (exponent == 0)
                return new BigInteger(1);
            throw new ArithmeticException("negative exponent");
        }
        if (isZero())
            return this;
        int plen = words == null ? 1 : ival;  // Length of pow2.
        int blen = ((bitLength() * exponent) >> 5) + 2 * plen;
        boolean negative = isNegative() && (exponent & 1) != 0;
        int[] pow2 = new int[blen];
        int[] rwords = new int[blen];
        int[] work = new int[blen];
        getAbsolute(pow2);    // pow2 = abs(this);
        int rlen = 1;
        rwords[0] = 1; // rwords = 1;
        for (; ; )  // for (i = 0;  ; i++)
        {
            // pow2 == this**(2**i)
            // prod = this**(sum(j=0..i-1, (exponent>>j)&1))
            if ((exponent & 1) != 0) { // r *= pow2
                MPN.mul(work, pow2, plen, rwords, rlen);
                int[] temp = work;
                work = rwords;
                rwords = temp;
                rlen += plen;
                while (rwords[rlen - 1] == 0) rlen--;
            }
            exponent >>= 1;
            if (exponent == 0)
                break;
            // pow2 *= pow2;
            MPN.mul(work, pow2, plen, pow2, plen);
            int[] temp = work;
            work = pow2;
            pow2 = temp;  // swap to avoid a copy
            plen *= 2;
            while (pow2[plen - 1] == 0) plen--;
        }
        if (rwords[rlen - 1] < 0)
            rlen++;
        if (negative)
            negate(rwords, rwords, rlen);
        return BigInteger.make(rwords, rlen);
    }

    public BigInteger modInverse(BigInteger y) {
        if (y.isNegative() || y.isZero())
            throw new ArithmeticException("non-positive modulo");

        // Degenerate cases.
        if (y.isOne())
            return new BigInteger(0);
        if (isOne())
            return new BigInteger(1);

        // Use Euclid's algorithm as in gcd() but do this recursively
        // rather than in a loop so we can use the intermediate results as we
        // unwind from the recursion.
        // Used http://www.math.nmsu.edu/~crypto/EuclideanAlgo.html as reference.
        BigInteger result = new BigInteger();
        boolean swapped = false;

        if (y.words == null) {
            // The result is guaranteed to be less than the modulus, y (which is
            // an int), so simplify this by working with the int result of this
            // modulo y.  Also, if this is negative, make it positive via modulo
            // math.  Note that BigInteger.mod() must be used even if this is
            // already an int as the % operator would provide a negative result if
            // this is negative, BigInteger.mod() never returns negative values.
            int xval = (words != null || isNegative()) ? mod(y).ival : ival;
            int yval = y.ival;

            // Swap values so x > y.
            if (yval > xval) {
                int tmp = xval;
                xval = yval;
                yval = tmp;
                swapped = true;
            }
            // Normally, the result is in the 2nd element of the array, but
            // if originally x < y, then x and y were swapped and the result
            // is in the 1st element of the array.
            result.ival =
                    euclidInv(yval, xval % yval, xval / yval)[swapped ? 0 : 1];

            // Result can't be negative, so make it positive by adding the
            // original modulus, y.ival (not the possibly "swapped" yval).
            if (result.ival < 0)
                result.ival += y.ival;
        } else {
            // As above, force this to be a positive value via modulo math.
            BigInteger x = isNegative() ? this.mod(y) : this;

            // Swap values so x > y.
            if (x.compareTo(y) < 0) {
                result = x;
                x = y;
                y = result; // use 'result' as a work var
                swapped = true;
            }
            // As above (for ints), result will be in the 2nd element unless
            // the original x and y were swapped.
            BigInteger rem = new BigInteger();
            BigInteger quot = new BigInteger();
            divide(x, y, quot, rem, FLOOR);
            // quot and rem may not be in canonical form. ensure
            rem.canonicalize();
            quot.canonicalize();
            BigInteger[] xy = new BigInteger[2];
            euclidInv(y, rem, quot, xy);
            result = swapped ? xy[0] : xy[1];

            // Result can't be negative, so make it positive by adding the
            // original modulus, y (which is now x if they were swapped).
            if (result.isNegative())
                result = add(result, swapped ? x : y, 1);
        }

        return result;
    }

    public BigInteger modPow(BigInteger exponent, BigInteger m) {
        if (m.isNegative() || m.isZero())
            throw new ArithmeticException("non-positive modulo");

        if (exponent.isNegative())
            return modInverse(m).modPow(exponent.negate(), m);
        if (exponent.isOne())
            return mod(m);

        // To do this naively by first raising this to the power of exponent
        // and then performing modulo m would be extremely expensive, especially
        // for very large numbers.  The solution is found in Number Theory
        // where a combination of partial powers and moduli can be done easily.
        //
        // We'll use the algorithm for Additive Chaining which can be found on
        // p. 244 of "Applied Cryptography, Second Edition" by Bruce Schneier.
        BigInteger s = new BigInteger(1);
        BigInteger t = this;
        BigInteger u = exponent;
        int runcounter = 0;
        while (!u.isZero()) {
            runcounter++;
            if (u.and(new BigInteger(1)).isOne()) {
                BigInteger tmp = times(s, t);
                s = tmp.mod(m);
            }
            u = u.shiftRight(1);
            t = times(t, t).mod(m);
        }

        return s;
    }

    public BigInteger gcd(BigInteger y) {
        int xval = ival;
        int yval = y.ival;
        if (words == null) {
            if (xval == 0)
                return abs(y);
            if (y.words == null
                    && xval != Integer.MIN_VALUE && yval != Integer.MIN_VALUE) {
                if (xval < 0)
                    xval = -xval;
                if (yval < 0)
                    yval = -yval;
                return valueOf(gcd(xval, yval));
            }
            xval = 1;
        }
        if (y.words == null) {
            if (yval == 0)
                return abs(this);
            yval = 1;
        }
        int len = (xval > yval ? xval : yval) + 1;
        int[] xwords = new int[len];
        int[] ywords = new int[len];
        getAbsolute(xwords);
        y.getAbsolute(ywords);
        len = MPN.gcd(xwords, ywords, len);
        BigInteger result = new BigInteger(0);
        result.ival = len;
        result.words = xwords;
        return result.canonicalize();
    }

    /**
     * <p>Returns <code>true</code> if this BigInteger is probably prime,
     * <code>false</code> if it's definitely composite. If <code>certainty</code>
     * is <code><= 0</code>, <code>true</code> is returned.</p>
     *
     * @param certainty a measure of the uncertainty that the caller is willing
     *                  to tolerate: if the call returns <code>true</code> the probability that
     *                  this BigInteger is prime exceeds <code>(1 - 1/2<sup>certainty</sup>)</code>.
     *                  The execution time of this method is proportional to the value of this
     *                  parameter.
     * @return <code>true</code> if this BigInteger is probably prime,
     *         <code>false</code> if it's definitely composite.
     */
    public boolean isProbablePrime(int certainty) {
        if (certainty < 1)
            return true;

        /** We'll use the Rabin-Miller algorithm for doing a probabilistic
         * primality test.  It is fast, easy and has faster decreasing odds of a
         * composite passing than with other tests.  This means that this
         * method will actually have a probability much greater than the
         * 1 - .5^certainty specified in the JCL (p. 117), but I don't think
         * anyone will complain about better performance with greater certainty.
         *
         * The Rabin-Miller algorithm can be found on pp. 259-261 of "Applied
         * Cryptography, Second Edition" by Bruce Schneier.
         */

        // First rule out small prime factors
        BigInteger rem = new BigInteger();
        int i;
        for (i = 0; i < primes.length; i++) {
            if (words == null && ival == primes[i])
                return true;
            divide(this, BigInteger.valueOf(primes[i]), null, rem, TRUNCATE);
            if (rem.canonicalize().isZero())
                return false;
        }

        // Now perform the Rabin-Miller test.

        // Set b to the number of times 2 evenly divides (this - 1).
        // I.e. 2^b is the largest power of 2 that divides (this - 1).
        BigInteger pMinus1 = add(this, -1);
        int b = pMinus1.getLowestSetBit();

        // Set m such that this = 1 + 2^b * m.
        long val = 2L << b - 1;
        BigInteger m = pMinus1.divide(valueOf(val));

        // The HAC (Handbook of Applied Cryptography), Alfred Menezes & al. Note
        // 4.49 (controlling the error probability) gives the number of trials
        // for an error probability of 1/2**80, given the number of bits in the
        // number to test.  we shall use these numbers as is if/when 'certainty'
        // is less or equal to 80, and twice as much if it's greater.
        int bits = this.bitLength();
        for (i = 0; i < k.length; i++)
            if (bits <= k[i])
                break;
        int trials = t[i];
        if (certainty > 80)
            trials *= 2;
        BigInteger z;
        for (int t = 0; t < trials; t++) {
            // The HAC (Handbook of Applied Cryptography), Alfred Menezes & al.
            // Remark 4.28 states: "...A strategy that is sometimes employed
            // is to fix the bases a to be the first few primes instead of
            // choosing them at random.
            z = new BigInteger(primes[t]).modPow(m, this);
            if (z.isOne() || z.equals(pMinus1))
                continue;            // Passes the test; may be prime.

            for (i = 0; i < b; ) {
                if (z.isOne())
                    return false;
                i++;
                if (z.equals(pMinus1))
                    break;            // Passes the test; may be prime.

                z = z.modPow(valueOf(2), this);
            }

            if (i == b && !z.equals(pMinus1))
                return false;
        }
        return true;
    }

    private void setInvert() {
        if (words == null)
            ival = ~ival;
        else {
            for (int i = ival; --i >= 0; )
                words[i] = ~words[i];
        }
    }

    private void setShiftLeft(BigInteger x, int count) {
        int[] xwords;
        int xlen;
        if (x.words == null) {
            if (count < 32) {
                set((long) x.ival << count);
                return;
            }
            xwords = new int[1];
            xwords[0] = x.ival;
            xlen = 1;
        } else {
            xwords = x.words;
            xlen = x.ival;
        }
        int word_count = count >> 5;
        count &= 31;
        int new_len = xlen + word_count;
        if (count == 0) {
            realloc(new_len);
            for (int i = xlen; --i >= 0; )
                words[i + word_count] = xwords[i];
        } else {
            new_len++;
            realloc(new_len);
            int shift_out = MPN.lshift(words, word_count, xwords, xlen, count);
            count = 32 - count;
            words[new_len - 1] = (shift_out << count) >> count;  // sign-extend.
        }
        ival = new_len;
        for (int i = word_count; --i >= 0; )
            words[i] = 0;
    }

    private void setShiftRight(BigInteger x, int count) {
        if (x.words == null)
            set(count < 32 ? x.ival >> count : x.ival < 0 ? -1 : 0);
        else if (count == 0)
            set(x);
        else {
            boolean neg = x.isNegative();
            int word_count = count >> 5;
            count &= 31;
            int d_len = x.ival - word_count;
            if (d_len <= 0)
                set(neg ? -1 : 0);
            else {
                if (words == null || words.length < d_len)
                    realloc(d_len);
                MPN.rshift0(words, x.words, word_count, d_len, count);
                ival = d_len;
                if (neg)
                    words[d_len - 1] |= -2 << (31 - count);
            }
        }
    }

    private void setShift(BigInteger x, int count) {
        if (count > 0)
            setShiftLeft(x, count);
        else
            setShiftRight(x, -count);
    }

    public BigInteger shiftLeft(int n) {
        return shift(this, n);
    }

    public BigInteger shiftRight(int n) {
        return shift(this, -n);
    }

    private void format(int radix, StringBuffer buffer) {
        if (words == null)
            buffer.append(Integer.toString(ival, radix));
        else if (ival <= 2)
            buffer.append(Long.toString(longValue(), radix));
        else {
            boolean neg = isNegative();
            int[] work;
            if (neg || radix != 16) {
                work = new int[ival];
                getAbsolute(work);
            } else
                work = words;
            int len = ival;

            if (radix == 16) {
                if (neg)
                    buffer.append('-');
                int buf_start = buffer.length();
                for (int i = len; --i >= 0; ) {
                    int word = work[i];
                    for (int j = 8; --j >= 0; ) {
                        int hex_digit = (word >> (4 * j)) & 0xF;
                        // Suppress leading zeros:
                        if (hex_digit > 0 || buffer.length() > buf_start)
                            buffer.append(Character.forDigit(hex_digit, 16));
                    }
                }
            } else {
                int i = buffer.length();
                for (; ; ) {
                    int digit = MPN.divmod_1(work, work, len, radix);
                    buffer.append(Character.forDigit(digit, radix));
                    while (len > 0 && work[len - 1] == 0) len--;
                    if (len == 0)
                        break;
                }
                if (neg)
                    buffer.append('-');
         /* Reverse buffer. */
                int j = buffer.length() - 1;
                while (i < j) {
                    char tmp = buffer.charAt(i);
                    buffer.setCharAt(i, buffer.charAt(j));
                    buffer.setCharAt(j, tmp);
                    i++;
                    j--;
                }
            }
        }
    }

    public String toString() {
        return toString(16).toUpperCase();
    }

    public String toString(int radix) {
        if (words == null)
            return Integer.toString(ival, radix);
        if (ival <= 2)
            return Long.toString(longValue(), radix);
        int buf_size = ival * (MPN.chars_per_word(radix) + 1);
        StringBuffer buffer = new StringBuffer(buf_size);
        format(radix, buffer);
        return buffer.toString();
    }

    public int intValue() {
        if (words == null)
            return ival;
        return words[0];
    }

    public long longValue() {
        if (words == null)
            return ival;
        if (ival == 1)
            return words[0];
        return ((long) words[1] << 32) + ((long) words[0] & 0xffffffffL);
    }

    public int hashCode() {
        // FIXME: May not match hashcode of JDK.
        return words == null ? ival : (words[0] + words[ival - 1]);
    }

    /* Assumes this and obj are both canonicalized. */
    public boolean equals(Object obj) {
        if (!(obj instanceof BigInteger))
            return false;
        return equals(this, (BigInteger) obj);
    }

    public double doubleValue() {
        if (words == null)
            return (double) ival;
        if (ival <= 2)
            return (double) longValue();
        if (isNegative())
            return neg(this).roundToDouble(0, true, false);
        return roundToDouble(0, false, false);
    }

    public float floatValue() {
        return (float) doubleValue();
    }

    /**
     * Return true if any of the lowest n bits are one.
     * (false if n is negative).
     */
    private boolean checkBits(int n) {
        if (n <= 0)
            return false;
        if (words == null)
            return n > 31 || ((ival & ((1 << n) - 1)) != 0);
        int i;
        for (i = 0; i < (n >> 5); i++)
            if (words[i] != 0)
                return true;
        return (n & 31) != 0 && (words[i] & ((1 << (n & 31)) - 1)) != 0;
    }

    /**
     * Convert a semi-processed BigInteger to double.
     * Number must be non-negative.  Multiplies by a power of two, applies sign,
     * and converts to double, with the usual java rounding.
     *
     * @param exp       power of two, positive or negative, by which to multiply
     * @param neg       true if negative
     * @param remainder true if the BigInteger is the result of a truncating
     *                  division that had non-zero remainder.  To ensure proper rounding in
     *                  this case, the BigInteger must have at least 54 bits.
     */
    private double roundToDouble(int exp, boolean neg, boolean remainder) {
        // Compute length.
        int il = bitLength();

        // Exponent when normalized to have decimal point directly after
        // leading one.  This is stored excess 1023 in the exponent bit field.
        exp += il - 1;

        // Gross underflow.  If exp == -1075, we let the rounding
        // computation determine whether it is minval or 0 (which are just
        // 0x0000 0000 0000 0001 and 0x0000 0000 0000 0000 as bit
        // patterns).
        if (exp < -1075)
            return neg ? -0.0 : 0.0;

        // gross overflow
        if (exp > 1023)
            return neg ? Double.NEGATIVE_INFINITY : Double.POSITIVE_INFINITY;

        // number of bits in mantissa, including the leading one.
        // 53 unless it's denormalized
        int ml = (exp >= -1022 ? 53 : 53 + exp + 1022);

        // Get top ml + 1 bits.  The extra one is for rounding.
        long m;
        int excess_bits = il - (ml + 1);
        if (excess_bits > 0)
            m = ((words == null) ? ival >> excess_bits
                    : MPN.rshift_long(words, ival, excess_bits));
        else
            m = longValue() << (-excess_bits);

        // Special rounding for maxval.  If the number exceeds maxval by
        // any amount, even if it's less than half a step, it overflows.
        if (exp == 1023 && ((m >> 1) == (1L << 53) - 1)) {
            if (remainder || checkBits(il - ml))
                return neg ? Double.NEGATIVE_INFINITY : Double.POSITIVE_INFINITY;
            else
                return neg ? -Double.MAX_VALUE : Double.MAX_VALUE;
        }

        // Normal round-to-even rule: round up if the bit dropped is a one, and
        // the bit above it or any of the bits below it is a one.
        if ((m & 1) == 1
                && ((m & 2) == 2 || remainder || checkBits(excess_bits))) {
            m += 2;
            // Check if we overflowed the mantissa
            if ((m & (1L << 54)) != 0) {
                exp++;
                // renormalize
                m >>= 1;
            }
            // Check if a denormalized mantissa was just rounded up to a
            // normalized one.
            else if (ml == 52 && (m & (1L << 53)) != 0)
                exp++;
        }

        // Discard the rounding bit
        m >>= 1;

        long bits_sign = neg ? (1L << 63) : 0;
        exp += 1023;
        long bits_exp = (exp <= 0) ? 0 : ((long) exp) << 52;
        long bits_mant = m & ~(1L << 52);
        return Double.longBitsToDouble(bits_sign | bits_exp | bits_mant);
    }

    /**
     * Copy the abolute value of this into an array of words.
     * Assumes words.length >= (this.words == null ? 1 : this.ival).
     * Result is zero-extended, but need not be a valid 2's complement number.
     */
    private void getAbsolute(int[] words) {
        int len;
        if (this.words == null) {
            len = 1;
            words[0] = this.ival;
        } else {
            len = this.ival;
            for (int i = len; --i >= 0; )
                words[i] = this.words[i];
        }
        if (words[len - 1] < 0)
            negate(words, words, len);
        for (int i = words.length; --i > len; )
            words[i] = 0;
    }

    /**
     * Destructively negate this.
     */
    private void setNegative() {
        setNegative(this);
    }

    public BigInteger abs() {
        return abs(this);
    }

    public BigInteger negate() {
        return neg(this);
    }

    /**
     * Calculates ceiling(log2(this < 0 ? -this : this+1))
     * See Common Lisp: the Language, 2nd ed, p. 361.
     */
    public int bitLength() {
        if (words == null)
            return MPN.intLength(ival);
        return MPN.intLength(words, ival);
    }

    public byte[] toByteArray() {
        // Determine number of serialize needed.  The method bitlength returns
        // the size without the sign bit, so add one bit for that and then
        // add 7 more to emulate the ceil function using integer math.
        byte[] bytes = new byte[(bitLength() + 1 + 7) / 8];
        int nbytes = bytes.length;

        int wptr = 0;
        int word;

        // Deal with words array until one word or less is left to process.
        // If BigInteger is an int, then it is in ival and nbytes will be <= 4.
        while (nbytes > 4) {
            word = words[wptr++];
            for (int i = 4; i > 0; --i, word >>= 8)
                bytes[--nbytes] = (byte) word;
        }

        // Deal with the last few serialize.  If BigInteger is an int, use ival.
        word = (words == null) ? ival : words[wptr];
        for (; nbytes > 0; word >>= 8)
            bytes[--nbytes] = (byte) word;

        return bytes;
    }

    /**
     * Return the logical (bit-wise) "and" of two BigIntegers.
     */
    public BigInteger and(BigInteger y) {
        if (y.words == null)
            return and(this, y.ival);
        else if (words == null)
            return and(y, ival);

        BigInteger x = this;
        if (ival < y.ival) {
            BigInteger temp = this;
            x = y;
            y = temp;
        }
        int i;
        int len = y.isNegative() ? x.ival : y.ival;
        int[] words = new int[len];
        for (i = 0; i < y.ival; i++)
            words[i] = x.words[i] & y.words[i];
        for (; i < len; i++)
            words[i] = x.words[i];
        return make(words, len);
    }

    /**
     * Return the logical (bit-wise) "(inclusive) or" of two BigIntegers.
     */
    public BigInteger or(BigInteger y) {
        return bitOp(7, this, y);
    }

    /**
     * Return the logical (bit-wise) "exclusive or" of two BigIntegers.
     */
    public BigInteger xor(BigInteger y) {
        return bitOp(6, this, y);
    }

    /**
     * Return the logical (bit-wise) negation of a BigInteger.
     */
    public BigInteger not() {
        return bitOp(12, this, new BigInteger(0));
    }

//
//    public String getHex() {
//        String c[] = new String[]{"0", "1", "2", "3", "4", "5", "6", "7", "8", "9", "A", "B", "C", "D", "E", "F"};
//
//        int[] arc=words;
//        if (words==null || words.length==0) {
//            arc=new int[]{ival};
//        }
//        StringBuilder ret=new StringBuilder();
//        for (int i = arc.length - 1; i >= 0; i--) {
//            int value = arc[i];
//            int idx;
////        idx=value>>60&0x0000000f;
////        [ret appendString:c[idx]];
////        idx=value>>56&0x0000000f;
////        [ret appendString:c[idx]];
////
////        idx=value>>52&0x0000000f;
////        [ret appendString:c[idx]];
////        idx=value>>48&0x0000000f;
////        [ret appendString:c[idx]];
////
////        idx=value>>44&0x0000000f;
////        [ret appendString:c[idx]];
////        idx=value>>40&0x0000000f;
////        [ret appendString:c[idx]];
////
////        idx=value>>36&0x0000000f;
////        [ret appendString:c[idx]];
////        idx=value>>32&0x0000000f;
////        [ret appendString:c[idx]];
//
//            idx = value >> 28 & 0x0000000f;
//            ret.append(c[idx]);
//            idx = value >> 24 & 0x0000000f;
//            ret.append(c[idx]);
//
//            idx = value >> 20 & 0x0000000f;
//            ret.append(c[idx]);
//            idx = value >> 16 & 0x0000000f;
//            ret.append(c[idx]);
//
//            idx = value >> 12 & 0x0000000f;
//            ret.append(c[idx]);
//            idx = value >> 8 & 0x0000000f;
//            ret.append(c[idx]);
//
//            idx = value >> 4 & 0x0000000f;
//            ret.append(c[idx]);
//            idx = value & 0x0000000f;
//            ret.append(c[idx]);
//
//        }
//        return ret.toString();
//    }

    public BigInteger andNot(BigInteger val) {
        return and(val.not());
    }

    public BigInteger clearBit(int n) {
        if (n < 0)
            throw new ArithmeticException();

        return and(new BigInteger(1).shiftLeft(n).not());
    }

    public BigInteger setBit(int n) {
        if (n < 0)
            throw new ArithmeticException();

        return or(new BigInteger(1).shiftLeft(n));
    }

    public boolean testBit(int n) {
        if (n < 0)
            throw new ArithmeticException();

        return !and(new BigInteger(1).shiftLeft(n)).isZero();
    }

    public BigInteger flipBit(int n) {
        if (n < 0)
            throw new ArithmeticException();

        return xor(new BigInteger(1).shiftLeft(n));
    }

    public int getLowestSetBit() {
        if (isZero())
            return -1;

        if (words == null)
            return MPN.findLowestBit(ival);
        else
            return MPN.findLowestBit(words);
    }

    /**
     * Count one bits in a BigInteger.
     * If argument is negative, count zero bits instead.
     */
    public int bitCount() {
        int i, x_len;
        int[] x_words = words;
        if (x_words == null) {
            x_len = 1;
            i = bitCount(ival);
        } else {
            x_len = ival;
            i = bitCount(x_words, x_len);
        }
        return isNegative() ? x_len * 32 - i : i;
    }

    public byte[] serialize() {
        byte buffer[] = {0, 0, 0, 0};
        ArrayList<Byte> dat = new ArrayList<Byte>();
        //first 4 serialize == integer show length of this integer
        // eg: 000004 => 4 serialize in length
        // if < then maxValueInt => 4 serialize
        if (this.words == null) {
            //use iVal only
            fillInteger(4, buffer);
            dat.add(buffer[0]);
            dat.add(buffer[1]);
            dat.add(buffer[2]);
            dat.add(buffer[3]);
            fillInteger(ival, buffer);
            dat.add(buffer[0]);
            dat.add(buffer[1]);
            dat.add(buffer[2]);
            dat.add(buffer[3]);

            byte[] ret = new byte[dat.size()];
            for (int i = 0; i < dat.size(); i++) {
                ret[i] = dat.get(i);
            }
            return ret;
        }

        fillInteger(ival * 4, buffer);
        dat.add(buffer[0]);
        dat.add(buffer[1]);
        dat.add(buffer[2]);
        dat.add(buffer[3]);

        for (int j = ival - 1; j >= 0; j--) {
            int v = words[j];
//        if (j == self.dataSize - 1 && v == 0) continue; //Why!??!?!?
            fillInteger(v, buffer);
            dat.add(buffer[0]);
            dat.add(buffer[1]);
            dat.add(buffer[2]);
            dat.add(buffer[3]);
        }
        byte[] ret = new byte[dat.size()];
        for (int i = 0; i < dat.size(); i++) {
            ret[i] = dat.get(i);
        }
        return ret;
    }

    public void pack() {
        if (words == null) return;
        if (ival > 0 && words.length == 0) {
            words = null;
            return;
        }
        if (words.length == 0) {
            words = null;
        } else {
            ival = words.length;
            while (ival > 0 && words[ival - 1] == 0) {

                int[] newWords = new int[ival - 1];
                System.arraycopy(words, 0, newWords, 0, ival - 1);
                ival = newWords.length;
                words = newWords;
            }
            if (ival == 1) {
                ival = words[0];
                words = null;
            }
            if (ival == 0) {
                words = null;
            }
        }
    }
}
