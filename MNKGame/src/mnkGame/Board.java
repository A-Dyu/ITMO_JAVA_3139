package mnkGame;

interface Board {
    Position getPosition();
    char getCell();
    Result makeMove(Move move);
}
