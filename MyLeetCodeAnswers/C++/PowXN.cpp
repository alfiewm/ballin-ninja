// Source   : https://oj.leetcode.com/problems/powx-n/
// Authoer  : Wang Meng
// Date     : 2014-11-24

/*
 * Implement pow(x, n).
 */

// none recursive solution
class Solution {
public:
    double pow(double x, int n) {
        double res = 1;
        int tmp = n;
        while (n != 0) {
            if (n&1) res*=x;
            x*=x;
            n/=2;
        }
        return (tmp>=0) ? res : 1.0/res;
    }
};


// recursive Solution
class Solution {
public:
    double pow(double x, int n) {
        if (n == 0) return 1;
        double tmp = pow(x, n/2);
        // 注意不能写成n&1 == 0，因为&的优先级低于==，%>_<%
        if (!(n&1)) return tmp*tmp;
        else {
            if (n > 0) return x*tmp*tmp;
            else return tmp*tmp/x;
        }
    }
};