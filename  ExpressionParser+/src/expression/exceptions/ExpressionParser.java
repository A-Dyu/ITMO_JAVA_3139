package expression.exceptions;

import java.util.Map;

public class ExpressionParser extends BaseParser implements Parser {
    private String lastOperator;
    private static final int topLevel = 2;
    private static final int primeLevel = 0;
    private static final Map<String, Integer> priorities = Map.of(
            "+", 2,
            "-", 2,
            "*", 1,
            "/", 1,
            ")", topLevel + 1
    );
    private static final Map<Character, String> firstCharToOperator = Map.of(
            '+', "+",
            '-', "-",
            '*', "*",
            '/', "/",
            ')', ")"
    );

    @Override
    public TripleExpression parse(String expression) {
        setSource(new StringSource(expression + ")"));
        nextChar();
        skipWhitespaces();
        final TripleExpression tripleExpression = parseLevel(topLevel);
        if (ch != '\0') {
            throw error("Unexpected close bracket");
        }
        return tripleExpression;
    }

    private CommonExpression parseLevel(int level) {
        if (level == primeLevel) {
            CommonExpression primeExpression = getPrimeExpression();
            skipWhitespaces();
            if (!testOperator()) {
                throw error("Expected operator");
            }
            return primeExpression;
        }
        CommonExpression expression = parseLevel(level - 1);
        while (lastOperator != null && priorities.get(lastOperator) == level) {
            expression = makeExpression(lastOperator, expression, parseLevel(level - 1));
        }
        if (level == topLevel) {
            if (lastOperator == null || !lastOperator.equals(")")) {
                throw error("Expected close bracket");
            }
            lastOperator = null;
        }
        return expression;
    }

    private CommonExpression getPrimeExpression() {
        if (test('(')) {
            return parseLevel(topLevel);
        } else if (test('-')) {
            skipWhitespaces();
            if (between('0', '9')) {
                return getConstExpression(true);
            } else {
                return new CheckedNegate(getPrimeExpression());
            }
        } else if (testOperator()) {
            throw error("Unexpected operator");
        } else if (between('0', '9')) {
            return getConstExpression(false);
        } else {
            return getVariableExpression();
        }
    }


    private CommonExpression getVariableExpression() {
        StringBuilder stringBuilder = new StringBuilder();
        while (between('x', 'z') || between('0', '9')) {
            stringBuilder.append(ch);
            nextChar();
        }
        if (stringBuilder.length() == 0) {
            throw error("Unsupported variable " + ch);
        }
        return new Variable(stringBuilder.toString());
    }

    private CommonExpression getConstExpression(boolean isNegative) {
        StringBuilder stringBuilder = new StringBuilder(isNegative ? "-" : "");
        while (between('0', '9')) {
            stringBuilder.append(ch);
            nextChar();
        }
        return new Const(Integer.parseInt(stringBuilder.toString()));
    }

    private boolean testOperator() {
        if (!firstCharToOperator.containsKey(ch)) {
            return false;
        }
        getOperator();
        skipWhitespaces();
        return true;
    }

    private void getOperator() {
        String operator = firstCharToOperator.get(ch);
        expect(operator);
        lastOperator = operator;
    }

    private CommonExpression makeExpression(String operator, CommonExpression a, CommonExpression b) {
        if (operator.equals("+")) {
            return new CheckedAdd(a, b);
        }
        if (operator.equals("-")) {
            return new CheckedSubtract(a, b);
        }
        if (operator.equals("*")) {
            return new CheckedMultiply(a, b);
        }
        if (operator.equals("/")) {
            return new CheckedDivide(a, b);
        }
        throw error("Unsupported operator: " + operator);
    }

    private void skipWhitespaces() {
        while (Character.isWhitespace(ch)) {
            nextChar();
        }
    }
}
