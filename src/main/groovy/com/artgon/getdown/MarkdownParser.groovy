package com.artgon.getdown

/**
 * This is the main parser class where everything begins...
 *
 * @author Arthur Gonigberg
 * @since 11-10-21
 */
class MarkdownParser {
    static final TAB_WIDTH = 4

    def hashStore = new HashStore()

    def parseText(String fileText) {
        def t = new TextContainer(fileText)
        /*
         1. standardize line endings
         */
        t.replaceAll(/\r\n/, '\n') // DOS
        t.replaceAll(/\r/, '\n') // Mac
        /*
         2. make sure text ends with \n\n
         */
        t << '\n\n'
        /*
         3. convert all tabs to spaces
         */
        tabsToSpaces(t)
        /*
         4. strip lines consisting of only spaces/tabs (for easier regex)
         */
        t.replaceAll(/^[ \t]+$/, '')
        /*
         5. hash html blocks
         */
        hashStore.hashHtmlBlocks(t)
        /*
         6. hash links
         7. parse blocks
         */
        def p = new BlockParser(hashStore: hashStore)
        p.parseBlocks(t)
        /*
         8. unescape special chars
         */
    }

    def tabsToSpaces(TextContainer t) {
        t.replaceAll(/(.*?)\t/) {all, line ->
            line + (' ' * (TAB_WIDTH - line.length() % TAB_WIDTH))
        }
    }
}
