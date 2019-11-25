package md2html;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class Md2Html {
    public static void main(String[] args) {

        List<String> lines = new ArrayList<>();
        try {
            BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(new FileInputStream(args[0]), "UTF-8"));
            try {
                while (true) {
                    String line = bufferedReader.readLine();
                    if (line == null) {
                        break;
                    }
                    lines.add(line);
                }
            } finally {
                bufferedReader.close();
            }
        } catch (FileNotFoundException e) {
            System.out.println("No such file " + e.getMessage());
            return;
        } catch (IOException e) {
            System.out.println("Error reading input file: " + e.getMessage());
            return;
        }

        String html = ToHTMLText.toHtml(lines);
        try {
            BufferedWriter bufferedWriter = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(args[1]), "UTF-8"));
            try {
                bufferedWriter.write(html);
            } finally {
                bufferedWriter.close();
            }
        } catch (FileNotFoundException e) {
            System.out.println("Can't create/open an output file: " + e.getMessage());
        } catch (IOException e) {
            System.out.println("Error writing output file: " + e.getMessage());
        }
    }
}
