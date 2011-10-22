package com.artgon.getdown
/**
 * This class strips out the Markdown symbols
 *
 * @author Arthur Gonigberg
 * @since 11-10-22
 */
class MarkdownStripper {
    def stripHeader(String s) {
        // strip last newline char along with one or more header chars after it
        s.replaceAll('\n[' + BlockParser.HEADER_1 + ',' + BlockParser.HEADER_2 + ']+', '')
    }

    def stripBlockquote(String s) {
        s.replaceAll(BlockParser.BLOCKQUOTE, '')
    }

    def stripEmphasis(String s) {
        // add escape character before * for regex
        s.replaceAll('\\' + SpanParser.ITALIC, '')
    }
}
