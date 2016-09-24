package Logic;

import Logic.Operators.*;

import java.util.*;

public class Expression {
    private String expression;
    private ArrayList<Character> variables = new ArrayList<Character>();

    public Expression(String expression) {
        //Make sure parentheses is balanced
        if(Parentheses.isBalanced(expression)) {
            //Get rid of whitespaces
            this.expression = expression.replaceAll("\\s+", "");
        } else {
            throw new IllegalArgumentException("Parentheses not balanced properly! Got String: '" + expression + "'");
        }
    }

    @Override
    public String toString() {
        return this.expression;
    }

    public Operator splitExpression() {
        Stack<Character> stack = new Stack<Character>();

        ArrayList<Integer> start = new ArrayList<Integer>();
        ArrayList<Integer> slut = new ArrayList<Integer>();
        Queue<String> temp = new LinkedList<String>();

        String s = this.expression.toString();

        //Iterate over each character in expression
        for (int i = 0; i < s.length(); i++) {

            //Find all parentheses
            if (s.charAt(i) == '(') {
                stack.push('(');
                start.add(i);
            } else if (s.charAt(i) == ')') {
                slut.add(i);
            }

        }

        //Enqueue all levels of parentheses
        if (start.size() != 0 && start.size() == slut.size()) {

            int parenthesesDepth = start.size();
            for(int i = 0; i < parenthesesDepth; i++) {
                String newStr = s.substring(start.get(i) + 1, slut.get(parenthesesDepth - i) - 1);
                temp.add(newStr);
            }

        }
        //In case no parentheses is found, we want the entire string
        //This is simply a wildcard, which is not found in the string
        temp.add("€");

        //Create a new string, with wildcard chars instead of insides of parentheses. From the outside in
        while(temp.peek() != null) {
            String current = temp.poll();
            String removedParentheses = "";

            char[] charArray = new char[current.length()];
            Arrays.fill(charArray, '€');

            //Replace everything inside parentheses with special chars.
            removedParentheses = s.replace(current, new String(charArray));

            //Go through all values in the order they are declared.
            //They are declared in order of precedence
            for(OperatorValue value : OperatorValue.values()) {

                int indexOf = removedParentheses.indexOf(value.getString());

                if(indexOf != -1) {
                    //An operator was found at this level of parentheses
                    //Split into 2 expressions
                    Expression left = new Expression(s.substring(0, indexOf));
                    Expression right = new Expression(s.substring(indexOf + 1));

                    //Return correct operator
                    return OperatorValue.getOperatorFromEnum(left, right, value);
                }
            }
        }
        return null;
    }
}
