package expression;

public class Multiply extends BinaryOperator {
    public Multiply(Expression a, Expression b) {
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
    public int getPriority() {
        return 2;
    }
}
