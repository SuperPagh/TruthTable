package Logic.Operators;

import Logic.Expression;
import Logic.LogicValue;

public class Implication extends Operator {

    public Implication(Expression left, Expression right) {
        super(left, right);
    }

    public LogicValue eval() {
        if (!containsOnlyLogicValue()) {
            return null;
        }

        if (left.toString().equals(LogicValue.TRUE)) {
            if(right.toString().equals(LogicValue.TRUE)) {
                return LogicValue.TRUE;
            }
            return LogicValue.FALSE;
        } else {
            return LogicValue.TRUE;
        }
    }
}