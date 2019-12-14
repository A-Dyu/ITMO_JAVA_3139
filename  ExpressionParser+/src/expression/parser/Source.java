package expression.parser;

public interface Source {
    ParserException error(String message);
    boolean hasNext();
    char next();
}
