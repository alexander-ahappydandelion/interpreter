package expression;

import exceptions.ArgumentNumberMismathcException;
import exceptions.FunctionNotFoundException;
import exceptions.ParameterNotFoundException;
import exceptions.RuntimeErrorException;
import context.Context;

public interface Expression {
    int evaluate(Context context) throws RuntimeErrorException, FunctionNotFoundException, ArgumentNumberMismathcException, ParameterNotFoundException;
}
