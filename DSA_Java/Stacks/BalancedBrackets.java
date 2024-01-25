package Stacks;
import java.util.ArrayDeque;
import java.util.Deque;
// is this expression balanced? (a{b+c[d-e]+f}) * For each type of bracket, its matched with another in Last in First out Sequence.
// Push the brackets onto a stack, until you reach a closing bracket, then check for the last in first out sequence
import java.util.Stack;

public class BalancedBrackets {
	
	public static boolean isBalanced(String expression) {
        Stack<Character> stack = new Stack<>();

        for (char ch : expression.toCharArray()) {
            if (ch == '(') {
                stack.push(ch);  // Push opening bracket to the stack
            } else if (ch == ')') {
                if (stack.isEmpty() || stack.peek() != '(') {  // No corresponding opening bracket
                    return false;
                }
                stack.pop();  // Pop the corresponding opening bracket from the stack
            }
        }

        return stack.isEmpty();  // Check if all opening brackets are closed
    }
	
	
	///////////////////////////////////////////////
//	Redundant brackets
	public static boolean checkRedundantBrackets(String expression) {
		//Your code goes here
		Deque<Character> stack = new ArrayDeque<>();
        for (char ch : expression.toCharArray()) {
            if (ch != ')') {
                stack.push(ch);
            } else {
                if (stack.peek() == '(') {
                    return true; // Found redundant brackets
                } else {
                    // Discard all characters until the matching opening bracket
                    while (!stack.isEmpty() && stack.peek() != '(') {
                        stack.pop();
                    }
                    // Discard the opening bracket
                    if (!stack.isEmpty()) {
                        stack.pop();
                    }
                }
            }
        }
        return false; // No redundant brackets found
	}
	/////////////////////////////////////////////////
//	Bracket reversal 
	
//	For a given expression in the form of a string, find the minimum number of brackets that can be reversed 
//	in order to make the expression balanced. The expression will only contain curly brackets.
//	If the expression can't be balanced, return -1.
	public static int countBracketReversals(String input) {
		//Your code goes here
		if (input.length() % 2 != 0) {
            // If number of brackets is not even then we can't make pairs
            return -1;
        }

        Deque<Character> stack = new ArrayDeque<>();
        for (char ch : input.toCharArray()) {
            if (ch == '{') {
                stack.push(ch);
            } else { // ch == '}'
                if (!stack.isEmpty() && stack.peek() == '{') {
                    stack.pop();
                } else {
                    stack.push(ch);
                }
            }
        }

        int count = 0;
        while (!stack.isEmpty()) {
            char c1 = stack.pop();
            char c2 = stack.pop();
            // If both are same i.e., {{ or }}, then we need to reverse one bracket
            // If not same i.e., {} or }{, then we need to reverse both brackets
            if (c1 == c2) {
                count++;
            } else {
                count += 2;
            }
        }

        return count;
	}

	
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		 	String expression = "(())()";  // Example expression
		 	
	        boolean balanced = isBalanced(expression);
	        
	        System.out.println(balanced);
	}

}
