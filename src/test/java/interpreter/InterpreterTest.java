package interpreter;

import org.junit.Test;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import static interpreter.Interpreter.interpret;
import static org.junit.jupiter.api.Assertions.*;

public class InterpreterTest {
    @Test
    public void interpretNumber() {
        List<String> program = Collections.singletonList("64");

        String result = interpret(program);

        assertEquals("64", result);
    }

    @Test
    public void interpretNegativeNumber() {
        List<String> program = Collections.singletonList("-98");

        String result = interpret(program);

        assertEquals("-98", result);
    }

    @Test
    public void interpretAddition() {
        List<String> program = Collections.singletonList("(3+-7)");

        String result = interpret(program);

        assertEquals("-4", result);
    }

    @Test
    public void interpretDivision() {
        List<String> program = Collections.singletonList("(15/4)");

        String result = interpret(program);

        assertEquals("3", result);
    }

    @Test
    public void interpretEqualsTrue() {
        List<String> program = Collections.singletonList("(3=3)");

        String result = interpret(program);

        assertEquals("1", result);
    }

    @Test
    public void interpretEqualsFalse() {
        List<String> program = Collections.singletonList("(3=4)");

        String result = interpret(program);

        assertEquals("0", result);
    }

    @Test
    public void interpretGreaterTrue() {
        List<String> program = Collections.singletonList("(4>3)");

        String result = interpret(program);

        assertEquals("1", result);
    }

    @Test
    public void interpretGreaterFalse() {
        List<String> program = Collections.singletonList("(3>4)");

        String result = interpret(program);

        assertEquals("0", result);
    }

    @Test
    public void interpretLessTrue() {
        List<String> program = Collections.singletonList("(3<4)");

        String result = interpret(program);

        assertEquals("1", result);
    }

    @Test
    public void interpretModulo() {
        List<String> program = Collections.singletonList("(17%7)");

        String result = interpret(program);

        assertEquals("3", result);
    }

    @Test
    public void interpretLessMultiplication() {
        List<String> program = Collections.singletonList("(2*-5)");

        String result = interpret(program);

        assertEquals("-10", result);
    }

    @Test
    public void interpretSubstraction() {
        List<String> program = Collections.singletonList("(3-5)");

        String result = interpret(program);

        assertEquals("-2", result);
    }

    @Test
    public void interpretLessFalse() {
        List<String> program = Collections.singletonList("(4<3)");

        String result = interpret(program);

        assertEquals("0", result);
    }

    @Test
    public void interpretIncidentMinus() {
        List<String> program = Collections.singletonList("(1--1)");

        String result = interpret(program);

        assertEquals("2", result);
    }

    @Test
    public void interpretCompositeOfBinaryOperation() {
        List<String> program = Collections.singletonList("(-5+((2*5)/3))");

        String result = interpret(program);

        assertEquals("-2", result);
    }

    @Test
    public void interpretIfExpressionTrueCondition() {
        List<String> program = Collections.singletonList("[(2<3)]?(239):(30)");

        String result = interpret(program);

        assertEquals("239", result);
    }

    @Test
    public void interpretIfExpressionFalseCondition() {
        List<String> program = Collections.singletonList("[(2>3)]?(239):(30)");

        String result = interpret(program);

        assertEquals("30", result);
    }

    @Test
    public void interpretIdFunction() {
        List<String> program = Arrays.asList("id(x)={x}",
                "id(45)");

        String result = interpret(program);

        assertEquals("45", result);
    }

    @Test
    public void interpretFactorialFunction() {
        List<String> program = Arrays.asList("fact(x)={[(x>1)]?((x*fact((x-1)))):(1)}",
                "fact(5)");

        String result = interpret(program);

        assertEquals("120", result);
    }

    @Test
    public void interpretFunctionMultipleParameters() {
        List<String> program = Arrays.asList("magic(x,y,z)={(x+(y*z))}",
                "magic(3,5,-2)");

        String result = interpret(program);

        assertEquals("-7", result);
    }

    @Test
    public void interpretMultipleFunctions() {
        List<String> program = Arrays.asList("one(x)={(x+1)}",
                "two(x)={(x*2)}",
                "three(x)={(x/3)}",
                "((one(2)+two(-1))+three(5))");

        String result = interpret(program);

        assertEquals("2", result);
    }

    @Test
    public void interpretCallExpressionInFunction() {
        List<String> program = Arrays.asList("foo(x)={[(x>1)]?((bar((x/2))+1)):(0)}",
                "bar(x)={[(x>1)]?((foo((x-1))+1)):(0)}",
                "bar(7)");

        String result = interpret(program);

        assertEquals("4", result);
    }

    @Test
    public void interpretCallExpressionInBody() {
        List<String> program = Arrays.asList("one(x)={(x+1)}",
                "two(x)={(x*2)}",
                "three(x)={(x/3)}",
                "three(two(one(4)))");

        String result = interpret(program);

        assertEquals("3", result);
    }
}