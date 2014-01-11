package de.caluga.rsa;

/*
 * CL_SUN_COPYRIGHT_JVM_BEGIN
 *   If you or the company you represent has a separate agreement with both
 *   CableLabs and Sun concerning the use of this code, your rights and
 *   obligations with respect to this code shall be as set forth therein. No
 *   license is granted hereunder for any other purpose.
 * CL_SUN_COPYRIGHT_JVM_END
*/

/*
 * @(#)SHA3.java	1.6 06/10/10
 *
 * Copyright  1990-2006 Sun Microsystems, Inc. All Rights Reserved.
 * DO NOT ALTER OR REMOVE COPYRIGHT NOTICES OR THIS FILE HEADER
 *
 * This program is free software; you can redistribute it and/or
 * modify it under the terms of the GNU General Public License version
 * 2 only, as published by the Free Software Foundation.
 *
 * This program is distributed in the hope that it will be useful, but
 * WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE. See the GNU
 * General Public License version 2 for more details (a copy is
 * included at /legal/license.txt).
 *
 * You should have received a copy of the GNU General Public License
 * version 2 along with this work; if not, write to the Free Software
 * Foundation, Inc., 51 Franklin St, Fifth Floor, Boston, MA
 * 02110-1301 USA
 *
 * Please contact Sun Microsystems, Inc., 4150 Network Circle, Santa
 * Clara, CA 95054 or visit www.sun.com if you need additional
 * information or have any questions.
 */

public class SHA3 extends SHA2 {

    private int length = 48;
    private static final long[] INITIAL_HASHES = {
            0xcbbb9d5dc1059ed8L, 0x629a292a367cd507L,
            0x9159015a3070dd17L, 0x152fecd8f70e5939L,
            0x67332667ffc00b31L, 0x8eb44a8768581511L,
            0xdb0c2e0d64f98fa7L, 0x47b5481dbefa4fa4L
    };

    public SHA3(int len) {
        length = len;
    }

    public SHA3() {
        init();
    }

    private SHA3(SHA3 that) {
        super((SHA2) that);
    }

    void init() {
        super.init();
        setInitialHash(INITIAL_HASHES);
    }

    /**
     * Return the length of the digest in bytes
     */
    protected int engineGetDigestLength() {
        return (length);
    }

    /**
     * Computes the final hash and returns the final value as a
     * byte[48] array. The object is reset to be ready for further
     * use, as specified in the JavaSecurity MessageDigest
     * specification.
     */
    public byte[] engineDigest() {
        byte[] sha5hashvalue = super.engineDigest();
        byte[] hashvalue = new byte[length];
        System.arraycopy(sha5hashvalue, 0, hashvalue, 0, length);
        return hashvalue;
    }

    /**
     * Resets the buffers and hash value to start a new hash.
     */
    public void engineReset() {
        init();
    }

    /**
     * Computes the final hash and returns the final value as a
     * byte[48] array. The object is reset to be ready for further
     * use, as specified in the JavaSecurity MessageDigest
     * specification.
     *
     * @param hashvalue buffer to hold the digest
     * @param offset    offset for storing the digest
     * @param len       length of the buffer
     * @return length of the digest in bytes
     */
    protected int engineDigest(byte[] hashvalue, int offset, int len) {
        if (len < length)
            throw new RuntimeException("partial digests not returned");
        if (hashvalue.length - offset < length)
            throw new RuntimeException("insufficient space in the output " +
                    "buffer to store the digest");
        super.performDigest(hashvalue, offset, length);
        return length;
    }

    /*
     * Clones this object.
     */
    public Object clone() {
        SHA3 that = null;
        that = new SHA3(this);
        return that;
    }

}
