package Logic;

import Logic.Operators.*;

import java.util.*;
import java.util.stream.Stream;

public class Parser {

    private static final char[] legalVariables =
            {'a','b','c','d','e','f','g','h','i','j','k','l','m','n','o','p','q','r','s','t','u','v','w','x','y','z'};

    private Expression expression;
    private String expressionString;
    private Set<Character> variables;

    public Parser(String s) {

        this.expressionString = s;
        this.variables = findVariables(s);
        this.expression = new Expression(subsOperators(s));

    }

    private Set<Character> findVariables(String expression)
    {
        Set<Character> result = new HashSet<>();
        for(char guess : legalVariables)
        {
            int index = expression.indexOf(guess);
            if(index != -1)
            {
                result.add(expression.charAt(index));
            }
        }
        return result;
    }

    private String subsOperators(String expression) {
        String result = "";

        //If and only if (Biconditional)
        result = expression.replaceAll("<->", OperatorValue.BICONDITIONAL.getString());
        result = result.replaceAll("<>", OperatorValue.BICONDITIONAL.getString());
        result = result.replaceAll("=", OperatorValue.BICONDITIONAL.getString());

        //If (Implication)
        result = result.replaceAll("->", OperatorValue.IMPLICATION.getString());

        //Or (Disjunction). There is 2 backslashes, because it is not only a string escaper,
        //But also a regex escaper
        result = result.replaceAll("\\+", OperatorValue.DISJUNCTION.getString());
        result = result.replaceAll("||", OperatorValue.DISJUNCTION.getString());

        //And
        result = result.replaceAll("^", OperatorValue.CONJUNCTION.getString());
        result = result.replaceAll("&&", OperatorValue.CONJUNCTION.getString());
        result = result.replaceAll("&", OperatorValue.CONJUNCTION.getString());

        //Not (Negation)
        result = result.replaceAll("~", OperatorValue.NEGATION.getString());
        result = result.replaceAll("!", OperatorValue.NEGATION.getString());

        return result;
    }

    private String printTruthTable()
    {

        String result = "";

        Character[] variables = (Character[]) this.variables.toArray();
        int n = variables.length;


        for(int i = 0; i < n; i++)
        {
            result += variables[i] + "\t";
        }
        result += "\n";

        int rows = (int) Math.pow(2,n);

        for (int i = 0; i < rows; i++) {
            String currentExpression = this.expression.toString();
            for(int j = n-1; j >= 0; j--) {

                boolean isTrue = (((i / (int) Math.pow(2,j)) % 2) != 0);
                int trueValueOfInt = n-j-1; //(0, 1, 2, 3, .., n-1);

                if(isTrue) {
                    result += LogicValue.TRUE.getChar() + "\t";

                    currentExpression = currentExpression.replace(variables[trueValueOfInt], LogicValue.TRUE.getChar());
                } else {
                    result += LogicValue.FALSE.getChar() + "\t";

                    currentExpression = currentExpression.replace(variables[trueValueOfInt], LogicValue.FALSE.getChar());
                }
            }
            result += getTruthValue(currentExpression);
            result += "\n";
        }
        return result;
    }

    private String getTruthValue(String s) {
        Expression newExpression = new Expression(s);

        //If the length of the string == 1, it must be the truthvalue itself
        if (newExpression.toString().length() > 1) {

            //If the string is longer than 1 character,
            //it must contain at least 1 operator.
            Operator parsing = newExpression.splitExpression();

            while (parsing != null) {
                //TODO: I'm so not sure
            }

        } else {
            //TODO: Also not sure
        }
        return null;
    }
}