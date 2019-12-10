package mnkGame;

import java.io.PrintStream;
import java.util.InputMismatchException;
import java.util.Scanner;

public class HumanPlayer implements Player {
    private final PrintStream out;
    private final Scanner in;

    public HumanPlayer(final PrintStream out, final Scanner in) {
        if (out == null || in == null) {
            throw new IllegalArgumentException();
        }
        this.out = out;
        this.in = in;
    }

    public HumanPlayer() {
        this(System.out, new Scanner(System.in));
    }


    private int nextInt() {
        while (true) {
            if (in.hasNextInt()) {
                return in.nextInt();
            } else {
                out.println("Invalid input, try again!");
            }
        }
    }
    @Override
    public Move move(final Position position, final char cell) {
        while (true) {
            out.println("Position");
            out.println(position);
            out.println(cell + "'s move");
            out.println("Enter row and column");
            Move move = new Move(nextInt(), nextInt(), cell);
            if (position.isValid(move)) {
                return move;
            }
            out.println("Move " + move + " is invalid");
        }
    }
}
