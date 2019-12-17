package expression.exceptions;

public class CheckedSubtract extends AbstractBinaryOperator {
    public CheckedSubtract(CommonExpression a, CommonExpression b) {
        super(a, b);
    }

    @Override
    protected String getOperator() {
        return " - ";
    }

    @Override
    protected int operate(int a, int b) {
        checkException(a, b);
        return a - b;
    }

    @Override
    protected boolean isOrdered() {
        return true;
    }

    @Override
    public int getPriority() {
        return 1;
    }

    private void checkException(int a, int b) {
        if (b > 0 && Integer.MIN_VALUE + b > a) {
            throw new SubtractOverflowException(a, b);
        } else if (b < 0 && Integer.MAX_VALUE + b < a) {
            throw new SubtractOverflowException(a, b);
        }
    }

}
