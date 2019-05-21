package expression;

import exceptions.ArgumentNumberMismathcException;
import exceptions.FunctionNotFoundException;
import exceptions.ParameterNotFoundException;
import exceptions.RuntimeErrorException;
import context.Context;

public class IfExpression implements Expression {
    private final Expression conditionExpression;
    private final Expression ifBranchExpression;
    private final Expression elseBranchExpression;

    private final int declaredAt;

    public IfExpression(Expression conditionExpression,
                        Expression ifBranchExpression,
                        Expression elseBranchExpression,
                        int declaredAt) {
        this.conditionExpression = conditionExpression;
        this.ifBranchExpression = ifBranchExpression;
        this.elseBranchExpression = elseBranchExpression;

        this.declaredAt = declaredAt;
    }

    public int evaluate(Context context) throws RuntimeErrorException, ArgumentNumberMismathcException, FunctionNotFoundException, ParameterNotFoundException {
        int conditionValue = conditionExpression.evaluate(context);

        if (conditionValue != 0) {
            return ifBranchExpression.evaluate(context);
        } else {
            return elseBranchExpression.evaluate(context);
        }
    }

    @Override
    public String toString() {
        return "[" + conditionExpression.toString() + "]" +
                "?(" + ifBranchExpression.toString() + ")" +
                ":(" + elseBranchExpression.toString() + ")";
    }
}
