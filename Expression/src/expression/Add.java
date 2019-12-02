package expression;

public class Add extends BinaryOperator {
    @Override
    protected String getOperator() {
        return " + ";
    }

    public Add(Expression a, Expression b) {
        super(a, b);
    }

    @Override
    protected int operate(int a, int b) {
        return a + b;
    }

    @Override
    public int getPriority() {
        return 1;
    }
}
