package expression;

import java.util.Objects;

abstract class AbstractBinaryOperator implements CommonExpression {
    private CommonExpression a;
    private CommonExpression b;

    public AbstractBinaryOperator(CommonExpression a, CommonExpression b) {
        this.a = a;
        this.b = b;
    }


    protected abstract String getOperator();
    protected abstract int getPriority();
    protected abstract int operate(int a, int b);
    protected abstract boolean isOrdered();

    private int operateWithException(int x, int y) {
        try {
            return operate(x, y);
        } catch (ArithmeticException e) {
            throw new ExpressionException(e);
        }
    }

    @Override
    public int evaluate(int x) {
        return operateWithException(a.evaluate(x), b.evaluate(x));
    }

    @Override
    public int evaluate(int x, int y, int z) { return operateWithException(a.evaluate(x, y, z), b.evaluate(x, y, z));}

    @Override
    public String toString() {
        return "(" + a + getOperator()+ b + ')';
    }

    private String getExpression(Expression a, boolean isInBrackets) {
        return (isInBrackets ? "(" : "") + a.toMiniString() + (isInBrackets ? ")" : "");
    }

    private boolean checkPriorityBrackets(Expression a) {
        return ((a instanceof AbstractBinaryOperator) && ((AbstractBinaryOperator) a).getPriority() < this.getPriority());
    }

    private boolean checkOrderAdditionBrackets(Expression a) {
        if (a instanceof AbstractBinaryOperator) {
            AbstractBinaryOperator binA = (AbstractBinaryOperator) a;
            if (binA.isOrdered() && binA.getPriority() <= this.getPriority()) {
                return true;
            }
            return (this.isOrdered()) && (binA.getPriority() <= this.getPriority());
        }
        return false;
    }

    @Override
    public String toMiniString() {
        return getExpression(a, checkPriorityBrackets(a)) +
                getOperator() +
                getExpression(b, checkPriorityBrackets(b) || checkOrderAdditionBrackets(b));
    }

    @Override
    public boolean equals(Object object) {
        if (object == null || getClass() != object.getClass()) {
            return false;
        }
        AbstractBinaryOperator abstractBinaryOperator = (AbstractBinaryOperator) object;
        return this.a.equals(abstractBinaryOperator.a) && b.equals(abstractBinaryOperator.b);
    }

    @Override
    public int hashCode() {
        return 42424241 * a.hashCode() + 31 * 31 * (Objects.hash(getOperator())) + 31 * b.hashCode();
    }
}
