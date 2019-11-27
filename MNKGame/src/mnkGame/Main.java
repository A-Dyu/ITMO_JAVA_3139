package mnkGame;

import java.util.List;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        final Game game = new Game(true, List.of(new HumanPlayer(), new HumanPlayer(), new HumanPlayer()));
        game.start();
    }
}
