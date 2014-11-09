// Source   : https://oj.leetcode.com/problems/divide-two-integers/
// Authoer  : Wang Meng
// Date     : 2014-11-09

/*
 * Divide two integers without using multiplication, division and mod operator.
 */

// solution from discuss area.
// 基本思路是利用位移，将被除数翻倍尽量大，一次性减尽量多的值。外层循环每次迭代至少减少一半的值，所以时间复杂度为O(logn)，内层循环类似，最多有Log N次迭代，故整体的复杂度为O((logN)^2); 注意必须用long long，因为在32位编译器上，int和long都是4字节。。。64位编译器上long和long long都是64字节
class Solution {
public:
    int divide(int dividend, int divisor) {
        assert(divisor != 0);
        long long ans = 0;
        long long n = abs((long long)dividend);
        long long d = abs((long long)divisor);
        while (n >= d) {
            long long a = d;
            long long m = 1;
            // n/d = x; a = md; (n-a)/d = x-m;
            while ((a<<1) < n) { a<<=1; m<<=1;}
            ans += m;
            n -= a;
        }
        if ((dividend>0 && divisor<0) || (dividend<0 && divisor>0)) {
            return -ans;
        }
        return ans;
    }
};

// my solution, TLE for 2147483647, 1
class Solution {
public:
    int divide(int dividend, int divisor) {
        assert(divisor != 0);
        if (dividend == 0) return 0;
        int r = -1;
        bool flag = false;
        if ((dividend<0 && divisor>0) || (dividend>0 && divisor<0)) {
            dividend = abs(dividend);
            divisor = abs(divisor);
            flag = true;
        }
        while (dividend >= 0) {
            dividend -= divisor;
            ++r;
        }
        return flag ? -r : r;
    }
};