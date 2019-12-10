package expression;

public class LeftShift extends AbstractBinaryOperator {
    public LeftShift(CommonExpression a, CommonExpression b) {
        super(a, b);
    }

    @Override
    protected String getOperator() {
        return "<<";
    }

    @Override
    protected int getPriority() {
        return 0;
    }

    @Override
    protected int operate(int a, int b) {
        return a << b;
    }

    @Override
    protected boolean isOrdered() {
        return true;
    }
}
