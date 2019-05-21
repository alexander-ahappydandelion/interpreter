package parser;

import exceptions.SintaxErrorException;
import expression.ConstantExpression;
import expression.Expression;
import org.junit.Assert;
import org.junit.Test;
import tokens.NumberToken;
import tokens.Token;
import static org.hamcrest.CoreMatchers.instanceOf;

import java.util.Arrays;
import java.util.List;

public class ParserTest {
    @Test
    public void parseNullListReturnsNullExpression() {
        List<Token> tokens = null;

        try {
            Expression expression = Parser.parseExpressionLine(tokens, 0);

            Assert.assertNull(expression);
        } catch (SintaxErrorException ignored){
        }
    }

    @Test
    public void parseNumberReturnsConstantExpression() {
        Token constantToken = new NumberToken("239", 0);

        List<Token> tokens = Arrays.asList(constantToken);

        try {
            Expression expression = Parser.parseExpressionLine(tokens, 0);

            Assert.assertThat(expression, instanceOf(ConstantExpression.class));
        } catch (SintaxErrorException ignored) {
        }
    }



}