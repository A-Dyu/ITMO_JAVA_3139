package expression.parser;

public class StringSource implements Source {
    private final String data;
    private int pos;

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