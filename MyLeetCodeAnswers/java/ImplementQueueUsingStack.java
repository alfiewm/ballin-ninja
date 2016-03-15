import java.util.Stack;

/**
 * Source   : https://leetcode.com/problems/implement-queue-using-stacks/
 * Author   : Wang Meng
 * Date     : 2016-03-15
 * Implement the following operations of a queue using stacks.
 * <p>
 * push(x) -- Push element x to the back of queue.
 * pop() -- Removes the element from in front of queue.
 * peek() -- Get the front element.
 * empty() -- Return whether the queue is empty.
 * Notes:
 * You must use only standard operations of a stack -- which means only push to top, peek/pop from top, size, and is empty operations are valid.
 * Depending on your language, stack may not be supported natively. You may simulate a stack by using a list or deque (double-ended queue), as long as you use only standard operations of a stack.
 * You may assume that all operations are valid (for example, no pop or peek operations will be called on an empty queue).
 */
public class ImplementQueueUsingStack {
    public static void main(String[] args) {
        // no need to...
    }

    private static class MyQueue {
        private Stack<Integer> inStack = new Stack<>();
        private Stack<Integer> outStack = new Stack<>();

        // Push element x to the back of queue.
        public void push(int x) {
            inStack.push(x);
        }

        // Removes the element from in front of queue.
        public void pop() {
            if (outStack.empty()) {
                while (!inStack.empty()) {
                    outStack.push(inStack.pop());
                }
            }
            outStack.pop();
        }

        // Get the front element.
        public int peek() {
            if (outStack.empty()) {
                while (!inStack.empty()) {
                    outStack.push(inStack.pop());
                }
            }
            return outStack.peek();
        }

        // Return whether the queue is empty.
        public boolean empty() {
            return inStack.empty() && outStack.empty();
        }
    }
}
