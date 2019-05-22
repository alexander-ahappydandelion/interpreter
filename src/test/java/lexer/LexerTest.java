package lexer;

import exceptions.SintaxErrorException;
import org.junit.Assert;
import org.junit.Test;
import tokens.Token;

import java.util.List;

import static org.junit.Assert.fail;

public class LexerTest {
    @Test
    public void tokenizeNullProgrammStringReturnsEmptyList() {
        String programmString = null;

        try {
            List<Token> tokens = Lexer.tokenizeLine(programmString, 0);

            Assert.assertEquals(0, tokens.size());
        } catch (SintaxErrorException ignored) {
        }
    }

    @Test
    public void tokenizeEmptyProgrammStringReturnsEmptyList() {
        String programmString = "";

        try {
            List<Token> tokens = Lexer.tokenizeLine(programmString, 0);

            Assert.assertEquals(0, tokens.size());
        } catch (SintaxErrorException ignored) {
        }
    }

    @Test
    public void tokenizeSingleIdentifierReturnsSingleElementList() {
        String programmString = "_aGthkly23hg_7gdsD";

        try {
            List<Token> tokens = Lexer.tokenizeLine(programmString, 0);

            Assert.assertEquals(1, tokens.size());
        } catch (SintaxErrorException ignored) {
        }
    }

    @Test
    public void tokenizeSingleIdentifierReturnsCorrectTokenType() {
        String programmString = "_aGthkly23hg_7gdsD";

        try {
            List<Token> tokens = Lexer.tokenizeLine(programmString, 0);

            Assert.assertTrue(tokens.get(0).isIdentifier());
        } catch (SintaxErrorException ignored) {
        }
    }

    @Test
    public void tokenizeSingleIdentifierReturnsCorrectTokenValue() {
        String programmString = "_aGthkly23hg_7gdsD";

        try {
            List<Token> tokens = Lexer.tokenizeLine(programmString, 0);

            Assert.assertEquals(programmString, tokens.get(0).getValue());
        } catch (SintaxErrorException ignored) {
        }
    }

    @Test
    public void tokenizeSingleNumberReturnsSingleElementList() {
        String programmString = "_aGthkly23hg_7gdsD";

        try {
            List<Token> tokens = Lexer.tokenizeLine(programmString, 0);

            Assert.assertEquals(1, tokens.size());
        } catch (SintaxErrorException ignored) {
        }
    }

    @Test
    public void tokenizeSingleNumericReturnsCorrectTokenType() {
        String programmString = "_aGthkly23hg_7gdsD";

        try {
            List<Token> tokens = Lexer.tokenizeLine(programmString, 0);

            Assert.assertTrue(tokens.get(0).isNumber());
        } catch (SintaxErrorException ignored) {
        }
    }

    @Test
    public void tokenizeSingleNumberReturnsCorrectTokenValue() {
        String programmString = "_aGthkly23hg_7gdsD";

        try {
            List<Token> tokens = Lexer.tokenizeLine(programmString, 0);

            Assert.assertEquals(programmString, tokens.get(0).getValue());
        } catch (SintaxErrorException ignored) {
        }
    }

    @Test
    public void tokenizePlusBinaryOperationReturnsCorrectTokenValue() {
        String programString = "+";

        try {
            List<Token> tokens = Lexer.tokenizeLine(programString, 0);

            Assert.assertEquals("+", tokens.get(0).getValue());
        } catch (SintaxErrorException ignored) {
            fail();
        }
    }

    @Test
    public void tokenizeOpenRoundBracketReturnsCorrectTokenValue() {
        String programString = "(";

        try {
            List<Token> tokens = Lexer.tokenizeLine(programString, 0);

            Assert.assertEquals("(", tokens.get(0).getValue());
        } catch (SintaxErrorException ignored) {
            fail();
        }
    }

    @Test
    public void tokenizeCloseRoundBracketReturnsCorrectTokenValue() {
        String programString = ")";

        try {
            List<Token> tokens = Lexer.tokenizeLine(programString, 0);

            Assert.assertEquals(")", tokens.get(0).getValue());
        } catch (SintaxErrorException ignored) {
            fail();
        }
    }
}
