package com.artgon.getdown

/**
 * This class wraps strings in appropriate html tags
 *
 * @author Arthur Gonigberg
 * @since 11-10-22
 */
class HtmlConverter {
    def convertBlocks(Collection blocks) {
        def sb = new StringBuilder()
        def last = blocks.size() - 1
        blocks.eachWithIndex { block, index ->
            if (index == last) {
                sb = sb + block
            }
            else {
                sb = sb + block + '\n\n'
            }
        }
        sb.toString()
    }

    def covertHeader(String s, int size) {
        "<h${size}>${s}</h${size}>"
    }

    def covertBlockquote(String s) {
        "<blockquote>${nest(convertParagraph(s))}</blockquote>"
    }

    def convertParagraph(String s) {
        "<p>${s}</p>"
    }

    def nest(String s) {
        "\n  ${s}\n"
    }

    def convertBold(String s) {
        "<b>${s}</b>"
    }

    def convertItalic(String s) {
        "<em>${s}</em>"
    }
}
