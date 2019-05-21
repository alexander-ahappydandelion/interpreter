package expression;

import exceptions.ParameterNotFoundContexException;
import exceptions.ParameterNotFoundException;
import context.Context;

public class IdentifierExpression implements Expression {
    private final String identifier;
    private final int declaredAt;

    public IdentifierExpression(String identifier, int declaredAt) {
        this.identifier = identifier;
        this.declaredAt = declaredAt;
    }

    public int evaluate(Context context) throws ParameterNotFoundException {
        try {
            return context.getParameter(identifier);
        } catch (ParameterNotFoundContexException e) {
            throw new ParameterNotFoundException(identifier, declaredAt);
        }
    }

    @Override
    public String toString() {
        return identifier;
    }
}
