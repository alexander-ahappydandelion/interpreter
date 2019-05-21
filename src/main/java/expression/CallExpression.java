package expression;

import exceptions.*;
import context.Context;
import function.Function;

import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;

public class CallExpression implements Expression {
    private final String name;
    private final List<Expression> args;
    private final int declaredAt;

    public CallExpression(String name,
                   List<Expression> args,
                   int declaredAt) {
        this.name = name;
        this.args = args;
        this.declaredAt = declaredAt;
    }

    public int evaluate(Context context) throws RuntimeErrorException, FunctionNotFoundException, ArgumentNumberMismathcException, ParameterNotFoundException {
        List<Integer> argValues = new LinkedList<>();

        for (Expression arg : args) {
            argValues.add(arg.evaluate(context));
        }

        try {
            Function function = context.getFunction(name);
            List<String> parameters = function.getParameters();

            Context funcContext = new Context(context);
            funcContext.setParameters(parameters, argValues);

            return function.getBody().evaluate(funcContext);

        } catch (ParameteNumberMismatchContextException e) {

            throw new ArgumentNumberMismathcException(name, declaredAt);

        } catch (FunctionNotFoundContextException e) {

            throw new FunctionNotFoundException(name, declaredAt);

        }
    }

    @Override
    public String toString() {
        Iterator<Expression> iter = args.iterator();

        StringBuilder builder = new StringBuilder(name + "(" + iter.next().toString());

        while (iter.hasNext()) {
            builder.append(",").append(iter.next().toString());
        }

        builder.append(")");

        return builder.toString();
    }
}
