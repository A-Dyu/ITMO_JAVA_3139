package expression;

import java.util.Objects;

public class Variable implements Expression {
    private String var;

    public Variable(String var) {
        this.var = var;
    }

    @Override
    public int evaluate(int x) {
        return x;
    }

    @Override
    public String toString() {
        return var;
    }

    @Override
    public String toMiniString() {
        return this.toString();
    }

    @Override
    public boolean equals(Object object) {
        if (object == null || getClass() != object.getClass()) { return false; }
        Variable variable = (Variable) object;
        return Objects.equals(var, variable.var);
    }

    @Override
    public int hashCode() {
        return Objects.hash(var);
    }
}
