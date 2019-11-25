package nmkGame;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        final Game game = new Game(true, new RandomPlayer(), new HumanPlayer());
        game.start();
    }
}
