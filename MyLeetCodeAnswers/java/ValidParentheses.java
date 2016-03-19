import java.util.Stack;

/**
 * Source   : https://leetcode.com/problems/valid-parentheses/
 * Author   : Wang Meng
 * Date     : 2016-03-18
 * Given a string containing just the characters '(', ')', '{', '}', '[' and ']', determine if the input string is valid.
 * <p>
 * The brackets must close in the correct order, "()" and "()[]{}" are all valid but "(]" and "([)]" are not.
 */
public class ValidParentheses {
    public boolean isValid(String s) {
        if (s == null || s.isEmpty()) return true;
        Stack<Character> stack = new Stack<>();
        for (int i = 0; i < s.length(); ++i) {
            if (stack.empty() || !isPair(stack.peek(), s.charAt(i))) {
                stack.push(s.charAt(i));
            } else {
                stack.pop();
            }
        }
        return stack.empty();
    }

    private boolean isPair(char a, char b) {
        return (a == '(' && b == ')') || (a == '[' && b == ']') || (a == '{' && b == '}');
    }
}
