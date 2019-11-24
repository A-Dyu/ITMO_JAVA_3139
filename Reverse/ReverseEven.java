import java.util.*;

public class ReverseEven {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int[][] a = new int[1][];
        int i = 0;
        int[] b = new int[1];
        while (sc.hasNextLine()) {
            Scanner s = new Scanner(sc.nextLine());
            int j = 0;
            while   (s.hasNextInt()) {
                if (j == b.length) {
                    b = Arrays.copyOf(b, b.length * 2);
                }
                b[j++] = s.nextInt();

            }
            if (i == a.length) {
                a = Arrays.copyOf(a, a.length * 2);
            }
            a[i++] = Arrays.copyOf(b, j);
        }

        for (i = i - 1; i >= 0; i--) {
            for (int j = a[i].length - 1; j >= 0; j--) {
                if (a[i][j] % 2 == 0) {
                    System.out.print(a[i][j] + " ");
                }
            }
            System.out.println();
        }
    }
}