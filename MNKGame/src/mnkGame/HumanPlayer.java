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

    @Override
    public Move move(final Position position, final char cell) {
        while (true) {
            out.println("Position");
            out.println(position);
            out.println(cell + "'s move");
            out.println("Enter row and column");
            Move move;
            while (true) {
                try {
                    move = new Move(in.nextInt() - 1, in.nextInt() - 1, cell);
                    break;
                } catch (InputMismatchException e) {
                    out.println("Invalid arguments, please try again");
                    in.nextLine();
                }
            }
            if (position.isValid(move)) {
                return move;
            }
            out.println("Move " + move + " is invalid");
        }
    }
}
