package context;

import exceptions.FunctionNotFoundContextException;
import exceptions.ParameteNumberMismatchContextException;
import exceptions.ParameterNotFoundContexException;
import function.Function;

import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

public class Context {
    private Map<String, Function> functions;
    private Map<String, Integer> parameters;

    public Context() {
        functions = new HashMap<>();
        parameters = new HashMap<>();
    }

    public Context(Context another) {
        functions = new HashMap<>(another.functions);
        parameters = new HashMap<>(another.parameters);
    }

    public void setFunction(String identifier,
                            Function function) {
        functions.put(identifier, function);
    }

    public void setParameter(String identifier,
                             Integer value) {
        parameters.put(identifier, value);
    }

    public void setParameters(List<String> identifiers,
                              List<Integer> values) throws ParameteNumberMismatchContextException {
        if (identifiers.size() != values.size()) {
            throw new ParameteNumberMismatchContextException();
        }

        Iterator<String> iIter = identifiers.iterator();
        Iterator<Integer> vIter = values.iterator();

        while (iIter.hasNext() && vIter.hasNext()) {
            parameters.put(iIter.next(), vIter.next());
        }
    }

    public Function getFunction(String identifier) throws FunctionNotFoundContextException {
        if (!functions.containsKey(identifier)) {
            throw new FunctionNotFoundContextException();
        }

        return functions.get(identifier);
    }

    public int getParameter(String identifier) throws ParameterNotFoundContexException {
        if (!parameters.containsKey(identifier)) {
            throw new ParameterNotFoundContexException();
        }

        return parameters.get(identifier);
    }
}
