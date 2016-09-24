package Logic.Operators;

import Logic.Expression;
import Logic.LogicValue;

public class Biconditional extends Operator {

    public Biconditional(Expression left, Expression right) {
        super(left, right);
    }

    public LogicValue eval() {
        if(!containsOnlyLogicValue()) {
            return null;
        }
        if(left.toString().equals(right.toString())) {
            return LogicValue.TRUE;
        } else {
            return LogicValue.FALSE;
        }
    }
}
