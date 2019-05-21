package parser;

import exceptions.SintaxErrorException;
import expression.*;
import function.Function;
import function.FunctionDefinition;
import operation.BinaryOperation;
import grammar.GrammarHelper;
import tokens.Token;

import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.ListIterator;

public class Parser {
    public static Expression parseExpressionLine(List<Token> tokens, int line) throws SintaxErrorException {
        if (tokens == null) {
            return null;
        }

        return parseExpressionLine(tokens.listIterator(), line);
    }

    public static FunctionDefinition parseFunctionDefinitionLine(List<Token> tokens, int line) throws SintaxErrorException {
        if (tokens == null) {
            return null;
        }

        return parseFunctionDefinitionLine(tokens.listIterator(), line);
    }

    private static FunctionDefinition parseFunctionDefinitionLine(ListIterator<Token> tokens, int line) throws SintaxErrorException {
        String identifier = nextIdentifier(tokens);
        Function function = nextFunction(tokens, line);

        checkForEnd(tokens);

        return new FunctionDefinition(identifier, function);
    }

    private static Function nextFunction(ListIterator<Token> tokens, int line) throws SintaxErrorException {
        checkForOpenRoundBracket(tokens, line);

        List<String> parameters = nextParameterList(tokens, line);

        checkForCloseRoundBracket(tokens, line);
        checkForAssignment(tokens, line);
        checkForOpenCurlyBracket(tokens, line);

        Expression bodyExpression = nextExpression(tokens, line);

        checkForCloseCurlyBracket(tokens, line);

        return new Function(parameters, bodyExpression, line);
    }

    private static List<Expression> nextArgumentListWithBrackets(ListIterator<Token> tokens, int line) throws SintaxErrorException {
        List<Expression> arguments = new LinkedList<>();

        checkForOpenRoundBracket(tokens, line);

        Expression argument = nextExpression(tokens, line);
        arguments.add(argument);

        appendArgumentListWithBrackets(arguments, tokens, line);

        return arguments;
    }

    private static void appendArgumentListWithBrackets(List<Expression> arguments, ListIterator<Token> tokens, int line) throws SintaxErrorException {
        if (safeCheckForCloseRoundBracket(tokens)) {
            return;
        }

        checkForComma(tokens);

        Expression argument = nextExpression(tokens, line);
        arguments.add(argument);

        appendArgumentListWithBrackets(arguments, tokens, line);
    }

    private static List<String> nextParameterList(ListIterator<Token> tokens, int line) throws SintaxErrorException {
        if (!tokens.hasNext()) {
            throw new SintaxErrorException();
        }

        List<String> parameterList = new LinkedList<>();
        Token token = tokens.next();

        if (!token.isIdentifier()) {
            throw new SintaxErrorException();
        }

        parameterList.add(token.getValue());
        safeAppendParameterList(parameterList, tokens);

        return parameterList;
    }

    private static void safeAppendParameterList(List<String> parameterList, ListIterator<Token> tokens) {
        if (!safeCheckForComma(tokens)) {
            return;
        }

        if (!tokens.hasNext()) {
            return;
        }

        Token token = tokens.next();

        if (!token.isIdentifier()) {
            tokens.previous(); // take back current token
            tokens.previous(); // take back comma

            return;
        }

        parameterList.add(token.getValue());

        safeAppendParameterList(parameterList, tokens);
    }

    private static Expression parseExpressionLine(ListIterator<Token> tokens, int line) throws SintaxErrorException {
        Expression expression = nextExpression(tokens, line);

        checkForEnd(tokens);

        return expression;
    }

