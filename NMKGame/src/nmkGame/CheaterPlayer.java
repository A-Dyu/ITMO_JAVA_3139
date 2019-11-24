package nmkGame;

public class CheaterPlayer implements Player {
    @Override
    public Move move(final Position position, final Cell cell) {
        Board board = (Board) position;
        Move move = new Move(0, 0, cell);
        board.makeMove(move);
        move = new Move(2, 2, cell);
        board.makeMove(move);
        move = new Move(0, 2, cell);
        board.makeMove(move);
        move = new Move(2, 0, cell);
        board.makeMove(move);
        move = new Move(0, 1, cell);
        return move;
    }
}
