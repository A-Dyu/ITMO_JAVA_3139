package expression;

public class Multiply extends AbstractBinaryOperator {
    public Multiply(AbstractExpression a, AbstractExpression b) {
        super(a, b);
    }

    @Override
    protected String getOperator() {
        return " * ";
    }

    @Override
    protected int operate(int a, int b) {
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
}
