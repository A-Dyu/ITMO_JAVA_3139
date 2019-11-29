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
        int n = nextInt(), mx = -1;
        int[] a = new int[n];
        for (int i = 0; i < n; i++) {
            a[i] = nextInt();
            mx = max(mx, a[i]);
            if (i > 0)
                a[i] += a[i - 1];
        }
        int[] ans = new int[(int) 1e6 + 3];
        int q = nextInt();
        for (int t0 = 0; t0 < q; t0++) {
            int t = nextInt();
            if (t < mx) {
                System.out.println("Impossible");
                continue;
            }
            if (ans[t] > 0) {
                System.out.println(ans[t]);
                continue;
            }
            int cur = 0, p = -1, k = 0;
            while (p < n - 1) {
                int l = p + 1, r = n;
                while (r - l > 1) {
                    int m = (l + r) / 2;
                    if (a[m] > cur + t)
                        r = m;
                    else
                        l = m;
                }
                k++;
                p = l;
                cur = a[l];
            }
            ans[t] = k;
            System.out.println(k);
        }
    }

}