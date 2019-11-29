import java.util.Arrays;
import java.util.NoSuchElementException;
import java.io.*;
public class MyScanner {

	private Reader reader;
	private int lt = -1;

	public MyScanner (String in) throws FileNotFoundException, IOException {
		reader = new InputStreamReader(new FileInputStream(in), "UTF-8");
	}

	public MyScanner () throws IOException {
		reader = new InputStreamReader(System.in, "UTF-8");
	}
	private interface Checker {
		boolean check(int c) throws IOException;
	}

	private static boolean wordChecker(int c) {
		return Character.isLetter((char) c) || Character.getType((char) c) == Character.DASH_PUNCTUATION || c == '\'';
	}

	private static boolean newLineChecker(int c) {
		return c == '\n' || c == '\r';
	}

	private static boolean eOFChecker(int c) {
		return c == -1;
	}

	private static boolean stringChecker(int c) {
		return !Character.isWhitespace((char) c) && !newLineChecker(c) && !eOFChecker(c);
	}
	private static boolean intChecker(int c) {
		return (char) c == '-' || Character.isDigit((char) c);
	}
	public void close() throws IOException {
		reader.close();
	}

	private boolean hasNext(Checker checker) throws NoSuchElementException, IOException {
		if (checker.check(lt)) {
			return true;
		} else {
			lt = -1;
		}
		int c = reader.read();
		while (!checker.check(c) && c != -1) {
			c = reader.read();
		}
		lt = c;
		return !eOFChecker(c);
	}

	private String next(Checker checker) throws NoSuchElementException, IOException {
		if (!hasNext(checker)) {
			throw new NoSuchElementException("No such element in input");
		}
		StringBuilder str = new StringBuilder();
		str.append((char) lt);
		int c = reader.read();
		while (checker.check(c)) {
			str.append((char) c);
			c = reader.read();
		}
		lt = c;
		return str.toString();
	}

	public boolean hasNextInt() throws NoSuchElementException, IOException {
		return hasNext(MyScanner::intChecker);
	}

	public int nextInt() throws NoSuchElementException, IOException {
		return Integer.parseInt(next(MyScanner::intChecker));
	}

	public boolean hasNextWord() throws NoSuchElementException, IOException {
		return hasNext(MyScanner::wordChecker);
	}

	public String nextWord() throws NoSuchElementException, IOException {
		return next(MyScanner::wordChecker);
	}

	public boolean hasNextString() throws NoSuchElementException, IOException {
		return hasNext(MyScanner::stringChecker);
	}

	public String nextString() throws NoSuchElementException, IOException {
		return next(MyScanner::stringChecker);
	}

	public boolean hasBeforeSeparation(Checker notSkip) throws IOException {
		int c;
		if (lt != -1) {
			c = lt;
			lt = -1;
		} else {
			c = reader.read();
		}
		while (!notSkip.check(c) && !newLineChecker(c) && !eOFChecker(c)) {
			c = reader.read();
		}
		lt = c;
		return notSkip.check( c);
	}

	public void skipLine() throws IOException {
		hasNext(MyScanner::newLineChecker);
		lt = -1;
	}

	public boolean hasIntBeforeSeparation() throws IOException {
		return hasBeforeSeparation(MyScanner::intChecker);
	}

	public boolean hasWordBeforeSeparation() throws IOException {
		return hasBeforeSeparation(MyScanner::wordChecker);
	}

	public boolean hasStringBeforeSeparation() throws IOException {
		return hasBeforeSeparation(MyScanner::stringChecker);
	}

	public boolean isEOF() throws IOException {
		return !hasStringBeforeSeparation() && eOFChecker(lt);
	}
}