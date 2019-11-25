package mnkGame;

public class ProxyBoard implements Position {
    private Position position;

    public ProxyBoard(Position board) {
        this.position = board;
    }

    @Override
    public boolean isValid(Move move) {
        return position.isValid(move);
    }

    @Override
    public Cell getCell(int r, int c) {
        return position.getCell(r, c);
    }

    @Override
    public int getK() { return position.getK(); }

    @Override
    public int getN() {
        return position.getN();
    }

    @Override
    public int getM() {
        return position.getM();
    }

    @Override
    public String toString() {
        return position.toString();
    }
}
