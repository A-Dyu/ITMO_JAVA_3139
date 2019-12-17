package expression.exceptions;

public class CheckedAdd extends AbstractBinaryOperator {
    public CheckedAdd(CommonExpression a, CommonExpression b) {
        super(a, b);
    }

    @Override
    protected String getOperator() {
        return " + ";
    }

    @Override
    protected int operate(int a, int b) {
        checkException(a, b);
        return a + b;
    }

    @Override
    protected boolean isOrdered() {
        return false;
    }

    private void checkException(int a, int b) {
        if (b > 0 && Integer.MAX_VALUE - b < a) {
            throw new AddOverflowException(a, b);
        } else if (b < 0 && Integer.MIN_VALUE - b > a) {
            throw new AddOverflowException(a, b);
        }
    }

    @Override
    public int getPriority() {
        return 1;
    }

}
