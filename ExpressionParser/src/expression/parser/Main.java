package expression.parser;

import expression.TripleExpression;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        ExpressionParser expressionParser = new ExpressionParser();
        TripleExpression expression = expressionParser.parse(scanner.nextLine());
        System.out.println(expression);
    }
}
