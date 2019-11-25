package md2html;

import java.io.*;
import java.util.ArrayList;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(new FileInputStream("input.txt")));
        ArrayList<String> lines = new ArrayList<>();
        while (true) {
            String line = bufferedReader.readLine();
            if (line == null)
                break;
            lines.add(line);
        }
        String html = ToHTMLText.toHtml(lines);
        BufferedWriter bufferedWriter = new BufferedWriter(new OutputStreamWriter(new FileOutputStream("output.txt")));
        bufferedWriter.write(html);
        bufferedWriter.close();
    }
}
