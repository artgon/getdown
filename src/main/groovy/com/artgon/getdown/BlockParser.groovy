package com.artgon.getdown

/**
 * This class parses the Markdown block elements
 *
 * @author Arthur Gonigberg
 * @since 11-10-22
 */
class BlockParser {
    def spanParser = new SpanParser()

    def parseBlocks(TextContainer t) {
        /*
           7.1. headers
           */
        replaceHeader(t)
        /*
           7.2. horizontal rules
           7.3. lists
           7.4. code blocks
           7.5. block quotes
           */
        replaceBlockquote(t)
        /*
           7.6. hash html blocks again (i.e. the markup we just created)
           7.7. form paragraphs
        */
        // replace the block elements after
        formatParagraph(t)
    }

    def replaceHeader(TextContainer t) {
        t.replaceAll(/(.*)\n[=]+/, "<h1>\$1</h1>")
        t.replaceAll(/(.*)\n[-]+/, "<h2>\$1</h2>")
    }

    def replaceBlockquote(TextContainer t) {
        t
    }

    def formatParagraph(TextContainer t) {
        /*
           7.7.1. strip leading/trailing lines
           7.7.2. split into blocks using \n\n
               7.7.2.1. for each block, wrap in <p> unless it's hashed
               7.7.2.2. parse spans
           */
        spanParser.parseText(t)
        /*
           7.7.3. unhash html blocks
           7.7.4. put blocks back together separated by \n\n
         */
    }
}
