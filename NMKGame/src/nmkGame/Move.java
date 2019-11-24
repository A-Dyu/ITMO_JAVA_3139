package nmkGame;

public final class Move {
    private final int row;
    private final int column;
    private final Cell value;

    public Move(final int row, final int column, final Cell value) {
        this.row = row;
        this.column = column;
        this.value = value;
    }

    public int getRow() {
        return row;
    }

    public int getColumn() {
        return column;
    }

    @Override
    public String toString() {
        return "row=" + (row + 1) + ", column=" + (column + 1) + ", value=" + value;
    }
}
