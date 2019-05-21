package function;

import expression.Expression;

import java.util.List;

public class Function {
    private final List<String> parameters;
    private final Expression body;
    private final int declaredAt;

    public Function(List<String> parameters, Expression body, int declaredAt) {
        this.parameters = parameters;
        this.body = body;
        this.declaredAt = declaredAt;
    }

    public List<String> getParameters() {
        return parameters;
    }

    public Expression getBody() {
        return body;
    }
}