    private static Expression nextExpression(ListIterator<Token> tokens, int line) throws SintaxErrorException {
        if (!tokens.hasNext()) {
            throw new SintaxErrorException();
        }

        Token token = tokens.next();

        if (token.isOpenRoundBracket()) {
            tokens.previous();

            return nextBinaryExpression(tokens, line);
        }

        if (token.isNumber() || token.isUnaryMinus()) {
            tokens.previous();

            return nextConstantExpression(tokens, line);
        }

        if (token.isOpenBoxBracket()) {
            tokens.previous();

            return nextIfExpression(tokens, line);
        }

        if (token.isIdentifier()) {
            if (safeCheckForOpenRoundBracket(tokens)) {
                tokens.previous();
                tokens.previous();

                return nextCallExpression(tokens, line);
            }

            return new IdentifierExpression(token.getValue(), line);
        }

        throw new SintaxErrorException();
    }

    private static CallExpression nextCallExpression(ListIterator<Token> tokens, int line) throws SintaxErrorException {
        String identifier = nextIdentifier(tokens);

        List<Expression> arguments = nextArgumentListWithBrackets(tokens, line);

        return new CallExpression(identifier, arguments, line);
    }

    private static IfExpression nextIfExpression(ListIterator<Token> tokens, int line) throws SintaxErrorException {
        checkForOpenBoxBracket(tokens, line);

        Expression conditionExpression = nextExpression(tokens, line);

        checkForCloseBoxBracket(tokens, line);
        checkForQuestionMark(tokens, line);
        checkForOpenRoundBracket(tokens, line);

        Expression ifBranchExpression = nextExpression(tokens, line);

        checkForCloseRoundBracket(tokens, line);
        checkForColon(tokens, line);
        checkForOpenRoundBracket(tokens, line);

        Expression elseBranchExpression = nextExpression(tokens, line);

        checkForCloseRoundBracket(tokens, line);

        return new IfExpression(conditionExpression, ifBranchExpression, elseBranchExpression, line);
    }

    private static ConstantExpression nextConstantExpression(ListIterator<Token> tokens, int line) throws SintaxErrorException {
        String sign = safeCheckForUnaryMinus(tokens) ? "-" : "";
        String number = nextNumber(tokens);

        return new ConstantExpression(sign + number, line);
    }

    private static String nextIdentifier(ListIterator<Token> tokens) throws SintaxErrorException {
        if (!tokens.hasNext()) {
            throw new SintaxErrorException();
        }

        Token token = tokens.next();

        if (!token.isIdentifier()) {
            throw new SintaxErrorException();
        }

        return token.getValue();
    }

    private static String nextNumber(ListIterator<Token> tokens) throws SintaxErrorException {
        if (!tokens.hasNext()) {
            throw new SintaxErrorException();
        }

        Token token = tokens.next();

        if (!token.isNumber()) {
            throw new SintaxErrorException();
        }

        return token.getValue();
    }

    private static BinaryExpression nextBinaryExpression(ListIterator<Token> tokens, int line) throws SintaxErrorException {
        checkForOpenRoundBracket(tokens, line);

        Expression leftExpression = nextExpression(tokens, line);
        BinaryOperation operation = nextBinaryOperation(tokens, line);
        Expression rightExpression = nextExpression(tokens, line);

        checkForCloseRoundBracket(tokens, line);

        return new BinaryExpression(leftExpression, rightExpression, operation, line);
    }

    private static BinaryOperation nextBinaryOperation(ListIterator<Token> tokens, int line) throws SintaxErrorException {
        if (!tokens.hasNext()) {
            throw new SintaxErrorException();
        }

        Token token = tokens.next();

        if (!token.isBinaryOperation()) {
            throw new SintaxErrorException();
        }

        try {
            return GrammarHelper.getBinaryOperationByValue(token.getValue());
        } catch (IllegalStateException e) {
            throw new SintaxErrorException();
        }
    }

    private static void checkForOpenRoundBracket(ListIterator<Token> tokens, int line) throws SintaxErrorException {
        if (!tokens.hasNext()) {
            throw new SintaxErrorException();
        }

        if (!tokens.next().isOpenRoundBracket()) {
            throw new SintaxErrorException();
        }
    }

