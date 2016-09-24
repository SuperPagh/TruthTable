package Logic.Operators;

import Logic.Expression;
import Logic.LogicValue;

public abstract class Operator {

    protected Expression left;
    protected Expression right;

    public Operator(Expression left, Expression right) {
        this.left = left;
        this.right = right;
    }

    protected boolean containsOnlyLogicValue() {
        if (left.splitExpression() != null) {
            return false;
        }
        if (right.splitExpression() != null) {
            return false;
        }
        return true;
    }

    public abstract LogicValue eval();

    public Expression getLeft() {
        return this.left;
    }

    public Expression getRight() {
        return this.right;
    }

}
