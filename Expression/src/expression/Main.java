package expression;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        Expression expression = new Add(new Subtract(new Multiply(new Variable("x"), new Variable("x")),
                new Multiply(new Const(2), new Variable("x"))),
                    new Const(1));
        System.out.println(expression.evaluate(scanner.nextInt()));
        //System.out.println(new Add(new Const(2), new Variable("x")).equals(new Subtract(new Const(2), new Variable("x"))));
    }
}
