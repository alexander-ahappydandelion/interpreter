package exceptions;

import expression.Expression;

public class RuntimeErrorException extends Exception {
    private Expression expression;
    private int line;

    public RuntimeErrorException(Expression expression, int line) {
        this.expression = expression;
        this.line = line;
    }

    @Override
    public String toString() {
        return "RUNTIME ERROR " + expression.toString() + ":" + line;
    }
}
