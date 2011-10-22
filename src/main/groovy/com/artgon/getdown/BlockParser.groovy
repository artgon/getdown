package com.artgon.getdown

/**
 * This class parses the Markdown block elements
 *
 * @author Arthur Gonigberg
 * @since 11-10-22
 */
class BlockParser {
    static final HEADER_1 = "="
    static final HEADER_2 = "-"
    static final BLOCKQUOTE = ">"

    def converter = new HtmlConverter()
    def spanParser = new SpanParser()
    def stripper = new MarkdownStripper()

    def parseBlock(String block) {
        if (block.endsWith(HEADER_1)) {
            converter.covertHeader(spanParser.parseSpan(stripper.stripHeader(block)),1)
        }
        else if (block.endsWith(HEADER_2)) {
            converter.covertHeader(spanParser.parseSpan(stripper.stripHeader(block)),2)
        }
        else if (block.startsWith(BLOCKQUOTE)) {
            converter.covertBlockquote(spanParser.parseSpan(stripper.stripBlockquote(block)))
        }
        else {
            converter.convertParagraph(spanParser.parseSpan(block))
        }
    }
}
