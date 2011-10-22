package com.artgon.getdown

import org.junit.Test
import static groovy.util.GroovyTestCase.assertEquals

/**
 * Testing the markdown parser
 *
 * @author Arthur Gonigberg
 * @since 11-10-22
 */
class MarkdownParserTest {

    @Test
    def void testParser() {
        def markdown =
"""Arthur
======

is cool

>man
dude
guy

*buddy*"""

        def html =
"""<h1>Arthur</h1>

<p>is cool</p>

<blockquote>
  <p>man
dude
guy</p>
</blockquote>

<p><em>buddy</em></p>"""

        runTest(markdown, html )
    }

    def runTest( String markdown, String html )
    {
        def parser = new MarkdownParser()
        assertEquals( html, parser.parseText(markdown))
    }
}
