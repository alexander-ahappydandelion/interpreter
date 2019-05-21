package grammar;

import operation.*;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

public class GrammarHelper {
    private static final Set<String> functional;
    private static final Set<String> binaryOperations;

    static {
        functional = new HashSet<>(Arrays.asList(
                "+", "-", "*", "/", "%",
                ">", "<", "=",
                "?", ":",
                "(", ")", "[", "]", "{", "}",
                ","
        ));

        binaryOperations = new HashSet<>(Arrays.asList(
                "+", "-", "*", "/", "%", ">", "<", "="
        ));
    }

    public static boolean isCharacter(char c) {
        return c == '_'
                || 'A' <= c && c <= 'Z'
                || 'a' <= c && c <= 'z';
    }

    public static boolean isDigit(char c) {
        return '0' <= c && c <= '9';
    }

    public static boolean isFunctional(Character c) {
        return functional.contains(c.toString());
    }

    public static boolean isIdentifier(String string) {
        for (char c : string.toCharArray()) {
            if (!isCharacter(c)) {
                return false;
            }
        }

        return true;
    }

    public static boolean isNumber(String string) {
        for (char c : string.toCharArray()) {
            if (!isDigit(c)) {
                return false;
            }
        }

        return true;
    }

    public static boolean isBinaryOperation(String string) {
        return binaryOperations.contains(string);
    }

    public static boolean isOpenRoundBracket(String string) {
        return "(".equals(string);
    }

    public static boolean isCloseRoundBracket(String string) {
        return ")".equals(string);
    }

    public static boolean isOpenBoxBracket(String string) {
        return "[".equals(string);
    }

    public static boolean isCloseBoxBracket(String string) {
        return "]".equals(string);
    }

    public static boolean isOpenCurlyBracket(String string) {
        return "{".equals(string);
    }

    public static boolean isCloseCurlyBracket(String string) {
        return "}".equals(string);
    }

    public static boolean isQuestionMark(String string) {
        return "?".equals(string);
    }

    public static boolean isColon(String string) {
        return ":".equals(string);
    }

    public static boolean isUnaryMinus(String string) {
        return "-".equals(string);
    }

    public static boolean isAssignment(String string) {
        return "=".equals(string);
    }

    public static boolean isComma(String string) {
        return ",".equals(string);
    }

    public static BinaryOperation getBinaryOperationByValue(String value) {
        switch (value) {
            case "+": return new AdditionBinaryOperation();
            case "-": return new SubstractionBinaryOperation();
            case "*": return new MultiplicationBinaryOperation();
            case "/": return new DivisionBinaryOperation();
            case "%": return new ModuloBinaryOperation();
            case ">": return new GreaterBinaryOperation();
            case "<": return new LessBinaryOperation();
            case "=": return new EqualsBinaryOperation();
            default:
                throw new IllegalStateException("Unexpected value: " + value);
        }
    }
}
