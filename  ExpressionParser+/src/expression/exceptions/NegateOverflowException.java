package expression.exceptions;

public class NegateOverflowException extends OperateOverflowException {
    public NegateOverflowException(String message) {
        super(message);
    }
}
