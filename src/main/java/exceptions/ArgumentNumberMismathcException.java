package exceptions;

public class ArgumentNumberMismathcException extends Exception {
    private String function;
    private int line;

    public ArgumentNumberMismathcException(String function, int line) {
        this.function = function;
        this.line = line;
    }

    @Override
    public String toString() {
        return "ARGUMENT NUMBER MISMATCH " + function + ":" + line;
    }
}
