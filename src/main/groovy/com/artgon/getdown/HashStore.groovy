package com.artgon.getdown

import java.security.MessageDigest

/**
 * Quick md5 hashing method
 *
 * @author Arthur Gonigberg
 * @since 11-10-24
 */
class HashStore {
    def hashMap = [:]

    def hashHtmlBlocks(TextContainer t) {
        def tagsA = "p|div|h[1-6]|blockquote|pre|table|dl|ol|ul|script|noscript|form|fieldset|iframe|math|ins|del"

        def hashHtmlClosure = {text ->
            '\n\n' + hashValue(text[0]) + '\n\n'
        }
        // strip tags
        t.replaceAll("(^<($tagsA)\\b(.*\\n)*?.*</\\2>[ \\t]*(?=\\n+|\\Z))", hashHtmlClosure)
    }

    def unHashHtmlBlocks(String s){
        def strippedS = s.replaceAll('\n','')
        if( containsKey(strippedS)){
            return hashMap.get(strippedS)
        }
        return s
    }

    def hashValue(String t) {
        def key = hashString(t)
        hashMap.put(key, t)
        key
    }

    def containsKey(String t) {
        hashMap.containsKey(t)
    }

    def containsValue(String t) {
        hashMap.containsValue(t)
    }

    def static hashString(String s) {
        def m = MessageDigest.getInstance("MD5")
        m.update(s.bytes, 0, s.length())
        new BigInteger(1, m.digest()).toString(16)
    }
}
