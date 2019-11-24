package nmkGame;

public interface Position {
    boolean isValid(Move move);

    Cell getCell(int r, int c);

    int getK();
    int getN();
    int getM();
}
