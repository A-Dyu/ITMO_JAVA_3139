package mnkGame;

import java.util.Arrays;

public class MNKBoard implements Board, Position {


    private final char[][] cells;
    private int turn = 0;
    private final int k;
    private int cellCounter = 0;
    private final int playerCount;

    public MNKBoard(final int n, final int m, final int k, final int playerCount) throws IllegalArgumentException {
        if (n <= 0 || m <= 0 || k <= 0) {
            throw new IllegalArgumentException();
        }
        this.cells = new char[n][m];
        for (char[] row : cells) {
            Arrays.fill(row, Cell.getCell(Cell.getSize() - 1));
        }
        this.k = k;
        this.playerCount = playerCount;
    }

    @Override
    public Position getPosition() {
        return this;
    }

    @Override
    public char getCell() { return Cell.getCell(turn); }

    private int deltaCounter(int row, int column, int dx, int dy) {
        int counter = 0;
        for (int x = row, y = column; x < cells.length && x >= 0 && y < cells[0].length && y >= 0 && cells[x][y] == cells[row][column]; x += dx, y += dy) {
            counter++;
        }
        return counter;
    }

    private boolean deltaChecker(int row, int column, int dx, int dy) {
        return (deltaCounter(row, column, dx, dy) + deltaCounter(row, column, -dx, -dy) - 1) >= k;
    }

    private Result getResult(Move move) {
        if (deltaChecker(move.getRow(), move.getColumn(), 0, 1) ||
                deltaChecker(move.getRow(), move.getColumn(), 1, 0) ||
                    deltaChecker(move.getRow(), move.getColumn(), 1, 1) ||
                        deltaChecker(move.getRow(), move.getColumn(), 1, -1)) {
            return Result.WIN;
        }
        if (cellCounter == cells.length * cells[0].length) {
            return Result.DRAW;
        }
        return Result.UNKNOWN;
    }

    @Override
    public Result makeMove(final Move move) {
        if (!isValid(move)) {
            return Result.LOSE;
        }

        cells[move.getRow()][move.getColumn()] = Cell.getCell(turn);
        cellCounter++;
        Result result = getResult(move);
        turn = (turn + 1) % playerCount;
        return result;
    }

    @Override
    public boolean isValid(final Move move) {
        return move != null && 0 <= move.getRow() && move.getRow() < cells.length
                && 0 <= move.getColumn() && move.getColumn() < cells[0].length
                && cells[move.getRow()][move.getColumn()] == Cell.getCell(Cell.getSize() - 1) && move.getCell() == Cell.getCell(turn);
    }

    @Override
    public char getCell(final int r, final int c) {
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
        int lenN = Integer.toString(cells.length).length(), lenM = Integer.toString(cells[0].length).length();
        final StringBuilder sb = new StringBuilder(" ".repeat(lenN + 1));
        for (int i = 1; i <= cells[0].length; i++) {
            sb.append(String.format("%" + lenM +"d ", i));
        }
        for (int r = 0; r < cells.length; r++) {
            sb.append("\n");
            sb.append(String.format("%" + lenN + "d ", (r + 1)));
            for (int c = 0; c < cells[0].length; c++) {
                sb.append(String.format("%" + lenM + "s ", cells[r][c]));
            }
        }
        return sb.toString();
    }
}
