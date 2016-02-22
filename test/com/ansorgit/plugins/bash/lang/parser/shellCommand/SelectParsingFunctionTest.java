package com.ansorgit.plugins.bash.lang.parser.shellCommand;

import com.ansorgit.plugins.bash.lang.BashVersion;
import com.ansorgit.plugins.bash.lang.parser.BashPsiBuilder;
import com.ansorgit.plugins.bash.lang.parser.MockPsiTest;
import com.ansorgit.plugins.bash.lang.parser.Parsing;
import org.junit.Test;

import java.util.Collections;

@SuppressWarnings("Duplicates")
public class SelectParsingFunctionTest extends MockPsiTest {
    MockPsiTest.MockFunction loopParser = new MockPsiTest.MockFunction() {
        @Override
        public boolean apply(BashPsiBuilder psi) {
            return Parsing.shellCommand.selectParser.parse(psi);
        }
    };

    @Test
    public void testSelectLoop() {
        //select f in 1; do {
        //echo 1
        //}
        // done
        mockTest(loopParser, SELECT_KEYWORD, WORD, IN_KEYWORD, INTEGER_LITERAL, SEMI, DO_KEYWORD, LEFT_CURLY, LINE_FEED,
                WORD, WHITESPACE, WORD, LINE_FEED, RIGHT_CURLY, WHITESPACE, LINE_FEED, DONE_KEYWORD);

        //select f in 1; do {
        //echo 1
        //};
        // done
        mockTest(loopParser, SELECT_KEYWORD, WORD, IN_KEYWORD, INTEGER_LITERAL, SEMI, DO_KEYWORD, LEFT_CURLY, LINE_FEED,
                WORD, WHITESPACE, WORD, LINE_FEED, RIGHT_CURLY, SEMI, LINE_FEED, DONE_KEYWORD);

        //select A do echo $A; done
        mockTest(loopParser, SELECT_KEYWORD, WORD, DO_KEYWORD, WHITESPACE, WORD, VARIABLE, SEMI, DONE_KEYWORD);
    }

    @Test
    public void testErrors() throws Exception {
        //  select f in 1; do {
        //      echo 1
        //  } done
        //missing terminator after the body
        mockTestError(loopParser, SELECT_KEYWORD, WORD, IN_KEYWORD, INTEGER_LITERAL, SEMI, DO_KEYWORD, LEFT_CURLY, LINE_FEED,
                WORD, WHITESPACE, WORD, LINE_FEED, RIGHT_CURLY, WHITESPACE, DONE_KEYWORD);


        //select f in 1; do {
        //echo 1
        //} done
        //missing terminator after the body
        mockTestError(loopParser, SELECT_KEYWORD, WORD, IN_KEYWORD, INTEGER_LITERAL, SEMI, DO_KEYWORD, LEFT_CURLY, LINE_FEED,
                WORD, WHITESPACE, WORD, LINE_FEED, RIGHT_CURLY, WHITESPACE, DONE_KEYWORD);

    }

    @Test
    public void testIncompleteParse() throws Exception {
        //error markers must be present, but the incomplete if should be parsed without remaining elements

        // select f in a; do; done
        mockTestError(BashVersion.Bash_v3, loopParser, false, true, Collections.<String>emptyList(), SELECT_KEYWORD, WORD, IN_KEYWORD, WORD, SEMI, DO_KEYWORD, SEMI, DONE_KEYWORD);

        //select a in; do echo; done
        mockTestError(BashVersion.Bash_v3, loopParser, false, true, Collections.<String>emptyList(), SELECT_KEYWORD, WORD, IN_KEYWORD, SEMI, DO_KEYWORD, WORD, DONE_KEYWORD);
    }
}