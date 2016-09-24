package Logic;

import java.util.Stack;

public class Parentheses {
	public static boolean isBalanced(String s)
    {
        Stack<Character> stack = new Stack<Character>();
        
        for (int i = 0; i < s.length(); i++)
        {

            if      (s.charAt(i) == '(')
            {
            	stack.push('(');
            }
            else if (s.charAt(i) == ')') 
            {
                if (stack.isEmpty())    
                {
                	return false;
                }
                if (stack.pop() != '(')
                {
                	return false;
                }
            }
            // ignore all other characters

        }
        return stack.isEmpty();
    }
}
