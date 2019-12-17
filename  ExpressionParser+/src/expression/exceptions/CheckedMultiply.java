package expression.exceptions;

public class CheckedMultiply extends AbstractBinaryOperator {
    public CheckedMultiply(CommonExpression a, CommonExpression b) {
        super(a, b);
    }

    @Override
    protected String getOperator() {
        return " * ";
    }

    @Override
    protected int operate(int a, int b) {
        checkException(a, b);
        return a * b;
    }

    @Override
    protected boolean isOrdered() {
        return false;
    }

    @Override
    public int getPriority() {
        return 2;
    }

    private void checkException(int a, int b) {
        if (a != 0 && b != 0) {
            if (a == Integer.MIN_VALUE && b == -1 || a == -1 && b == Integer.MIN_VALUE) {
                throw new MultiplyOverflowException(a, b);
            } else if (a * b == Integer.MIN_VALUE && Integer.MIN_VALUE / b == a) {
                return;
            } else if ((a == Integer.MIN_VALUE && b != 1) || (a != 1 && b == Integer.MIN_VALUE)) {
                throw new MultiplyOverflowException(a, b);
            } else if (Integer.MAX_VALUE / abs(a) < abs(b)) {
                throw new MultiplyOverflowException(a, b);
            }
        }
    }

    private static int abs(int x) {
        return x >= 0 ? x : -x;
    }
}
