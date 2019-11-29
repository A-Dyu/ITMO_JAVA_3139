import java.io.*;
import java.util.*;

import static java.lang.Integer.max;
import static java.lang.Math.min;

public class Main {
    static StreamTokenizer in;
    static PrintWriter out;

   /* static int nextInt() throws IOException
    {
        in.nextToken();
        return (int)in.nval;
    }*/

    public static void main(String[] args) throws IOException {
        //in = new StreamTokenizer(new BufferedReader(new InputStreamReader(System.in)));
        //out = new PrintWriter(System.out);
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        sc.nextLine();
        int[][] a = new int[n][n];
        for (int i = 0; i < n; i++) {
            String s = sc.nextLine();
            for (int j = 0; j < n; j++) {
                a[i][j] = s.charAt(j) - (int) '0';
            }
        }
        int[][] ans = new int[n][n];
        for (int i = 0; i < n; i++) {
            for (int j = i + 1; j < n; j++) {
                if (a[i][j] != 0) {
                    ans[i][j] = 1;
                    for (int k = j + 1; k < n; k++) {
                        a[i][k] -= a[j][k];
                        if (a[i][k] < 0)
                            a[i][k] += 10;
                    }
                }
            }
        }
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                System.out.print(a[i][j]);
            }
            System.out.println();
        }
    }

}