package com.artgon.getdown
/**
 * This is the main parser class where everything begins...
 *
 * @author Arthur Gonigberg
 * @since 11-10-21
 */
class MarkdownParser {
    def parseText(String fileText) {
        def parser = new BlockParser()
        new HtmlConverter().convertBlocks(
                fileText.split('\n\n').inject([]) {results, block ->
                    results << parser.parseBlock(block)
                }
        )
    }
}
