package expression;

public class NegativeExpression implements CommonExpression {
    private CommonExpression expression;

    public static CommonExpression getNegativeExpression(CommonExpression expression) {
        if (expression instanceof Const) {
            return new Const(-expression.evaluate(0));
        }
        if (expression instanceof NegativeExpression) {
            return ((NegativeExpression) expression).expression;
        }
        return new NegativeExpression(expression);
    }

    private NegativeExpression(CommonExpression expression) {
        this.expression = expression;
    }

    @Override
    public String toString() {
        return "-" + expression.toString();
    }

    @Override
    public String toMiniString() {
        return "-" + expression.toMiniString();
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
