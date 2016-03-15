import java.util.LinkedList;
import java.util.Queue;

/**
 * Source   : https://leetcode.com/problems/implement-stack-using-queues/
 * Author   : Wang Meng
 * Date     : 2016-03-14
 * Implement the following operations of a stack using queues.
 * <p>
 * push(x) -- Push element x onto stack.
 * pop() -- Removes the element on top of the stack.
 * top() -- Get the top element.
 * empty() -- Return whether the stack is empty.
 * Notes:
 * You must use only standard operations of a queue -- which means only push to back, peek/pop from front, size, and is empty operations are valid.
 * Depending on your language, queue may not be supported natively. You may simulate a queue by using a list or deque (double-ended queue), as long as you use only standard operations of a queue.
 * You may assume that all operations are valid (for example, no pop or top operations will be called on an empty stack).
 */
public class ImplementStackUsingQueue {

    public static void main(String[] args) {
        MyStack2 stack = new MyStack2();
        stack.push(0);
        System.out.println(stack.top());
        stack.push(1);
        System.out.println(stack.top());
        stack.pop();
        System.out.println(stack.top());
        stack.push(2);
        System.out.println(stack.top());
        stack.push(-1);
        System.out.println(stack.top());
    }

    private static class MyStack2 {
        Queue<Integer> queue = new LinkedList<>();
        // Push element x onto stack.
        public void push(int x) {
            queue.offer(x);
            for (int i = 0; i < queue.size() -1; ++i) {
                queue.offer(queue.poll());
            }
        }

        // Removes the element on top of the stack.
        public void pop() {
            queue.poll();
        }

        // Get the top element.
        public int top() {
            return queue.peek();
        }

        // Return whether the stack is empty.
        public boolean empty() {
            return queue.isEmpty();
        }
    }

    private static class MyStack {
        Queue<Integer> inQueue = new LinkedList<>();
        Queue<Integer> outQueue = new LinkedList<>();

        // Push element x onto stack.
        public void push(int x) {
            inQueue.offer(x);
        }

        // Removes the element on top of the stack.
        public void pop() {
            if (!inQueue.isEmpty()) {
                while (inQueue.size() > 1) {
                    outQueue.offer(inQueue.poll());
                }
                inQueue.poll();
            } else {
                while (outQueue.size() > 1) {
                    inQueue.offer(outQueue.poll());
                }
                outQueue.poll();
            }
        }

        // Get the top element.
        public int top() {
            if (!inQueue.isEmpty()) {
                while (inQueue.size() > 1) {
                    outQueue.offer(inQueue.poll());
                }
                int result = inQueue.peek();
                outQueue.offer(inQueue.poll());
                return result;
            } else {
                while (outQueue.size() > 1) {
                    inQueue.offer(outQueue.poll());
                }
                int result = outQueue.peek();
                inQueue.offer(outQueue.poll());
                return result;
            }
        }

        // Return whether the stack is empty.
        public boolean empty() {
            return inQueue.isEmpty() && outQueue.isEmpty();
        }
    }
}
