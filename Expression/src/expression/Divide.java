package expression;

public class Divide extends AbstractBinaryOperator {
    public Divide(AbstractExpression a, AbstractExpression b) {
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
        return a / b;
    }

    @Override
    protected boolean isOrdered() {
        return true;
    }

}
