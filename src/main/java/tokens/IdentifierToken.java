package tokens;

import grammar.GrammarHelper;

public class IdentifierToken extends Token {
    public IdentifierToken(String value, int declaredAt) {
        if (!GrammarHelper.isIdentifier(value)) {
            throw new IllegalArgumentException();
        }

        this.value = value;
    }
}
