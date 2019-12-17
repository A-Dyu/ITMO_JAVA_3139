package expression.exceptions;

public class CheckedDivide extends AbstractBinaryOperator {
    public CheckedDivide(CommonExpression a, CommonExpression b) {
        super(a, b);
    }

    @Override
    protected String getOperator() {
        return " / ";
    }

    @Override
    protected int getPriority() {
        return 2;
    }

    @Override
    protected int operate(int a, int b) {
        checkException(a, b);
        return a / b;
    }

    @Override
    protected boolean isOrdered() {
        return true;
    }

    private void checkException(int a, int b) {
        if (a == Integer.MIN_VALUE && b == -1) {
            throw new DivideOverflowException(a, b);
        }
        if (b == 0) {
            throw new DivideByZeroException("Divide by zero: " + a + getOperator() + b);
        }
    }

}
