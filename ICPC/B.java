import java.util.*;

public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        for (int i = -25000 * 710; n > 0; i += 710) {
            System.out.println(i);
            n--;
        }
    }
}