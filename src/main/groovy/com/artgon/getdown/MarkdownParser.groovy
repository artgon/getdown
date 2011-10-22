package com.artgon.getdown
/**
 * This is the main parser class where everything begins...
 *
 * @author Arthur Gonigberg
 * @since 11-10-21
 */
class MarkdownParser {
    def parseText(String fileText) {
        def resultList = []
        fileText.split('\n\n').collect(resultList, { x ->
            def p = new BlockParser()
            p.parseBlock(x)
        } )

        def converter = new HtmlConverter()
        converter.convertBlocks(resultList)
    }
}
