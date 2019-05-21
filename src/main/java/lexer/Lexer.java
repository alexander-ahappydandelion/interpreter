package lexer;

import exceptions.SintaxErrorException;
import grammar.GrammarHelper;
import iterator.CharacterIterator;
import tokens.FunctionalToken;
import tokens.IdentifierToken;
import tokens.NumberToken;
import tokens.Token;

import java.util.LinkedList;
import java.util.List;
import java.util.ListIterator;

public class Lexer {
    public static List<Token> tokenizeLine(String input, int line) throws SintaxErrorException {
        List<Token> tokens = new LinkedList<>();

        if (input == null) {
            return tokens;
        }

        ListIterator<Character> iter = new CharacterIterator(input);

        while (iter.hasNext()) {
            Character c = iter.next();

            if (GrammarHelper.isCharacter(c)) {
                iter.previous();

                String identifier = nextIdentifier(iter);
                tokens.add(new IdentifierToken(identifier, line));

            } else if (GrammarHelper.isDigit(c)) {
                iter.previous();

                String number = nextNumber(iter);
                tokens.add(new NumberToken(number, line));

            } else if (GrammarHelper.isFunctional(c)) {

                tokens.add(new FunctionalToken(c, line));

            } else {

                throw new SintaxErrorException();

            }
        }

        return tokens;
    }

    private static String nextIdentifier(ListIterator<Character> iter) {
        StringBuilder builder = new StringBuilder();

        while (iter.hasNext()) {
            Character c = iter.next();

            if (!GrammarHelper.isCharacter(c)) {
                iter.previous();
                break;
            }

            builder.append(c);
        }

        return builder.toString();
    }

    private static String nextNumber(ListIterator<Character> iter) {
        StringBuilder builder = new StringBuilder();

        while (iter.hasNext()) {
            Character c = iter.next();

            if (!GrammarHelper.isDigit(c)) {
                iter.previous();
                break;
            }

            builder.append(c);
        }

        return builder.toString();
    }
}