    private static void checkForCloseRoundBracket(ListIterator<Token> tokens, int line) throws SintaxErrorException {
        if (!tokens.hasNext()) {
            throw new SintaxErrorException();
        }

        if (!tokens.next().isCloseRoundBracket()) {
            throw new SintaxErrorException();
        }
    }

    private static void checkForOpenBoxBracket(ListIterator<Token> tokens, int line) throws SintaxErrorException {
        if (!tokens.hasNext()) {
            throw new SintaxErrorException();
        }

        if (!tokens.next().isOpenBoxBracket()) {
            tokens.previous();
            throw new SintaxErrorException();
        }
    }

    private static void checkForCloseBoxBracket(ListIterator<Token> tokens, int line) throws SintaxErrorException {
        if (!tokens.hasNext()) {
            throw new SintaxErrorException();
        }

        if (!tokens.next().isCloseBoxBracket()) {
            throw new SintaxErrorException();
        }
    }

    private static void checkForOpenCurlyBracket(ListIterator<Token> tokens, int line) throws SintaxErrorException {
        if (!tokens.hasNext()) {
            throw new SintaxErrorException();
        }

        if (!tokens.next().isOpenCurlyBracket()) {
            throw new SintaxErrorException();
        }
    }

    private static void checkForCloseCurlyBracket(ListIterator<Token> tokens, int line) throws SintaxErrorException {
        if (!tokens.hasNext()) {
            throw new SintaxErrorException();
        }

        if (!tokens.next().isCloseCurlyBracket()) {
            throw new SintaxErrorException();
        }
    }

    private static void checkForQuestionMark(ListIterator<Token> tokens, int line) throws SintaxErrorException {
        if (!tokens.hasNext()) {
            throw new SintaxErrorException();
        }

        if (!tokens.next().isQuestionMark()) {
            throw new SintaxErrorException();
        }
    }

    private static void checkForColon(ListIterator<Token> tokens, int line) throws SintaxErrorException {
        if (!tokens.hasNext()) {
            throw new SintaxErrorException();
        }

        if (!tokens.next().isColon()) {
            throw new SintaxErrorException();
        }
    }

    private static void checkForAssignment(ListIterator<Token> tokens, int line) throws SintaxErrorException {
        if (!tokens.hasNext()) {
            throw new SintaxErrorException();
        }

        if (!tokens.next().isAssignment()) {
            throw new SintaxErrorException();
        }
    }

    private static boolean safeCheckForOpenRoundBracket(ListIterator<Token> tokens) {
        if (!tokens.hasNext()) {
            return false;
        }

        if (!tokens.next().isOpenRoundBracket()) {
            tokens.previous();
            return false;
        }

        return true;
    }

    private static boolean safeCheckForUnaryMinus(ListIterator<Token> tokens) {
        if (!tokens.hasNext()) {
            return false;
        }

        if (!tokens.next().isUnaryMinus()) {
            tokens.previous();
            return false;
        }

        return true;
    }

    private static void checkForEnd(Iterator<Token> tokens) throws SintaxErrorException {
        if (tokens.hasNext()) {
            throw new SintaxErrorException();
        }
    }

    private static void checkForComma(Iterator<Token> tokens) throws SintaxErrorException {
        if (!tokens.hasNext()) {
            throw new SintaxErrorException();
        }

        if (!tokens.next().isComma()) {
            throw new SintaxErrorException();
        }
    }

    private static boolean safeCheckForComma(ListIterator<Token> tokens) {
        if (!tokens.hasNext()) {
            return false;
        }

        if (!tokens.next().isComma()) {
            tokens.previous();
            return false;
        }

        return true;
    }

    private static boolean safeCheckForCloseRoundBracket(ListIterator<Token> tokens) {
        if (!tokens.hasNext()) {
            return false;
        }

        if (!tokens.next().isCloseRoundBracket()) {
            tokens.previous();
            return false;
        }

        return true;
    }
}
