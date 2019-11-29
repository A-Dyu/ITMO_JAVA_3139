import java.io.*;
import java.util.*;

import static java.lang.Integer.max;
import static java.lang.Math.min;

public class Main {
    static StreamTokenizer in;
    static PrintWriter out;

    static int nextInt() throws IOException
    {
        in.nextToken();
        return (int)in.nval;
    }

    public static void main(String[] args) throws IOException {
        in = new StreamTokenizer(new BufferedReader(new InputStreamReader(System.in)));
        out = new PrintWriter(System.out);
        int n = nextInt(), xl = (int) 1e9, xr = (int) -1e9, yl = (int) 1e9, yr = (int) -1e9;
        for (int i = 0; i < n; i++) {
            int x = nextInt(), y = nextInt(), h = nextInt();
            xl = min(x - h, xl);
            xr = max(x + h, xr);
            yl = min(y - h, yl);
            yr = max(y + h, yr);
        }
        System.out.println((xl + xr) / 2 + " " + (yl + yr) / 2 + " " + max(xr - xl + 1, yr - yl + 1) / 2);
    }

}