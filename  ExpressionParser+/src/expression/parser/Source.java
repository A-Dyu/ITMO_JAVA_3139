package expression.parser;

import expression.exceptions.ParserException;

public interface Source {
    ParserException error(String message);
    boolean hasNext();
    char next();
}
