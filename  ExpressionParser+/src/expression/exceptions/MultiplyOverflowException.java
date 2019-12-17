package expression.exceptions;

public class MultiplyOverflowException extends OperateOverflowException {
    public MultiplyOverflowException(int a, int b) {
        super("Multiply", a + " * " + b);
    }
}
