package expression.exceptions;

import expression.exceptions.ParserException;

public interface Source {
    ParserException error(String message);
    boolean hasNext();
    char next();
    int getPos();
}
