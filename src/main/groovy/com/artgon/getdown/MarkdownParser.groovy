package com.artgon.getdown
/**
 * This is the main parser class where everything begins...
 *
 * @author Arthur Gonigberg
 * @since 11-10-21
 */
class MarkdownParser {
    static final TAB_WIDTH = 4

    def parseText(String fileText) {
        def t = new TextContainer(fileText)
        /*
         1. standardize line endings
         */
        t.replaceAll(/\r\n/,'\n') // DOS
        t.replaceAll(/\r/,'\n') // Mac
        /*
         2. make sure text ends with \n\n
         */
        t << /\n\n/
        /*
         3. convert all tabs to spaces
         */
        tabsToSpaces(t)
        /*
         4. strip lines consisting of only spaces/tabs (for easier regex)
         */
        t.replaceAll(/^[ \t]+$/,'')
        /*
         5. hash html blocks
         6. hash links
         7. parse blocks
         */
        def p = new BlockParser()
        p.parseBlocks(t)
        /*
         8. unescape special chars
         */


/*        def parser = new BlockParser()
        def blocks = t.toString().split('\n\n').inject([], {results, block ->
            results << parser.parseBlocks(block)
        })

        def sb = new StringBuilder()
        blocks.eachWithIndex { block, index ->
            if (index == blocks.size() - 1 ) {
                sb = sb + block
            }
            else {
                sb = sb + block + '\n\n'
            }
        }
        sb.toString()
        */
    }

    def tabsToSpaces(TextContainer t) {
        t.replaceAll(/(.*?)\t/) {all, line ->
            line + (' ' * (TAB_WIDTH-line.length()%TAB_WIDTH))
        }
    }
}
