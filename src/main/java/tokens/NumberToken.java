package tokens;

import grammar.GrammarHelper;

public class NumberToken extends Token {
    public NumberToken(String value, int declaredAt) {
        if (!GrammarHelper.isNumber(value)) {
            throw new IllegalArgumentException();
        }

        this.value = value;
    }
}
