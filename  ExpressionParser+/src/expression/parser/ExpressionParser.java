package expression.parser;

import expression.*;

import java.util.Map;

public class ExpressionParser extends BaseParser implements Parser {
    private String lastOperator;
    private static final int topLevel = 3;
    private static final int primeLevel = 0;
    private static final Map<String, Integer> priorities = Map.of(
            "+", 2,
            "-", 2,
            "*", 1,
            "/", 1,
            "<<", 3,
            ">>", 3,
            ")", topLevel + 1
    );
    private static final Map<Character, String> firstCharToOperator = Map.of(
            '+', "+",
            '-', "-",
            '*', "*",
            '/', "/",
            ')', ")",
            '<', "<<",
            '>', ">>"
    );

    @Override
    public TripleExpression parse(String expression) {
        setSource(new StringSource(format(expression)));
        nextChar();
        final TripleExpression tripleExpression = parseLevel(topLevel);
        if (hasNextChar() || lastOperator != null) {
            throw error("Unexpected close bracket");
        }
        return tripleExpression;
    }

    private CommonExpression parseLevel(int level) {
        if (level == primeLevel) {
            return getPrimeExpression();
        }
        CommonExpression expression = parseLevel(level - 1);
        while (lastOperator != null && priorities.get(lastOperator) == level) {
            expression = makeExpression(lastOperator, expression, parseLevel(level - 1));
        }
        if (level == topLevel) {
            if (lastOperator == null) {
                throw error("Expected close bracket");
            }
            if (!testOperator() && lastOperator.equals(")")) {
                lastOperator = null;
            }
        }
        return expression;
    }

    private CommonExpression getPrimeExpression() {
        if (test('-')) {
            if (between('0', '9')) {
                return getConstExpression(true);
            } else {
                return new UnaryMinus(parseLevel(0));
            }
        } else if (testOperator()) {
            throw error("Unexpected operator");
        } else if (test('(')) {
            return parseLevel(topLevel);
        } else if (between('0', '9')) {
            return getConstExpression(false);
        } else {
            return getVariableExpression();
        }
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
        while (!testOperator()) {
            stringBuilder.append(ch);
            nextChar();
        }
        try {
            return new Const(Integer.parseInt(stringBuilder.toString()));
        } catch (NumberFormatException e) {
            throw error("Illegal number:" + stringBuilder.toString());
        }
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
        if (operator.equals("<<")) {
            return new LeftShift(a, b);
        }
        if (operator.equals(">>")) {
            return new RightShift(a, b);
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
        return stringBuilder.toString() + ')';
    }
}
