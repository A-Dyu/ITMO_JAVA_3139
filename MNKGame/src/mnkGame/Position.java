package mnkGame;

public interface Position {
    boolean isValid(Move move);

    char getCell(int r, int c);

    int getK();
    int getN();
    int getM();
}
