package expression.parser;

public class BaseParser {
    private Source source;
    protected char ch;

    protected void setSource(Source source) {
        this.source = source;
    }

    protected void nextChar() {
        ch = source.hasNext() ? source.next() : '\0';
        //System.out.println(ch);
    }

    protected boolean test(char expected) {
        if (ch == expected) {
            nextChar();
            return true;
        }
        return false;
    }

    protected void expect(final char c) {
        if (ch != c) {
            throw error("Expected '" + c + "', found '" + ch + "'");
        }
        nextChar();
    }

    protected void expect(final String value) {
        for (char c : value.toCharArray()) {
            expect(c);
        }
    }

    protected boolean between(final char from, final char to) {
        return from <= ch && ch <= to;
    }

    protected RuntimeException error(final String message) {
        return source.error(message);
    }
}
