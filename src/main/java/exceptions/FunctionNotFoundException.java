package exceptions;

public class FunctionNotFoundException extends Exception {
    private String function;
    private int line;

    public FunctionNotFoundException(String function, int line) {
        this.function = function;
        this.line = line;
    }

    @Override
    public String toString() {
        return "FUNCTION NOT FOUND " + function + ":" + line;
    }
}
