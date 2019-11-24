import java.io.*;
import java.math.BigInteger;
import java.util.Scanner;

public class Main {
    static BufferedWriter writer;
    static Scanner sc;
    static int mod = 999999937;
    static int[][] matrix = new int[5][5];
    static {
        matrix[0] = new int[]{1, 1, 1, 1, 1};
        matrix[1] = new int[]{1, 1, 1, 1, 1};
        matrix[2] = new int[]{1, 1, 1, 1, 1};
        matrix[3] = new int[]{1, 1, 0, 1, 0};
        matrix[4] = new int[]{1, 1, 0, 1, 0};
    }

    static int[][] mul(int[][] a, int[][] b) {
        int[][] c = new int[5][5];
        for (int i = 0; i < 5; i++)
            for (int j = 0; j < 5; j++) {
                c[i][j] = 0;
                for (int r = 0; r < 5; r++) {
                    c[i][j] += a[i][r] * b[r][j];
                    c[i][j] %= mod;
                }
            }
        return c;
    }
    static int[][] pow(BigInteger n) {
        if (n.equals(BigInteger.valueOf(1)))
            return matrix;
        int[][] a = pow(n.divide(BigInteger.valueOf(2)));
        a = mul(a, a);
        if (n.mod(BigInteger.valueOf(2)).equals(BigInteger.valueOf(1))) {
            a = mul(a, matrix);
        }
        return a;
    }

    static int solve() throws IOException {
        
        BigInteger n = new BigInteger(sc.next());
        if (n.equals(BigInteger.valueOf(0))) {
            return 1;
        }
        n = n.add(BigInteger.valueOf(-1));
        if (n.equals(BigInteger.valueOf(0))) {
            writer.write(5);
        } else {
            int[][] a = pow(n);
            int ans = 0;
            for (int i = 0; i < 5; i++) {
                for (int j = 0; j < 5; j++) {
                    ans = (ans + a[i][j]) % mod;
                }
            }
            writer.write(ans);
        }
        writer.newLine();
        return 0;
    }
    public static void main(String[] args) throws IOException {
        writer =  new BufferedWriter(
                new OutputStreamWriter(new FileOutputStream("sequences.out")));
        sc = new Scanner(new InputStreamReader(new FileInputStream("sequences.in")));
        int t = 0;
        while (t == 0)
            t = solve();
        writer.close();
    }
}