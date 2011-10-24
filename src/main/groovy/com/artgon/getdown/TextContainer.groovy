package com.artgon.getdown

import java.util.regex.Pattern

/**
 * Container for storing/updating the String value
 *
 * @author Arthur Gonigberg
 * @since 11-10-23
 */
class TextContainer {
    private StringBuilder sb

    def TextContainer(String text) {
        this.sb = new StringBuilder(text)
    }

    @Override
    def String toString() {
        sb.toString()
    }

    def replaceAll(String regex, String replacement) {
        def p = Pattern.compile(regex, Pattern.MULTILINE)
        sb = new StringBuilder(sb.toString().replaceAll(p, replacement))
        return this
    }

    def replaceAll(String regex, Closure closure) {
        def p = Pattern.compile(regex, Pattern.MULTILINE)
        sb = new StringBuilder(sb.toString().replaceAll(p, closure))
        return this
    }

    def leftShift(s) {
        sb << s
    }
}
