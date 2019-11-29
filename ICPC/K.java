import java.io.*;
import java.util.*;
 
import static java.lang.Integer.max;
import static java.lang.Math.abs;
import static java.lang.Math.min;
 
public class Main {
    static char[][] a = new char[1000][1000];
    public static boolean check(char c) {
        return c == '.' || c == 'A';
    }
    static void fillRectangle(int ul, int ur, int hl, int hr) {
        for (int i = ul + 1; i < ur; i++) {
            for (int j = hl; j < hr; j++) {
                if (a[i][j] == '.') {
                    a[i][j] = Character.toLowerCase(a[i - 1][j]);
                }
            }
        }
        for (int i = ur - 2; i >= ul; i--) {
            for (int j = hl; j < hr; j++) {
                if (a[i][j] == '.') {
                    a[i][j] = Character.toLowerCase(a[i + 1][j]);
                }
            }
        }
        for (int i = ul; i < ur; i++) {
            for (int j = hl + 1; j < hr; j++) {
                if (a[i][j] == '.') {
                    a[i][j] = Character.toLowerCase(a[i][j - 1]);
                }
            }
        }
        for (int i = ul; i < ur; i++) {
            for (int j = hr - 2; j >= hl; j--) {
                if (a[i][j] == '.') {
                    a[i][j] = Character.toLowerCase(a[i][j + 1]);
                }
            }
        }
    }
 
    public static void main(String[] args) throws IOException {
        PrintWriter out = new PrintWriter(System.out);
        MyReader sc = new MyReader(System.in);
        ChildList list = new ChildList();
        int n = sc.nextInt(), m = sc.nextInt(), x = 0, y = 0;
        for (int i = 0; i < n; i++) {
            String s = sc.nextWord();
            for (int j = 0; j < m; j++) {
                a[i][j] = s.charAt(j);
                if (a[i][j] == 'A') {
                    x = i;
                    y = j;
                } else if (Character.isLetter(a[i][j])) {
                    list.append(i, j);
                }
            }
        }
 
        int ans = 0, left, right, i1 = 0, i2 = 0, j1 = 0, j2 = 0;
        for (int up = x; up >= 0 && check(a[up][y]) ; up--) {
            for (int down = x; down < n && check(a[down][y]); down++) {
                left = -1;
                right = m;
                for (int i = 0; i < list.size; i++) {
                    if (list.x[i] >= up && list.x[i] <= down && list.y[i] < y) {
                        left = max(left, list.y[i]);
                    }
                    if (list.x[i] >= up && list.x[i] <= down && list.y[i] > y) {
                        right = min(right, list.y[i]);
                    }
                }
                int cur = (abs(right - left) - 1) * (abs(up - down) + 1);
                if (ans < cur) {
                    ans = cur;
                    i1 = up;
                    i2 = down;
                    j1 = left + 1;
                    j2 = right - 1;
                }
            }
        }
        for (int i = i1; i <= i2; i++) {
            for (int j = j1; j <= j2; j++) {
                if (a[i][j] != 'A') {
                    a[i][j] = 'a';
                }
            }
        }
        fillRectangle(0, i1, 0, m);
        fillRectangle(i1, i2 + 1, 0, j1);
        fillRectangle(i1, i2 + 1, j2 + 1, m);
        fillRectangle(i2 + 1, n, 0, m);
 
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                out.print(a[i][j]);
            }
            out.println();
        }
        out.close();
    }
    static class ChildList {
        int[] x = new int[1];
        int[] y = new int[1];
        int size = 0;
 
        void append(int a, int b) {
            if (size == x.length) {
                x = Arrays.copyOf(x, x.length * 2);
                y = Arrays.copyOf(y, y.length * 2);
            }
            x[size] = a;
            y[size++] = b;
        }
    }
    static class MyReader {
        BufferedInputStream in;
 
        final int bufSize = 1 << 16;
        final byte b[] = new byte[bufSize];
 
        MyReader(InputStream in) {
            this.in = new BufferedInputStream(in, bufSize);
        }
 
        int nextInt() throws IOException {
            int c;
            while ((c = nextChar()) <= 32)
                ;
            int x = 0, sign = 1;
            if (c == '-') {
                sign = -1;
                c = nextChar();
            }
            while (c >= '0') {
                x = x * 10 + (c - '0');
                c = nextChar();
            }
            return x * sign;
        }
 
        StringBuilder _buf = new StringBuilder();
        String nextWord() throws IOException {
            int c;
            _buf.setLength(0);
            while ((c = nextChar()) <= 32 && c != -1)
                ;
            if (c == -1)
                return null;
            while (c > 32) {
                _buf.append((char)c);
                c = nextChar();
            }
            return _buf.toString();
        }
 
        int bn = bufSize, k = bufSize;
 
        int nextChar() throws IOException {
            if (bn == k) {
                k = in.read(b, 0, bufSize);
                bn = 0;
            }
            return bn >= k ? -1 : b[bn++];
        }
 
        int nextNotSpace() throws IOException {
            int ch;
            while ((ch = nextChar()) <= 32 && ch != -1)
                ;
            return ch;
        }
    }
}