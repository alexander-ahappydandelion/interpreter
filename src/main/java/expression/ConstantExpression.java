package expression;

import context.Context;

public class ConstantExpression implements Expression {
    private final int value;
    private final int declaredAt;

    public ConstantExpression(int value, int declaredAt) {
        this.value = value;
        this.declaredAt = declaredAt;
    }

    public ConstantExpression(String value, int declaredAt) {
        this.value = Integer.parseInt(value);
        this.declaredAt = declaredAt;
    }

    public int evaluate(Context context) {
        return value;
    }

    @Override
    public String toString() {
        return "" + value;
    }
}
