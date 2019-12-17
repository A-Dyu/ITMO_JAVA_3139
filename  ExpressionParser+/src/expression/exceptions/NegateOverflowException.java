package expression.exceptions;

public class NegateOverflowException extends OperateOverflowException {
    public NegateOverflowException(int x) {
        super("Negate", "-" + x);
    }
}
