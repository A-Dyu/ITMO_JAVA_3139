package expression.exceptions;

public abstract class OperateOverflowException extends ExpressionException {
    public OperateOverflowException(String operation, String argument) {
        super(operation + " overflow: " + argument);
    }
}
