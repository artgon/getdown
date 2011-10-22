package com.artgon.getdown
/**
 * This class strips out the Markdown symbols
 * 
 * @author Arthur Gonigberg
 * @since 11-10-22
 */
class MarkdownStripper {
    def stripHeader(String s) {
        // strip last newline char
        s.replace('\n'+BlockParser.HEADER_1,'')
        .replace('\n'+BlockParser.HEADER_2,'')
        // strip all header symbols
        .replaceAll(BlockParser.HEADER_1, '')
        .replaceAll(BlockParser.HEADER_2, '')
    }

    def stripBlockquote(String s) {
        s.replaceAll(BlockParser.BLOCKQUOTE,'')
    }

    def stripEmphasis(String s) {
        s.replaceAll('\\'+SpanParser.ITALIC,'')
    }
}
