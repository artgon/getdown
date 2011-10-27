package com.artgon.getdown

/**
 * This class parses the Markdown block elements
 *
 * @author Arthur Gonigberg
 * @since 11-10-22
 */
class BlockParser {
    HashStore hashStore

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
           */
        hashStore.hashHtmlBlocks(t)
        /*
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
        /*
        Regex description:
            This regex grabs the first instance of ">", potentially
            surrounded by space/tab and any subsequent lines
         */
        t.replaceAll('((^[ \t]*>[ \t]?.+\n(.+\n)*\n*)+)') {groups ->
            def bq = groups[0].replaceAll('^[ \t]*>[ \t]?', '') // strip the first line's '>'
            bq = bq.replaceAll('^[ \t]+$', '') // remove all whitespace lines
            bq = parseBlocks(new TextContainer(bq)) // recurse through block parser
            "<blockquote>\n$bq\n</blockquote>\n\n"
        }
    }

    def formatParagraph(TextContainer t) {
        /*
           7.7.1. strip leading/trailing lines
           */
        t.replaceAll('\\A\n+', '')
        t.replaceAll('\n+\\Z', '')

        /*
           7.7.2. split into blocks using \n\n
               7.7.2.1. for each block, wrap in <p> unless it's hashed
               7.7.2.2. parse spans
           7.7.3. unhash html blocks
           */
        def blocks = t.toString().split('\n\n').inject([]) {results, block ->
            if (!block.isEmpty()) {
                if (hashStore.containsKey(block)) {
                    results << hashStore.unHashHtmlBlocks(block)
                }
                else {
                    def parsedBlock = spanParser.parseText(block)
                    results << "<p>$parsedBlock</p>"
                }
            }
            else {
                results
            }
        }
        /*
           7.7.4. put blocks back together separated by \n\n
         */
        def sb = new StringBuilder()
        blocks.eachWithIndex { block, index ->
            if (index == blocks.size() - 1) {
                sb = sb + block
            }
            else {
                sb = sb + block + '\n\n'
            }
        }
        sb.toString()
    }
}
