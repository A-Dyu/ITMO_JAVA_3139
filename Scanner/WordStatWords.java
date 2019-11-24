import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
import java.io.*;
public class WordStatWords {
    public static Boolean inWord(char c) {
        return (Character.isLetter(c) || Character.getType(c) == Character.DASH_PUNCTUATION || (c == '\''));
    }
    public static void main(String[] args) {
        if (args.length < 2) {
            System.out.println("Less then 2 arguments");
            return;
        }

        Map<String, Integer> mp = new HashMap<String, Integer>();

        try {
            MyScanner sc = new MyScanner(args[0]);
            try {
                while (sc.hasNextWord()) {
                    
                    mp.merge(sc.nextWord().toLowerCase(), 1, Integer::sum); 
                }
            } finally {
                sc.close();
            } 

        } catch (FileNotFoundException e) {
            System.out.println("No such file " + e.getMessage());
            return;
        }   catch (IOException e) {
                System.out.println("Error reading input file: " + e.getMessage());
                return;
        }

        String[] ans = Arrays.stream(mp.keySet().toArray()).toArray(String[]::new);
        Arrays.sort(ans);

        try {
            BufferedWriter writer = new BufferedWriter(
                    new OutputStreamWriter(new FileOutputStream(args[1]), "utf8"));
            try {
                for (int i = 0; i < ans.length; i++) {
                    writer.write(ans[i] + " " + mp.get(ans[i]));
                    writer.newLine();
                } 
            } finally {
                writer.close();
            }

        } catch (FileNotFoundException e) {
            System.out.println("Can't create/open the output file " + e.getMessage());

        } catch (IOException e) {
        System.out.println("Error writing output file: " + e.getMessage());
        }
    }
}