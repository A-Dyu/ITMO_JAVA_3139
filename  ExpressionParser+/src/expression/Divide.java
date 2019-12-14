package expression;

public class Divide extends AbstractBinaryOperator {
    public Divide(CommonExpression a, CommonExpression b) {
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
        if (a == Integer.MIN_VALUE && b == -1) {
            throw new ArithmeticException("integer overflow");
        }
        return a / b;
    }

    @Override
    protected boolean isOrdered() {
        return true;
    }

}
