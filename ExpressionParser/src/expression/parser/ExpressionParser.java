package expression.parser;

import expression.*;

import java.util.Map;

public class ExpressionParser extends BaseParser implements Parser {
    private String lastOperator = ")";
    private final int topLevel = 2;
    private final int primalLevel = 0;
    private static Map<String, Integer> priorities = Map.of(
            "+", 2,
            "-", 2,
            "*", 1,
            "/", 1,
            ")", 10
    );
    private static Map<Character, String> firstCharToOperator = Map.of(
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
        if (level == primalLevel) {
            return getPrimalExpression();
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

    private CommonExpression getPrimalExpression() {
        if (test('-')) {
            if (between('0', '9')) {
                return getConstExpression(true);
            } else {
                return NegativeExpression.getNegativeExpression(parseLevel(0));
            }
        }
        if (test('(')) {
            return parseLevel(topLevel);
        }
        if (between('0', '9')) {
            return getConstExpression(false);
        }
        return getVariableExpression();
    }


    private CommonExpression getVariableExpression() {
        StringBuilder stringBuilder = new StringBuilder();
        while (!testOperator()) {
            stringBuilder.append(ch);
            nextChar();
        }
        return new Variable(stringBuilder.toString());
    }

    private CommonExpression getConstExpression(boolean isNegative) {
        StringBuilder stringBuilder = new StringBuilder(isNegative ? "-" : "");
        while (between('0', '9')) {
            stringBuilder.append(ch);
            nextChar();
        }
        testOperator();
        return new Const(Integer.parseInt(stringBuilder.toString()));
    }

    private boolean testOperator() {
        if (!firstCharToOperator.containsKey(ch)) {
            return false;
        }
        getOperator();
        return true;
    }

    private void getOperator() {
        String operator = firstCharToOperator.get(ch);
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
