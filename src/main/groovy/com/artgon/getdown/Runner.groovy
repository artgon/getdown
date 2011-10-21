package com.artgon.getdown
/**
 * This class is for CLI execution
 *
 * @author Arthur Gonigberg
 * @since 11-10-21
 */
class Runner {
    static void main(String[] args) {
        def parser = new MarkdownParser()
        parser.parseText(new File(args[0]).readLines())
    }
}
