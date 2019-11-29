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
        int n = nextInt(), m = nextInt();
        int[] w = new int[n];
        int[] ct = new int[n];
        IntList[] g = new IntList[n];
        for (int i = 0; i < n; i++) {
            g[i] = new IntList();
        }
        int[] q = new int[n];
        int[] used = new int[n];
        int tail = 0, head = 0;

        for (int i = 0; i < n - 1; i++) {
            int x = nextInt(), y = nextInt();
            g[x - 1].append(y - 1);
            g[y - 1].append(x - 1);
        }

        for (int i = 0; i < m; i++) {
            int x = nextInt();
            x--;
            used[x] = 1;
            q[head++] = x;
            ct[x] = 1;
        }

        while (head != tail) {
            int v = q[tail++];
            used[v] = 2;
            for (int i = 0; i < g[v].getSize(); i++) {
                int to = g[v].get(i);

                if (used[to] == 1) {
                    if (w[to] == w[v] + 1) {
                        ct[to] += ct[v];
                    }
                }

                if (used[to] == 0) {
                    w[to] = w[v] + 1;
                    used[to] = 1;
                    q[head++] = to;
                    ct[to] = ct[v];
                }
            }
        }
        for (int i = 0; i < n; i++) {
            if (ct[i] == m) {
                System.out.println("YES");
                System.out.println(i + 1);
                return;
            }
        }
        System.out.println("NO");
    }

    public static class IntList {
        int[] a = new int[1];
        int size = 0;

        public void append(int x) {
            if (a.length == size) {
                a = Arrays.copyOf(a, a.length * 2);
            }
            a[size++] = x;
        }

        public int get(int i) {
            return a[i];
        }

        public int getSize() {
            return size;
        }
    }
}