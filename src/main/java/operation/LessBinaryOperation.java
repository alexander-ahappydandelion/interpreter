package operation;

public class LessBinaryOperation implements BinaryOperation {
    @Override
    public int apply(int leftValue, int rightValue) {
        return leftValue < rightValue ? 1 : 0;
    }

    @Override
    public String toString() {
        return "<";
    }
}
