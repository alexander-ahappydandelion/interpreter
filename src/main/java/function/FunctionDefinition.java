package function;

public class FunctionDefinition {
    private String name;
    private Function function;

    public FunctionDefinition(String name, Function function) {
        this.name = name;
        this.function = function;
    }

    public String getName() {
        return name;
    }

    public Function getFunction() {
        return function;
    }
}
