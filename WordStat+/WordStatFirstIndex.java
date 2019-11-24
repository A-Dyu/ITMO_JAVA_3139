import java.util.*;
import java.io.*;
public class WordStatFirstIndex {
	public static void main(String[] args) {
		Map<String, InputCounter> mp = new LinkedHashMap<>();
		try {
			MyScanner sc = new MyScanner(args[0]);
			int k = 1, strK = 0;
			try {
				while (sc.hasNextWord()) {
					String word = sc.nextWord().toLowerCase();
					mp.computeIfAbsent(word, t -> new InputCounter()).append(k, strK);
					if (!sc.hasWordBeforeSeparation()) {
						strK++;
						k = 0;
					}
					k++;
				}
			} finally {
				sc.close();
			}
		} catch (FileNotFoundException e) {
			System.out.println("No such file: " + e.getMessage());
			return;
		} catch (IOException e) {
			System.out.println("Error reading input file: " + e.getMessage());
			return;
		}
		try {
			BufferedWriter writer = new BufferedWriter(
                    new OutputStreamWriter(new FileOutputStream(args[1]), "utf8"));
			try {
				for (Map.Entry<String, InputCounter> entry : mp.entrySet()) {
					writer.write(entry.getKey() + " " + entry.getValue().getAnswer());
					writer.newLine();
				}
			} finally {
				writer.close();
			}
		} catch (FileNotFoundException e) {
			System.out.println("Can't create/open an output file: " + e.getMessage());
		} catch (IOException e) {
			System.out.println("Error writing output file: " + e.getMessage());
		}
	}
}