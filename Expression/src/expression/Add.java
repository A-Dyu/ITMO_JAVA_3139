package expression;

public class Add extends AbstractBinaryOperator {
    public Add(CommonExpression a, CommonExpression b) {
        super(a, b);
    }

    @Override
    protected String getOperator() {
        return " + ";
    }

    @Override
    protected int operate(int a, int b) {
        return a + b;
    }

    @Override
    protected boolean isOrdered() {
        return false;
    }

    @Override
    public int getPriority() {
        return 1;
    }

}
