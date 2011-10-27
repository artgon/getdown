package com.artgon.getdown

/**
 * This class parses the Markdown span elements
 *
 * @author Arthur Gonigberg
 * @since 11-10-22
 */
class SpanParser {
    def parseText(String s) {
        def span = new TextContainer(s)
        /*
           7.7.2.2.1. do code spans
           7.7.2.2.2. do escape special chars
           7.7.2.2.3. do images
           7.7.2.2.4. do anchors
           7.7.2.2.5. do do auto links
           7.7.2.2.6. encode amps/angles
           7.7.2.2.7. do italics/bolds
           7.7.2.2.8. do hard breaks
         */
        searchReplaceEmphasis(span)
        span.toString()
    }

    def searchReplaceEmphasis(TextContainer tc) {
        tc.replaceAll(/[\*_]{2}(.*)[\*_]{2}/,"<strong>\$1</strong>")
        tc.replaceAll(/[\*_](.*)[\*_]{1}/,"<em>\$1</em>")
        return this
    }
}
