package expression;

import exceptions.ArgumentNumberMismathcException;
import exceptions.FunctionNotFoundException;
import exceptions.ParameterNotFoundException;
import exceptions.RuntimeErrorException;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import context.Context;

public class ConstantExpressionTest {
    private Context context;

    @Before
    public void setUp() {
        context = new Context();
    }

    @Test
    public void evaluateIntNumber() {
        Expression expression = new ConstantExpression(-239, 0);

        try {
            Assert.assertEquals(-239, expression.evaluate(context));
        } catch (RuntimeErrorException | FunctionNotFoundException | ArgumentNumberMismathcException | ParameterNotFoundException ignored) {
        }
    }

    @Test
    public void evaluatePositiveStringNumber() {
        Expression expression = new ConstantExpression("67", 0);

        try {
            Assert.assertEquals(67, expression.evaluate(context));
        } catch (RuntimeErrorException | FunctionNotFoundException | ArgumentNumberMismathcException | ParameterNotFoundException ignored) {
        }
    }

    @Test
    public void evaluateNegativeStringNumber() {
        Expression expression = new ConstantExpression("-45", 0);

        try {
            Assert.assertEquals(-45, expression.evaluate(context));
        } catch (RuntimeErrorException | FunctionNotFoundException | ArgumentNumberMismathcException | ParameterNotFoundException ignored) {
        }
    }

}