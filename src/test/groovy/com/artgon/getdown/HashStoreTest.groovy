package com.artgon.getdown

/**
 * Test the HashStore
 *
 * @author Arthur Gonigberg
 * @since 11-10-26
 */
class HashStoreTest extends GroovyTestCase {
    def hashStore = new HashStore()

    def void testOneLineP() {
        def p = "<p>test</p>"
        def hash = hashStore.hashHtmlBlocks(new TextContainer(p)).toString()
        assertFalse(p.equalsIgnoreCase(hash))
    }

    def void testMultiLineP() {
        def p = '<p>test\ntest2\ntest3</p>'
        def hash = hashStore.hashHtmlBlocks(new TextContainer(p)).toString()
        assertFalse(p.equalsIgnoreCase(hash))
    }
}
