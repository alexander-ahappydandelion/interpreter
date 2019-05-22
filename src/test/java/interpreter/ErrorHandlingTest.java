package interpreter;

import org.junit.Test;

import java.awt.image.AreaAveragingScaleFilter;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import static org.junit.Assert.assertEquals;

public class ErrorHandlingTest {
    @Test
    public void binaryExpressionWithoutBrackets() {
        List<String> lines = Collections.singletonList("2+3");

        String result = Interpreter.interpret(lines);

        assertEquals("SYNTAX ERROR", result);
    }

    @Test
    public void constantExpressionWithBrackets() {
        List<String> lines = Collections.singletonList("(-1)");

        String result = Interpreter.interpret(lines);

        assertEquals("SYNTAX ERROR", result);
    }

    @Test
    public void incorrectLineWithCorrectBeggining() {
        List<String> lines = Collections.singletonList("(1+3)blablabla");

        String result = Interpreter.interpret(lines);

        assertEquals("SYNTAX ERROR", result);
    }

    @Test
    public void declareFunctionWithoutParameters() {
        List<String> lines = Arrays.asList("g()={3}",
                "239");

        String result = Interpreter.interpret(lines);

        assertEquals("SYNTAX ERROR", result);
    }

    @Test
    public void callUndefinedFunctionFromBody() {
        List<String> lines = Collections.singletonList("g(10)");

        String result = Interpreter.interpret(lines);

        assertEquals("FUNCTION NOT FOUND g:1", result);
    }

    @Test
    public void callUndefinedFunctionFromAnotherFunction() {
        List<String> lines = Arrays.asList("f(x)={bar(1)}",
                "f(10)");

        String result = Interpreter.interpret(lines);

        assertEquals("FUNCTION NOT FOUND bar:1", result);
    }

    @Test
    public void callUndefinedFunctionWithParameterName() {
        List<String> lines = Arrays.asList("f(x)={x(10)}",
                "f(3)");

        String result = Interpreter.interpret(lines);

        assertEquals("FUNCTION NOT FOUND x:1", result);
    }

    @Test
    public void getUndefinedParameterFromBody() {
        List<String> lines = Collections.singletonList("(x+1)");

        String result = Interpreter.interpret(lines);

        assertEquals("PARAMETER NOT FOUND x:1", result);
    }

    @Test
    public void getUndefinedParameterAnotherFunction() {
        List<String> lines = Arrays.asList("f(x)={g(x)}",
                "g(x)={(x+y)}",
                "f(10)");

        String result = Interpreter.interpret(lines);

        assertEquals("PARAMETER NOT FOUND y:2", result);
    }

    @Test
    public void getUndefinedParameterWithFunctionName() {
        List<String> lines = Arrays.asList("g(x)={(g+3)}",
                "g(10)");

        String result = Interpreter.interpret(lines);

        assertEquals("PARAMETER NOT FOUND g:1", result);
    }

    @Test
    public void divisionByZeroInBody() {
        List<String> lines = Collections.singletonList("(1/0)");

        String result = Interpreter.interpret(lines);

        assertEquals("RUNTIME ERROR (1/0):1", result);
    }

    @Test
    public void divisionByZeroInFunction() {
        List<String> lines = Arrays.asList("f(x)={(2/x)}",
                "f(0)");

        String result = Interpreter.interpret(lines);

        assertEquals("RUNTIME ERROR (2/x):1", result);
    }

    @Test
    public void complexDivisionByZero() {
        List<String> lines = Arrays.asList("f(x)={(2-(1/(1-(1+x))))}",
                "f(0)");

        String result = Interpreter.interpret(lines);

        assertEquals("RUNTIME ERROR (1/(1-(1+x))):1", result);
    }
}
