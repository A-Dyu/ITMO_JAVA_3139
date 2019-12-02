package expression;

public class Subtract extends BinaryOperator{
    public Subtract(Expression a, Expression b) {
        super(a, b);
    }

    @Override
    protected String getOperator() {
        return " - ";
    }

    @Override
    protected int operate(int a, int b) {
        return a - b;
    }

    @Override
    public int getPriority() {
        return 1;
    }
}
