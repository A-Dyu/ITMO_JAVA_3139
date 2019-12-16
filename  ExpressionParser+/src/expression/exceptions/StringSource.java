package expression.exceptions;

import expression.exceptions.ParserException;
import expression.exceptions.Source;

public class StringSource implements Source {
    private final String data;
    private int pos;

    public int getPos() {
        return pos;
    }

    public StringSource(final String data) {
        this.data = data;
    }

    @Override
    public boolean hasNext() {
        return pos < data.length();
    }

    @Override
    public char next() {
        return data.charAt(pos++);
    }

    @Override
    public ParserException error(final String message) {
        return new ParserException("Exception at pos " + (pos - 1) +  ": " + message);
    }
}