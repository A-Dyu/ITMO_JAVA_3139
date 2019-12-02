package expression;

import java.util.Objects;

abstract class BinaryOperator implements Expression {
    private Expression a;
    private Expression b;

    protected abstract String getOperator();

    protected abstract int getPriority();

    public BinaryOperator(Expression a, Expression b) {
        this.a = a;
        this.b = b;
    }

    protected abstract int operate(int a, int b);

    @Override
    public int evaluate(int x) {
        return operate(a.evaluate(x), b.evaluate(x));
    }

    @Override
    public String toString() {
        StringBuilder stringBuilder = new StringBuilder("(" );
        stringBuilder.append(a);
        stringBuilder.append(getOperator());
        stringBuilder.append(b).append(')');
        return stringBuilder.toString();
    }

    private String getExpression(Expression a, boolean isInBrackets) {
        return (isInBrackets ? "(" : "") + a.toMiniString() + (isInBrackets ? ")" : "");
    }

    private boolean checkBrackets(Expression a) {
        return ((a instanceof BinaryOperator) && ((BinaryOperator) a).getPriority() < this.getPriority());
    }

    private boolean checkExpressionOrderAddition(Expression a) {
        return a instanceof Subtract || a instanceof Divide;
    }
    
    private boolean checkBracketsOrderAddition(Expression a) {
        if (checkExpressionOrderAddition(a) && this.getPriority() == ((BinaryOperator) a).getPriority()) {
            return true;
        }
        if (checkExpressionOrderAddition(this) &&
                (a instanceof BinaryOperator && ((BinaryOperator) a).getPriority() <= this.getPriority())) {
            return true;
        }
        return false;
    }

    @Override
    public String toMiniString() {
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append(getExpression(a, checkBrackets(a)));
        stringBuilder.append(getOperator());
        stringBuilder.append(getExpression(b, checkBrackets(b) || checkBracketsOrderAddition(b)));
        return stringBuilder.toString();
    }

    @Override
    public boolean equals(Object object) {
        if (object == null || getClass() != object.getClass()) { return false; }
        BinaryOperator binaryOperator = (BinaryOperator) object;
        return this.a.equals(binaryOperator.a) && b.equals(binaryOperator.b);
    }

    @Override
    public int hashCode() {
        return 424241 * a.hashCode() + 31 * Objects.hashCode(getOperator()) + b.hashCode();
    }
}
