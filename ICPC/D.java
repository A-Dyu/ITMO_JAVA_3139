import java.io.*;
import java.util.*;

import static java.lang.Integer.max;
import static java.lang.Math.min;

public class Main {
    static StreamTokenizer in;

    static int nextInt() throws IOException
    {
        in.nextToken();
        return (int)in.nval;
    }

    public static void main(String[] args) throws IOException {
        in = new StreamTokenizer(new BufferedReader(new InputStreamReader(System.in)));
        long n = nextInt(), k = nextInt(), mod = 998244353;

        IntList[] div = new IntList[(int) n + 1];
        for (int i = 1; i <= n; i++) {
            div[i] = new IntList();
        }

        for (int i = 2; i <= n; i++) {
            for (int j = 2; j * j <= i; j++)
                if (i % j == 0) {
                    div[i].append(j);
                    if (j * j != i)
                        div[i].append(i / j);
                }
            div[i].append(1);
        }

        long[] pow = new long[(int) n / 2 + 2];
        pow[0] = 1;
        for (int i = 1; i <= n / 2 + 1; i++)
            pow[i] = (k * pow[i - 1]) % mod;

        long[] r = new long[(int) (n + 1)];
        for (int i = 1; i <= n; i++) {
            if (i % 2 == 1) {
                r[i] = (i * pow[(i + 1) / 2]) % mod;
            } else {
                r[i] = ((i / 2) * pow[i / 2] + (i / 2) * pow[i / 2 + 1]) % mod;
            }
        }

        for (int i = 2; i <= n; i++) {
            for (int j = 0; j < div[i].getSize(); j++) {
                r[i] -= (i / div[i].get(j) * r[(int) div[i].get(j)]) % mod;
                if (r[i] < 0)
                    r[i] += mod;
            }
        }

        long ans = 0;
        for (int i = 1; i <= n; i++) {
            for (int j = 0; j < div[i].getSize(); j++) {
                ans = (ans + r[(int) div[i].get(j)]) % mod;
            }
            ans = (ans + r[i]) % mod;
        }
        System.out.println(ans);
    }

    public static class IntList {
        long[] a = new long[1];
        int size = 0;

        public void append(long x) {
            if (a.length == size) {
                a = Arrays.copyOf(a, a.length * 2);
            }
            a[size++] = x;
        }

        public long get(int i) {
            return a[i];
        }

        public int getSize() {
            return size;
        }
    }

}