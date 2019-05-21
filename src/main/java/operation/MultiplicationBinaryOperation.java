package operation;

public class MultiplicationBinaryOperation implements BinaryOperation {
    @Override
    public int apply(int leftValue, int rightValue) {
        return leftValue * rightValue;
    }

    @Override
    public String toString() {
        return "*";
    }
}
