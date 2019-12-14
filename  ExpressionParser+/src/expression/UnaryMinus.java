package expression;

public class UnaryMinus implements CommonExpression {
    private CommonExpression expression;

    public UnaryMinus(CommonExpression expression) {
        this.expression = expression;
    }

    @Override
    public String toString() {
        return "-(" + expression.toString() + ")";
    }

    @Override
    public String toMiniString() {
        boolean hasBrackets = expression instanceof AbstractBinaryOperator;
        return "-" + (hasBrackets ? "(" : "") + expression.toMiniString() + (hasBrackets ? ")" : "");
    }

    private static int operate(int x) {
        if (x == Integer.MIN_VALUE) {
            throw new ArithmeticException("integer overflow");
        }
        return -x;
    }

    @Override
    public int evaluate(int x) {
        return operate(expression.evaluate(x));
    }

    @Override
    public int evaluate(int x, int y, int z) {
        return operate(expression.evaluate(x, y, z));
    }
}
