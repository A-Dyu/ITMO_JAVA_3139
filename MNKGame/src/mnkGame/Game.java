package mnkGame;

import java.util.List;

public class Game {
    private final boolean log;
    private final List<Player> players;

    public Game(final boolean log, final List<Player> playerList) {
        if (playerList.size() > Cell.getSize() - 1) {
            throw new IllegalArgumentException("Too much players in list");
        }

        this.log = log;
        players = playerList;
    }

    public int getPlayersCount() {
        return players.size();
    }

    public int play(Board board) {
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
        final Move move = players.get(id).move(new ProxyPosition(board.getPosition()), board.getCell());
        final Result result = board.makeMove(move);
        log("Player " + (id + 1) + " move: " + move);
        log("Position:\n" + board);
        if (result == Result.WIN) {
            log("Player " + Cell.getCell(id) + " won");
            return id + 1;
        } else if (result == Result.LOSE) {
            log("Player " + Cell.getCell(id) + " lost!");
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
