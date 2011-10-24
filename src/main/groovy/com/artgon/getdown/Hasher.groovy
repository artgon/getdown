package com.artgon.getdown

import java.security.MessageDigest

/**
 * Quick md5 hashing method
 * 
 * @author Arthur Gonigberg
 * @since 11-10-24
 */
class Hasher {
    def hashString(String s) {
        def m = MessageDigest.getInstance("MD5")
        m.update(s.bytes,0, s.length())
        new BigInteger(1,m.digest()).toString(16)
    }
}
