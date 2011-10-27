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
        // this is a set of all possible html tags we want to hash
        def tagsA = "p|div|h[1-6]|blockquote|pre|table|dl|ol|ul|script|noscript|form|fieldset|iframe|math|ins|del"

        // this closure hashes a value and returns it surrounded by new lines
        def hashHtmlClosure = {text ->
            '\n\n' + hashValue(text[0]) + '\n\n'
        }
        /*
        Regex description:
            This regular expression grabs any matching set of tags, from the set above and
            returns it. After the beginning of the first tag, it expects a word break, then
            any number of characters, including new lines, and finally any possible spaces,
            newlines or the end of the string.
         */
        t.replaceAll("(^<($tagsA)\\b(.*\\n)*?.*</\\2>[ \\t]*(?=\\n+|\\Z))", hashHtmlClosure)
    }

    def unHashHtmlBlocks(String s){
        // get rid of all the newline characters
        def strippedS = s.replaceAll('\n','')
        // return the original value if the key exists
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

    def static hashString(String s) {
        def m = MessageDigest.getInstance("MD5")
        m.update(s.bytes, 0, s.length())
        new BigInteger(1, m.digest()).toString(16)
    }
}
