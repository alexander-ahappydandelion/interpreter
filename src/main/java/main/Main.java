package main;

import exceptions.*;
import expression.Expression;
import function.FunctionDefinition;
import lexer.Lexer;
import parser.Parser;
import context.Context;
import tokens.Token;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        List<String> lines = new ArrayList<>();
        while (scanner.hasNextLine()) {
            lines.add(scanner.nextLine());
        }

        System.out.println(interprate(lines));
    }

    public static String interprate(List<String> lines) {
        try {Context context = new Context();
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
