package nmkGame;

import java.util.InputMismatchException;
import java.util.Scanner;

public class Game {
    private final boolean log;
    private final Player[] players = new Player[2];

    public Game(final boolean log, final Player player1, final Player player2) {
        this.log = log;
        this.players[0] = player1;
        this.players[1] = player2;
    }

    public int start() {
        Scanner scanner = new Scanner(System.in);
        while (true) {
            try {
                log("Insert n, m, k");
                int n = scanner.nextInt(), m = scanner.nextInt(), k = scanner.nextInt();
                return play(new NMKBoard(n, m, k));
            } catch (InputMismatchException e) {
                log("Invalid input. Please try again.");
                scanner.nextLine();
            }
        }
    }

    public int start(int n, int m, int k) {
        return play(new NMKBoard(n, m, k));
    }

    private int play(Board board) {
        while (true) {
            for (int i = 0; i < 2; i++) {
                final int result = move(board, 0);
                if (result != -1) {
                    return result;
                }
            }
        }
    }

    private int move(final Board board, final int id) {
        final Move move = players[id].move(new ProxyBoard(board.getPosition()), board.getCell());
        final Result result = board.makeMove(move);
        log("Player " + (id + 1) + " move: " + move);
        log("Position:\n" + board);
        if (result == Result.WIN) {
            log("Player " + (id + 1) + " won");
            return id;
        } else if (result == Result.LOSE) {
            log("Player " + (id + 1) + " lose");
            return 1 - id;
        } else if (result == Result.DRAW) {
            log("Draw");
            return 2;
        } else {
            return -1;
        }
    }

    private void log(final String message) {
        if (log) {
            System.out.println(message);
        }
    }
}
