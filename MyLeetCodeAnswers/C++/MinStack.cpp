// Source   : https://oj.leetcode.com/problems/min-stack/
// Authoer  : Wang Meng
// Date     : 2014-11-10

/*
 * Design a stack that supports push, pop, top, and retrieving the minimum element in constant time.
 * 
 * push(x) -- Push element x onto stack.
 * pop() -- Removes the element on top of the stack.
 * top() -- Get the top element.
 * getMin() -- Retrieve the minimum element in the stack.
 */

// My third Solution, Accepted finally
class MinStack {
public:
    void push(int x) {
        valSta.push(x);
        if (minSta.empty() || x <= minSta.top()) {
            minSta.push(x);
        }
    }

    void pop() {
        assert (!valSta.empty());
        if (valSta.top() == getMin()) {
            minSta.pop();
        }
        valSta.pop();
    }

    int top() {
        assert (!valSta.empty());
        return valSta.top();
    }

    int getMin() {
        assert (!minSta.empty());
        return minSta.top();
    }
private:
    stack<int> valSta;
    stack<int> minSta;
};
// My second Solution, MLE too...
class MinStack {
public:
    void push(int x) {
        int mini;
        if (sta.empty()) {
            mini = x;
        } else {
            mini = sta.top() > x ? x : sta.top();
        }
        sta.push(mini);
        sta.push(x);
    }

    void pop() {
        assert (!sta.empty());
        sta.pop();
        sta.pop();
    }

    int top() {
        assert (!sta.empty());
        return sta.top();
    }

    int getMin() {
        assert (!sta.empty());
        int top = sta.top();
        sta.pop();
        int mini = sta.top();
        sta.push(top);
        return mini;
    }
private:
    stack<int> sta;
};
// My first Solution, MLE
class MinStack {
public:
    void push(int x) {
        valSta.push(x);
        if (minSta.empty()) {
            minSta.push(x);
        } else {
            minSta.push((x<minSta.top()) ? x : minSta.top());
        }
    }

    void pop() {
        assert (!valSta.empty());
        valSta.pop();
        minSta.pop();
    }

    int top() {
        assert (!valSta.empty());
        return valSta.top();
    }

    int getMin() {
        assert (!minSta.empty());
        return minSta.top();
    }
private:
    stack<int> valSta;
    stack<int> minSta;
};