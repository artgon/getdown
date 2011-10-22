package com.artgon.getdown

/**
 * This class parses the Markdown span elements
 *
 * @author Arthur Gonigberg
 * @since 11-10-22
 */
class SpanParser {
    static final ITALIC = "*"
    static final BOLD = "**"

    def converter = new HtmlConverter()
    def stripper = new MarkdownStripper()

    def parseSpan(String span) {
        if (span.startsWith(BOLD)) {
            converter.convertBold(stripper.stripEmphasis(span))
        }
        else if (span.startsWith(ITALIC)) {
            converter.convertItalic(stripper.stripEmphasis(span))
        }
        else {
            span
        }
    }
}
