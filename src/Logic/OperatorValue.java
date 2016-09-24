package Logic;

import Logic.Operators.*;

public enum OperatorValue {

    //First declared means highest precedence
    //Last declared means lowest precedence

    NEGATION(0),
    CONJUNCTION(1),
    DISJUNCTION(2),
    IMPLICATION(3),
    BICONDITIONAL(4);

    private int value;

    private OperatorValue(int value) { this.value = value; }

    public static Operator getOperatorFromEnum(Expression left, Expression right, OperatorValue opValue) {
        switch (opValue) {
            case BICONDITIONAL:
                return new Biconditional(left, right);
            case CONJUNCTION:
                return new Conjunction(left, right);
            case IMPLICATION:
                return new Implication(left, right);
            case NEGATION:
                return new Negation(left, right);
            case DISJUNCTION:
                return new Disjunction(left, right);
            default:
                return null;
        }
    }

    public int getValue() {
        return value;
    }

    public char getChar() {
        return this.getString().charAt(0);
    }
    public String getString() {
        return Integer.toString(value);
    }
}
