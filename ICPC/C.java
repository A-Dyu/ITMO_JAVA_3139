import java.io.*;
import java.util.*;

import static java.lang.Integer.max;
import static java.lang.Math.min;

public class Main {
    static StreamTokenizer in;
    static Map<Pair, PairList> up = new HashMap<>();
    static Map<Pair, PairList> down = new HashMap<>();
    static PairList ans = new PairList();

    static int nextInt() throws IOException
    {
        in.nextToken();
        return (int)in.nval;
    }
    static void add(Map<Pair, PairList> mp, Pair v, Pair to) {
        mp.computeIfAbsent(v, k->new PairList()).append(to);
        mp.computeIfAbsent(to, k->new PairList()).append(v);
    }

    static void dfs(Pair v, boolean sur) {
        if (!sur) {
            while (down.get(v).getSize() > 0) {
                for (int i = 0; i < down.get(v).getSize(); i++) {
                    Pair to = down.get(v).get(i);
                    down.get(v).del(to);
                    down.get(to).del(v);
                    dfs(to, true);
                    ans.append(v);
                }
            }
        } else {
            while (up.get(v).getSize() > 0) {
                for (int i = 0; i < up.get(v).getSize(); i++) {
                    Pair to = up.get(v).get(i);
                    up.get(v).del(to);
                    up.get(to).del(v);
                    dfs(to, false);
                    ans.append(v);
                }
            }
        }
    }

    public static void main(String[] args) throws IOException {
        //in = new StreamTokenizer(new BufferedReader(new InputStreamReader(System.in)));
        Scanner sc = new Scanner(System.in);
        int m = sc.nextInt(), n = sc.nextInt();

        sc.nextLine();
        Pair start = null;

        for (int i = 0; i < n; i++) {
            String s = sc.nextLine();
            for (int j = 0; j < m; j++) {
                if (s.charAt(j) == 'X') {
                    if (start == null) {
                        start = new Pair(i + 1, j);
                    }

                    add(down, new Pair(i, j), new Pair(i + 1, j));
                    add(down, new Pair(i, j + 1), new Pair(i + 1, j + 1));
                    add(up, new Pair(i + 1, j), new Pair(i, j + 1));
                    add(up, new Pair(i + 1, j + 1), new Pair(i, j));
                }
            }
        }

        dfs(start, true);
        System.out.println(ans.getSize() - 1);
        for (int i = ans.getSize() - 1; i >= 0; i--)
            System.out.println(ans.get(i));
    }

    public static class PairList {
        Pair[] a = new Pair[1];
        int size = 0;


        public void append(Pair x) {
            if (a.length == size) {
                a = Arrays.copyOf(a, a.length * 2);
            }
            a[size++] = x;
        }

        public Pair get(int i) {
            return a[i];
        }

        public int getSize() {
            return size;
        }

        public void del(Pair x) {
                int i = -1;
                for (int j = 0; j < size; j++)
                    if (x.equals(a[j])) {
                        i = j;
                        break;
                    }
                if (i == -1)
                    return;
                while (i != size - 1) {
                    Pair c = a[i];
                    a[i] = a[i + 1];
                    a[i + 1] = c;
                    i++;
                }
                size--;
        }
        public void print() {
            System.out.print("( ");
            for (int i = 0; i < size; i++)
                System.out.print("[" + a[i] + "] ");
            System.out.println(")");
        }
    }

    public static class Pair {
        int x;
        int y;

        public Pair(int x, int y) {
            this.x = x;
            this.y = y;
        }

        @Override
        public boolean equals(final Object o) {
            final Pair a = (Pair) o;
            return a.x == this.x && a.y == this.y;
        }

        @Override
        public int hashCode()  {
            return Objects.hash(this.x, this.y);
        }

        @Override
        public String toString() {
            return y + " " + x;
        }
    }
}