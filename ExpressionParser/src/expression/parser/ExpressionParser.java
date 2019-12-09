package expression.parser;

import expression.*;

import java.util.InputMismatchException;
import java.util.Map;
import java.util.UnknownFormatConversionException;

public class ExpressionParser extends BaseParser implements Parser {
    private String lastOperator = ")";
    private final int topLevel = 2;
    private static Map<String, Integer> priorities = Map.of(
            "+", 2,
            "-", 2,
            "*", 1,
            "/", 1,
            ")", 10
    );
    private static Map<Character, String> charToString = Map.of(
            '+', "+",
            '-', "-",
            '*', "*",
            '/', "/",
            ')', ")"
    );

    @Override
    public TripleExpression parse(String expression) {
        setSource(new StringSource(format(expression) + ')'));
        nextChar();
        return parseLevel(topLevel);
    }

    private CommonExpression parseLevel(int level) {
        if (level == 0) {
            if (test('-')) {
                if (between('0', '9')) {
                    StringBuilder stringBuilder = new StringBuilder("-");
                    while (between('0', '9')) {
                        stringBuilder.append(ch);
                        nextChar();
                    }
                    testOperator();
                    return new Const(Integer.parseInt(stringBuilder.toString()));
                }
                return NegativeExpression.getNegativeExpression(parseLevel(0));
            }
            if (test('(')) {
                return parseLevel(topLevel);
            } else {
                StringBuilder stringBuilder = new StringBuilder();
                while (!testOperator()) {
                    stringBuilder.append(ch);
                    nextChar();
                }
                try {
                    int x = Integer.parseInt(stringBuilder.toString());
                    return new Const(x);
                } catch (NumberFormatException e) {
                    return new Variable(stringBuilder.toString());
                }
            }
        }
        CommonExpression expression = parseLevel(level - 1);
        while (priorities.get(lastOperator) == level) {
            expression = makeExpression(lastOperator, expression, parseLevel(level - 1));
        }
        if (level == topLevel) {
            testOperator();
        }
        return expression;
    }

    private boolean testOperator() {
        if (!charToString.containsKey(ch)) {
            return false;
        }
        getOperator();
        return true;
    }

    private void getOperator() {
        String operator = charToString.get(ch);
        expect(operator);
        lastOperator = operator;
    }

    private CommonExpression makeExpression(String operator, CommonExpression a, CommonExpression b) {
        if (operator.equals("+")) {
            return new Add(a, b);
        }
        if (operator.equals("-")) {
            return new Subtract(a, b);
        }
        if (operator.equals("*")) {
            return new Multiply(a, b);
        }
        if (operator.equals("/")) {
            return new Divide(a, b);
        }
        throw error("Unsupported operator: " + operator);
    }

    private String format(String string) {
        StringBuilder stringBuilder = new StringBuilder();
        for (int i = 0; i < string.length(); i++) {
            if (!Character.isWhitespace(string.charAt(i))) {
                stringBuilder.append(string.charAt(i));
            }
        }
        return stringBuilder.toString();
    }
}
