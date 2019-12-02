package expression;

public class Divide extends BinaryOperator {
    public Divide(Expression a, Expression b) {
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
}
