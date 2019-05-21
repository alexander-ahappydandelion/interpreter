package expression;

import exceptions.ArgumentNumberMismathcException;
import exceptions.FunctionNotFoundException;
import exceptions.ParameterNotFoundException;
import exceptions.RuntimeErrorException;
import operation.BinaryOperation;
import context.Context;

public class BinaryExpression implements Expression {
    private final Expression leftExpression;
    private final Expression rightExpression;
    private final BinaryOperation operation;
    private final int declaredAt;

    public BinaryExpression(Expression leftExpression,
                            Expression rightExpression,
                            BinaryOperation operation,
                            int declaredAt) {
        this.leftExpression = leftExpression;
        this.rightExpression = rightExpression;
        this.operation = operation;
        this.declaredAt = declaredAt;
    }

    public int evaluate(Context context) throws RuntimeErrorException, ArgumentNumberMismathcException, FunctionNotFoundException, ParameterNotFoundException {
        int leftValue = leftExpression.evaluate(context);
        int rightValue = rightExpression.evaluate(context);

        try {
            return operation.apply(leftValue, rightValue);
        } catch (ArithmeticException e) {
            throw new RuntimeErrorException(this, declaredAt);
        }
    }

    @Override
    public String toString() {
        return "(" +
                leftExpression.toString() +
                operation.toString() +
                rightExpression.toString() +
                ")";
    }
}
