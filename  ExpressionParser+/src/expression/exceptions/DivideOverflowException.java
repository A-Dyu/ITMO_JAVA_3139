package expression.exceptions;

public class DivideOverflowException extends OperateOverflowException {
    public DivideOverflowException(String message) {
        super(message);
    }
}
