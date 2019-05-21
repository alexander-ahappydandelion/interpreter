package tokens;

import grammar.GrammarHelper;

public class FunctionalToken extends Token {
    public FunctionalToken(char symbol, int declaredAt) {
        if (!GrammarHelper.isFunctional(symbol)) {
            throw new IllegalArgumentException();
        }

        this.value = "" + symbol;
    }
}
