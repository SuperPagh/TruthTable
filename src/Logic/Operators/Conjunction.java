package Logic.Operators;

import Logic.Expression;
import Logic.LogicValue;

public class Conjunction extends Operator {

    public Conjunction(Expression left, Expression right) {
        super(left, right);
    }

    public LogicValue eval() {
        if(!containsOnlyLogicValue()) {
            return null;
        }
        if(left.toString().equals(LogicValue.TRUE)
                && right.toString().equals(LogicValue.TRUE)) {
            return LogicValue.TRUE;
        } else {
            return LogicValue.FALSE;
        }
    }
}
