package mnkGame;

import java.util.InputMismatchException;
import java.util.List;
import java.util.Scanner;

public class Game {
    private final boolean log;
    private final List<Player> players;

    public Game(final boolean log, final List<Player> playerList) {
        this.log = log;
        if (playerList.size() > Cell.getSize() - 1) {
            throw new IllegalArgumentException("Too much players in list");
        }
        players = playerList;
    }

    public int start() {
        Scanner scanner = new Scanner(System.in);
        while (true) {
            try {
                log("Insert m, n, k");
                int m = scanner.nextInt(), n = scanner.nextInt(), k = scanner.nextInt();
                return play(new MNKBoard(n, m, k, players.size()));
            } catch (InputMismatchException e) {
                log("Invalid input. Please try again.");
                scanner.nextLine();
            }
        }
    }

    public int start(int n, int m, int k) {
        if (n <= 0 || m <= 0 || k <= 0) {
            throw new IllegalArgumentException();
        }
        return play(new MNKBoard(n, m, k, players.size()));
    }

    private int play(Board board) {
        while (true) {
            for (int i = 0; i < 2; i++) {
                final int result = move(board, i);
                if (result != 0) {
                    return result;
                }
            }
        }
    }

    private int move(final Board board, final int id) {
        final Move move = players.get(id).move(new ProxyBoard(board.getPosition()), board.getCell());
        final Result result = board.makeMove(move);
        log("Player " + (id + 1) + " move: " + move);
        log("Position:\n" + board);
        if (result == Result.WIN) {
            log("Player " + Cell.getCell(id) + " won");
            return id + 1;
        } else if (result == Result.LOSE) {
            log("Player " + Cell.getCell(id) + " has been cheating!");
            return -(id + 1);
        } else if (result == Result.DRAW) {
            log("Draw");
            return players.size() + 1;
        } else {
            return 0;
        }
    }

    private void log(final String message) {
        if (log) {
            System.out.println(message);
        }
    }
}
