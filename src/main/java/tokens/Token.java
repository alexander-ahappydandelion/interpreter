package tokens;

import grammar.GrammarHelper;

public abstract class Token {
    protected String value;

    public String getValue() {
        return value;
    }

    public boolean isIdentifier() {
        return GrammarHelper.isIdentifier(getValue());
    }

    public boolean isNumber() {
        return GrammarHelper.isNumber(getValue());
    }

    public boolean isBinaryOperation() {
        return GrammarHelper.isBinaryOperation(getValue());
    }

    public boolean isOpenRoundBracket() {
        return GrammarHelper.isOpenRoundBracket(getValue());
    }

    public boolean isCloseRoundBracket() {
        return GrammarHelper.isCloseRoundBracket(getValue());
    }

    public boolean isOpenBoxBracket() {
        return GrammarHelper.isOpenBoxBracket(getValue());
    }

    public boolean isCloseBoxBracket() {
        return GrammarHelper.isCloseBoxBracket(getValue());
    }

    public boolean isOpenCurlyBracket() {
        return GrammarHelper.isOpenCurlyBracket(getValue());
    }

    public boolean isCloseCurlyBracket() {
        return GrammarHelper.isCloseCurlyBracket(getValue());
    }

    public boolean isQuestionMark() { return GrammarHelper.isQuestionMark(getValue()); }

    public boolean isColon() { return GrammarHelper.isColon(getValue()); }

    public boolean isUnaryMinus() { return GrammarHelper.isUnaryMinus(getValue()); }

    public boolean isAssignment() { return GrammarHelper.isAssignment(getValue()); }

    public boolean isComma() {
        return GrammarHelper.isComma(getValue());
    }
}
