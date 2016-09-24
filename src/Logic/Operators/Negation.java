package Logic.Operators;

import Logic.Expression;
import Logic.LogicValue;

public class Negation extends Operator {

    public Negation(Expression left, Expression right) {
        super(left, right);
    }

    public LogicValue eval() {
        if (!containsOnlyLogicValue()) {
            return null;
        }

        if (right.toString().equals(LogicValue.TRUE)) {
            return LogicValue.FALSE;
        } else {
            return LogicValue.TRUE;
        }
    }
}
