package nmkGame;

import java.util.Arrays;
import java.util.Map;

public class NMKBoard implements Board, Position {
    private static final Map<Cell, Character> SYMBOLS = Map.of(
            Cell.X, 'X',
            Cell.O, 'O',
            Cell.E, '.'
    );

    private final Cell[][] cells;
    private Cell turn;
    private final int k;
    private int cellCounter = 0;

    public NMKBoard(int n, int m, int k) {
        this.cells = new Cell[n][m];
        for (Cell[] row : cells) {
            Arrays.fill(row, Cell.E);
        }
        turn = Cell.X;
        this.k = k;
    }

    @Override
    public Position getPosition() {
        return this;
    }

    @Override
    public Cell getCell() { return turn; }

    private int deltaCounter(int row, int column, int dx, int dy) {
        int counter = 0;
        for (int x = row, y = column; x < cells.length && x >= 0 && y < cells[0].length && y >= 0 && cells[x][y] == turn; x += dx, y += dy) {
            counter++;
        }
        return counter;
    }

    private boolean deltaChecker(int row, int column, int dx, int dy) {
        return (deltaCounter(row, column, dx, dy) + deltaCounter(row, column, -dx, -dy) - 1) >= k;
    }

    @Override
    public Result makeMove(final Move move) {
        if (!isValid(move)) {
            return Result.LOSE;
        }

        cells[move.getRow()][move.getColumn()] = turn;
        cellCounter++;
        if (deltaChecker(move.getRow(), move.getColumn(), 0, 1) ||
                deltaChecker(move.getRow(), move.getColumn(), 1, 0) ||
                    deltaChecker(move.getRow(), move.getColumn(), 1, 1) ||
                        deltaChecker(move.getRow(), move.getColumn(), 1, -1)) {
            return Result.WIN;
        }
        if (cellCounter == cells.length * cells[0].length) {
            return Result.DRAW;
        }

        turn = turn == Cell.X ? Cell.O : Cell.X;
        return Result.UNKNOWN;
    }

    @Override
    public boolean isValid(final Move move) {
        return 0 <= move.getRow() && move.getRow() < cells.length
                && 0 <= move.getColumn() && move.getColumn() < cells[0].length
                && cells[move.getRow()][move.getColumn()] == Cell.E
                && turn == getCell();
    }

    @Override
    public Cell getCell(final int r, final int c) {
        return cells[r][c];
    }

    @Override
    public int getK() {
        return k;
    }

    @Override
    public int getN() {
        return cells.length;
    }

    @Override
    public int getM() {
        return cells[0].length;
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder();
        for (int i = 0; i <= Integer.toString(cells.length).length() + 1; i++)
            sb.append(" ");
        for (int i = 1; i <= cells[0].length; i++) {
            sb.append(i);
            for (int j = 0; j <= Integer.toString(cells[0].length).length() - Integer.toString(i).length(); j++)
                sb.append(" ");
        }
        for (int r = 0; r < cells.length; r++) {
            sb.append("\n");
            sb.append(r + 1);
            for (int i = 0; i < Integer.toString(cells.length).length() - Integer.toString(r + 1).length(); i++)
                sb.append(" ");
            for (int c = 0; c < cells[0].length; c++) {
                for (int i = 0; i < Integer.toString(cells[0].length).length(); i++)
                    sb.append(" ");
                sb.append(SYMBOLS.get(cells[r][c]));
            }
        }
        return sb.toString();
    }
}
