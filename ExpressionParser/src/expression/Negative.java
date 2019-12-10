package expression;

public class Negative implements CommonExpression {
    private CommonExpression expression;

    public static CommonExpression getNegativeExpression(CommonExpression expression) {
        if (expression instanceof Const) {
            return new Const(-expression.evaluate(0));
        }
        if (expression instanceof Negative) {
            return ((Negative) expression).expression;
        }
        return new Negative(expression);
    }

    private Negative(CommonExpression expression) {
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

    @Override
    public int evaluate(int x) {
        return -expression.evaluate(x);
    }

    @Override
    public int evaluate(int x, int y, int z) {
        return -expression.evaluate(x, y, z);
    }
}
