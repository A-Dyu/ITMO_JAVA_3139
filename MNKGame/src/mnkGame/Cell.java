package mnkGame;

public class Cell {
    private static char[] cells = {'X', 'O','-', '|', '.'};

    public static char getCell(int i) {
        return cells[i];
    }

    public static int getSize() {
        return cells.length;
    }
}
