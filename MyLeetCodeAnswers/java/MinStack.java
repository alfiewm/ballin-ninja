import java.util.Stack;

/**
 * Source   : https://leetcode.com/problems/min-stack/
 * Author   : Wang Meng
 * Date     : 2016-03-14
 * Design a stack that supports push, pop, top, and retrieving the minimum element in constant time.
 * <p>
 * push(x) -- Push element x onto stack.
 * pop() -- Removes the element on top of the stack.
 * top() -- Get the top element.
 * getMin() -- Retrieve the minimum element in the stack.
 */
public class MinStack {
    private Stack<Integer> data = new Stack<>();
    private Stack<Integer> mins = new Stack<>();

    public void push(int x) {
        data.push(x);
        mins.push(mins.empty() ? x : Math.min(mins.peek(), x));
    }

    public void pop() {
        data.pop();
        mins.pop();
    }

    public int top() {
        return data.peek();
    }

    public int getMin() {
        return mins.peek();
    }

    public static void main(String[] args) {
        MinStack minStack = new MinStack();
        minStack.push(1);
        System.out.println(minStack.getMin());
        minStack.push(5);
        minStack.pop();
        minStack.pop();
        System.out.println(minStack.getMin());
        minStack.push(-2);
        System.out.println(minStack.getMin());
        minStack.push(3);
        System.out.println(minStack.getMin());
    }
}
