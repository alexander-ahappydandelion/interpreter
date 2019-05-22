package interpreter;

import context.Context;
import exceptions.*;
import expression.Expression;
import function.FunctionDefinition;
import lexer.Lexer;
import parser.Parser;
import tokens.Token;

import java.util.List;

public class Interpreter {
    public static String interpret(List<String> lines) {
        try {
            Context context = new Context();
            for (int i = 0; i < lines.size() - 1; ++i) {
                List<Token> tokens = Lexer.tokenizeLine(lines.get(i), i + 1);
                FunctionDefinition functionDefinition = Parser.parseFunctionDefinitionLine(tokens, i + 1);

                context.setFunction(functionDefinition.getName(),
                        functionDefinition.getFunction());
            }

            int lastLine = lines.size();

            List<Token> tokens = Lexer.tokenizeLine(lines.get(lastLine - 1), lastLine);
            Expression expression = Parser.parseExpressionLine(tokens, lastLine);

            return "" + expression.evaluate(context);
        } catch (SintaxErrorException | RuntimeErrorException | FunctionNotFoundException | ArgumentNumberMismathcException | ParameterNotFoundException e) {
            return e.toString();
        }
    }
}
