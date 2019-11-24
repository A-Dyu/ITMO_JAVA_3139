import java.io.*;
public class Main {
	public static void main(String[] args) throws IOException {
		MyScanner sc = new MyScanner("input.txt");
		while (sc.hasNextInt()) {
			System.out.println(sc.nextInt());
		}
	}
}