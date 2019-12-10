package mnkGame;

public class SequentialPlayer implements Player {
    @Override
    public Move move(Position position, char cell) {
        for (int i = 0; i < position.getN(); i++) {
            for (int j = 0; j < position.getM(); j++) {
                Move move = new Move(i, j, cell);
                if (position.isValid(move)) {
                    return move;
                }
            }
        }
        return null;
    }
}
