package nmkGame;

public class Game {
    private final boolean log;
    private final Player[] player = new Player[2];

    public Game(final boolean log, final Player player1, final Player player2) {
        this.log = log;
        this.player[0] = player1;
        this.player[1] = player2;
    }

    public int play(Board board) {
        while (true) {
            final int result1 = move(board, 0);
            if (result1 != -1) {
                return result1;
            }
            final int result2 = move(board, 1);
            if (result2 != -1) {
                return result2;
            }
        }
    }

    private int move(final Board board, final int no) {
        final Move move = player[no].move(new ProxyBoard(board.getPosition()), board.getCell());
        final Result result = board.makeMove(move);
        log("Player " + (no + 1) + " move: " + move);
        log("Position:\n" + board);
        if (result == Result.WIN) {
            log("Player " + (no + 1) + " won");
            return no;
        } else if (result == Result.LOSE) {
            log("Player " + (no + 1) + " lose");
            return 1 - no;
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
