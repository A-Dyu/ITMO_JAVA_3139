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
            if (in.hasNextInt()) {
                return in.nextInt();
            } else {
                in.nextLine();
                return 0;
            }
    }
    @Override
    public Move move(final Position position, final char cell) {
        while (true) {
            out.println("Position");
            out.println(position);
            out.println(cell + "'s move");
            out.println("Enter row and column");
            int row = nextInt(), col = nextInt();
            while (row <= 0 || col <= 0) {
                System.out.println("Invalid move, try again");
                row = nextInt();
                col = nextInt();
            }
            Move move = new Move(row - 1, col - 1, cell);
            if (position.isValid(move)) {
                return move;
            }
            out.println("Move " + move + " is invalid");
        }
    }
}
