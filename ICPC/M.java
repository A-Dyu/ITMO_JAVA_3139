import java.io.*;
import java.util.*;

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
        int t = nextInt();
        for (int i = 0; i < t; i++) {
            solve();
        }
    }

    static void solve() throws IOException {
        int n = nextInt(), ans = 0;
        int[] a = new int[n];
        for (int i = 0; i < n; i++) {
            a[i] = nextInt();
        }
        Map<Integer, Integer> m = new HashMap<Integer, Integer>();
        for (int j = n - 1; j > 0; j--) {
            for (int i = 0; i < j; i++) {
                if (m.containsKey(2 * a[j] - a[i])) {
                    ans += m.get(2 * a[j] - a[i]);
                }
            }
            m.merge(a[j], 1, Integer::sum);
        }
       System.out.println(ans);
    }
}