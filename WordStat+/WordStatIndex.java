import java.util.*;
import java.io.*;
public class WordStatIndex {
	public static void main(String[] args) {
		Map <String, IntList> mp = new LinkedHashMap<>();
		try {
			MyScanner sc = new MyScanner(args[0]);
			int k = 1;
			try {
				while (sc.hasNextWord()) {
					String word = sc.nextWord().toLowerCase();
					if (!mp.containsKey(word)) {
						mp.put(word, new IntList(k));
					} else {
						mp.get(word).append(k);
					}
					k++;
				}
			} catch (IOException e) {
				System.out.println("Error reading input file: " + e.getMessage());
			} finally {
				sc.close();
			}
		} catch (FileNotFoundException e) {
			System.out.println("No such file: " + e.getMessage());
			return;
		} catch (IOException e) {
			System.out.println("I/O exception: " + e.getMessage());
			return;
		}
		try {
			BufferedWriter writer = new BufferedWriter(
                    new OutputStreamWriter(new FileOutputStream(args[1]), "utf8"));
			try {
				for (Map.Entry<String, IntList> entry : mp.entrySet()) {
					writer.write(entry.getKey() + " " + entry.getValue().size());
					for (int i = 0; i < entry.getValue().size(); i++) {
						writer.write(" " + entry.getValue().get(i));
					}
					writer.newLine();
				}
			} catch (IOException e) {
				System.out.println("Error writing ouput file: " + e.getMessage());
			} finally {
				writer.close();
			}
		} catch (FileNotFoundException e) {
			System.out.println("Can't create/open an output file: " + e.getMessage());
		} catch (IOException e) {
			System.out.println("I/O exception: " + e.getMessage());
		}
	}
}