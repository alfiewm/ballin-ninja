// Source   : https://oj.leetcode.com/problems/reverse-linked-list-ii/
// Authoer  : Wang Meng
// Date     : 2014-11-08

/*
 * Implement int sqrt(int x).
 * 
 * Compute and return the square root of x.
 */

#include "Solution.h"


// 从Dicuss找来的方法，天才啊，考虑到INT最多有32位，开平方最多是15位，依次将15到1位置1，如果超了，则reset, 同时，使用unsigned long避免越界
class Solution {
public:
    int sqrt(int x) {
        unsigned long ans = 0;
        unsigned long bit = 1l << 15;
        while (bit > 0) {
            ans |= bit;
            if (ans*ans > x) ans ^= bit;
            bit >>= 1;
        }
        return (int)ans;
    }
};

// my solution, 没有用乘法，但是TLE了。。。
class Solution {
public:
    int sqrt(int x) {
        assert(x>=0);
        int r = 0;
        int H = 0;
        int pre = H;
        while (H != x) {
            if (H < x) {
                // 注意栈溢出问题
                if (INT_MAX - H < r+1+r) {
                    return r;
                }
                pre = H;
                H = H+r+1+r;
                r++;
            } else {
                if (abs(H-x) >= abs(pre-x)) {
                    return r-1;
                } else {
                    return r;
                }
            }
        }
        return r;
    }
};

int main() {
    int x;
    Solution s;
    while (cin >> x) {
        cout << s.sqrt(x) << endl;
    }
    return 0;
}