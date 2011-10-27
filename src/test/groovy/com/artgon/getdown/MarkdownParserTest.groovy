package com.artgon.getdown

/**
 * Testing the markdown parser
 *
 * @author Arthur Gonigberg
 * @since 11-10-22
 */
class MarkdownParserTest extends GroovyTestCase {

    def void testParser() {
        def markdown =
"""Arthur
======

<p>test</p>

is cool

>man
dude
guy

*buddy*"""

        def html =
"""<h1>Arthur</h1>

<p>test</p>

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
