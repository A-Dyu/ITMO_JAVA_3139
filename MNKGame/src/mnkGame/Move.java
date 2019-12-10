package mnkGame;

public final class Move {
    private final int row;
    private final int column;
    private final char cell;

    public Move(final int row, final int column, char cell) {
        this.row = row;
        this.column = column;
        this.cell = cell;
    }

    public int getRow() {
        return row;
    }

    public int getColumn() {
        return column;
    }

    public char getCell() {
        return cell;
    }

    @Override
    public String toString() {
        return "row=" + (row + 1) + ", column=" + (column + 1);
    }
}
