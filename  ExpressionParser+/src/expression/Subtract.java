package expression;

public class Subtract extends AbstractBinaryOperator {
    public Subtract(CommonExpression a, CommonExpression b) {
        super(a, b);
    }

    @Override
    protected String getOperator() {
        return " - ";
    }

    @Override
    protected int operate(int a, int b) {
        return Math.subtractExact(a, b);
    }

    @Override
    protected boolean isOrdered() {
        return true;
    }

    @Override
    public int getPriority() {
        return 1;
    }

}
