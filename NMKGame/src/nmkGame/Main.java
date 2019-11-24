package nmkGame;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int n = scanner.nextInt(), m = scanner.nextInt(), k = scanner.nextInt();
        final Game game = new Game(true, new HumanPlayer(), new HumanPlayer());
        int result = game.play(new NMKBoard(n, m, k));
        System.out.println("Game result: " + result + 1);
    }
}
