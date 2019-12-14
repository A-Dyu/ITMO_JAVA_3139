package expression;

public class ExpressionException extends RuntimeException {
    public ExpressionException(String message) {
        super(message);
    }

    public ExpressionException(Throwable cause) {
        super(cause);
    }
}
