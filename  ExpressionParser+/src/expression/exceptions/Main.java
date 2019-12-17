package expression.exceptions;

import java.util.Scanner;

public class Main {
    private static Scanner scanner = new Scanner(System.in);
    private static int nextInt() {
        while (!scanner.hasNextInt()) {
            System.out.println("Invalid number! Please try again");
            scanner.nextLine();
        }
        return scanner.nextInt();
    }

    public static void main(String[] args) {
        ExpressionParser expressionParser = new ExpressionParser();
        TripleExpression expression;
        while (true) {
            try {
                expression = expressionParser.parse(scanner.nextLine());
                break;
            } catch (ParserException e) {
                System.out.println("Invalid expression!");
                System.out.println(e.getMessage());
                System.out.println("Please, try again");
            }
        }
        int x = nextInt(), y = nextInt(), z = nextInt();
        try {
            System.out.println(expression.evaluate(x, y, z));
        } catch (ExpressionException e) {
            System.out.println(e.getMessage());
        }
    }
}
