package com.artgon.getdown

/**
 * This class wraps strings in appropriate html tags
 *
 * @author Arthur Gonigberg
 * @since 11-10-22
 */
class HtmlConverter {
    def convertBlocks(Collection blocks) {
        def last = blocks.size() - 1
        def secondLast = blocks.size() - 2

        StringBuilder sb = new StringBuilder()
        // loop all blocks until second last
        for (i in 0..secondLast) {
            sb.append(blocks[i]).append('\n\n')
        }
        // add the last without a newline
        sb.append(blocks[last])
        sb.toString()
    }

    def covertHeader1(String s) {
        "<h1>" + s + "</h1>"
    }

    def covertHeader2(String s) {
        "<h2>" + s + "</h2>"
    }

    def covertBlockquote(String s) {
        "<blockquote>" + nest(convertParagraph(s)) + "</blockquote>"
    }

    def convertParagraph(String s) {
        "<p>" + s + "</p>"
    }

    def nest(String s) {
        "\n  " + s + "\n"
    }

    def convertBold(String s) {
        "<b>" + s + "</b>"
    }

    def convertItalic(String s) {
        "<em>" + s + "</em>"
    }
}
