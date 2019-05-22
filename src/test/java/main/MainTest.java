package main;

import org.junit.Test;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

public class MainTest {
    @Test
    public void interpreteNumber() {
        List<String> program = Collections.singletonList("64");

        String result = Main.interprate(program);

        assertEquals("64", result);
    }

    @Test
    public void interpreteNegativeNumber() {
        List<String> program = Collections.singletonList("-98");

        String result = Main.interprate(program);

        assertEquals("-98", result);
    }

    @Test
    public void interpreteAddition() {
        List<String> program = Collections.singletonList("(3+-7)");

        String result = Main.interprate(program);

        assertEquals("-4", result);
    }

    @Test
    public void interpreteDivision() {
        List<String> program = Collections.singletonList("(15/4)");

        String result = Main.interprate(program);

        assertEquals("3", result);
    }

    @Test
    public void interpreteEqualsTrue() {
        List<String> program = Collections.singletonList("(3=3)");

        String result = Main.interprate(program);

        assertEquals("1", result);
    }

    @Test
    public void interpreteEqualsFalse() {
        List<String> program = Collections.singletonList("(3=4)");

        String result = Main.interprate(program);

        assertEquals("0", result);
    }

    @Test
    public void interpreteGreaterTrue() {
        List<String> program = Collections.singletonList("(4>3)");

        String result = Main.interprate(program);

        assertEquals("1", result);
    }

    @Test
    public void interpreteGreaterFalse() {
        List<String> program = Collections.singletonList("(3>4)");

        String result = Main.interprate(program);

        assertEquals("0", result);
    }

    @Test
    public void interpreteLessTrue() {
        List<String> program = Collections.singletonList("(3<4)");

        String result = Main.interprate(program);

        assertEquals("1", result);
    }

    @Test
    public void interpreteModulo() {
        List<String> program = Collections.singletonList("(17%7)");

        String result = Main.interprate(program);

        assertEquals("3", result);
    }

    @Test
    public void interpreteLessMultiplication() {
        List<String> program = Collections.singletonList("(2*-5)");

        String result = Main.interprate(program);

        assertEquals("-10", result);
    }

    @Test
    public void interpreteSubstraction() {
        List<String> program = Collections.singletonList("(3-5)");

        String result = Main.interprate(program);

        assertEquals("-2", result);
    }

    @Test
    public void interpreteLessFalse() {
        List<String> program = Collections.singletonList("(4<3)");

        String result = Main.interprate(program);

        assertEquals("0", result);
    }

    @Test
    public void interpreteIncidentMinus() {
        List<String> program = Collections.singletonList("(1--1)");

        String result = Main.interprate(program);

        assertEquals("2", result);
    }

    @Test
    public void interpreteCompositeOfBinaryOperation() {
        List<String> program = Collections.singletonList("(-5+((2*5)/3))");

        String result = Main.interprate(program);

        assertEquals("-2", result);
    }

    @Test
    public void interpreteIfExpressionTrueCondition() {
        List<String> program = Collections.singletonList("[(2<3)]?(239):(30)");

        String result = Main.interprate(program);

        assertEquals("239", result);
    }

    @Test
    public void interpreteIfExpressionFalseCondition() {
        List<String> program = Collections.singletonList("[(2>3)]?(239):(30)");

        String result = Main.interprate(program);

        assertEquals("30", result);
    }

    @Test
    public void interpreteIdFunction() {
        List<String> program = Arrays.asList("id(x)={x}",
                "id(45)");

        String result = Main.interprate(program);

        assertEquals("45", result);
    }

    @Test
    public void interpreteFactorialFunction() {
        List<String> program = Arrays.asList("fact(x)={[(x>1)]?((x*fact((x-1)))):(1)}",
                "fact(5)");

        String result = Main.interprate(program);

        assertEquals("120", result);
    }

    @Test
    public void interpreteFunctionMultipleParameters() {
        List<String> program = Arrays.asList("magic(x,y,z)={(x+(y*z))}",
                "magic(3,5,-2)");

        String result = Main.interprate(program);

        assertEquals("-7", result);
    }

    @Test
    public void interpreteMultipleFunctions() {
        List<String> program = Arrays.asList("one(x)={(x+1)}",
                "two(x)={(x*2)}",
                "three(x)={(x/3)}",
                "((one(2)+two(-1))+three(5))");

        String result = Main.interprate(program);

        assertEquals("2", result);
    }

    @Test
    public void interpreteCallExpressionInFunction() {
        List<String> program = Arrays.asList("foo(x)={[(x>1)]?((bar((x/2))+1)):(0)}",
                "bar(x)={[(x>1)]?((foo((x-1))+1)):(0)}",
                "bar(7)");

        String result = Main.interprate(program);

        assertEquals("4", result);
    }

    @Test
    public void interpreteCallExpressionInBody() {
        List<String> program = Arrays.asList("one(x)={(x+1)}",
                "two(x)={(x*2)}",
                "three(x)={(x/3)}",
                "three(two(one(4)))");

        String result = Main.interprate(program);

        assertEquals("3", result);
    }
}