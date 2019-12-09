package expression.parser;

public interface Source {
    RuntimeException error(String message);
    boolean hasNext();
    char next();
}
