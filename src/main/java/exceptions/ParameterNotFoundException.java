package exceptions;

import java.beans.Expression;

public class ParameterNotFoundException extends Exception {
    private String parameter;
    private int line;

    public ParameterNotFoundException(String parameter, int line) {
        this.parameter = parameter;
        this.line = line;
    }

    @Override
    public String toString() {
        return "PARAMETER NOT FOUND " + parameter + ":" + line;
    }
}
