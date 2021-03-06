#include <iostream>
#include <vector>
using namespace std;

/*Standard solution
f(k)代表以A[k]结尾的连续子数组最大乘积
g(k)代表以A[k]结尾的连续子数组最小乘积，之所以保留g(k)是因为存在当g(k-1)负数时，A[k]也可能是负数，乘积反而较大
f(k) = max(f(k-1)*A[k], A[k], g(k-1)*A[k]);
g(k) = min(g(k-1)*A[k], A[k], f(k-1)*A[k]);*/
class Solution2 {
public:
    int maxProduct(int A[], int n) {
        assert(n > 0);
        int cmax = A[0], cmin = A[0], maxAns = A[0];
        for (int i = 1; i < n; ++i) {
            int mx = cmax, mn = cmin;
            cmax = max(max(A[i], mx*A[i]), mn*A[i]);
            cmin = min(min(A[i], mx*A[i]), mn*A[i]);
            maxAns = max(cmax, maxAns);
        }
        return maxAns;
    }
};

/*Discuss area 对我的解法的一个优化，基于三点考虑
一、如果没有0，且都为正数，则最大值就是所有数的乘积。
二、若有0，则分割计算
三、分割后，每段若有奇数个负数，则从左至右累乘至第一个负数，从右至左累乘到第一个负数，不包括第一个负数，得到两个值
从左至右累乘至最后一个负数，从右至左至最后一个负数，又得到两个，则max必然在这四个数中
归纳起来，从右至左，从左至右遍历，必然会遇到这个最大值*/
class Solution1p5 {
public:
    int maxProduct(int A[], int n) {
        if (A == NULL || n == 0) return 0;
        int maxp = INT_MIN;
        int product = 1;
        for (int i=0; i < n; ++i) {
            product*=A[i];
            if (product > maxp)
                maxp = product;
            if (product == 0)
                product =1;
        }

        product = 1;
        for (int i = n-1; i >=0; --i) {
            product*=A[i];
            if (product > maxp)
                maxp = product;
            if (product == 0)
                product = 1;
        }
        return maxp;
    }
};

/*My ugly solution, but works*/
class Solution {
public:
    int maxProduct(int A[], int n) {
        if (n<1) return 0;
        if (n==1) return A[0];
        int pre0 = -1;
        int maxp = INT_MIN;
        for (int i=0; i<n; ++i) {
            if (A[i] == 0) {
                int m = maxProduct(A+pre0+1, i-pre0-1);
                maxp = max(maxp, m);
                pre0 = i;
            }
        }
        if (pre0 != -1) {
            if (pre0 != n-1) {
                int m = maxProduct(A+pre0+1, n-pre0-1);
                maxp = max(maxp, m);
            }
            return max(0, maxp);
        }

        // none zero
        vector<int> mp;//minus number positions
        for (int i = 0; i < n; ++i) {
            if (A[i] < 0) mp.push_back(i);
        }
        if (mp.size() %2 == 0) {
            // even negative numbers
            maxp = 1;
            for (int i = 0; i < n; ++i) {
                maxp*=A[i];
            }
        } else {
            // odd negative numbers
            int left1 = maxProduct(A, mp[0]);
            int right1 = maxProduct(A+mp[0]+1, n-mp[0]-1);
            cout << "1~~" << left1 << "   " << right1 << endl;
            maxp = max(left1, maxp);
            maxp = max(right1, maxp);
            int left2 = maxProduct(A, mp[mp.size()-1]);
            int right2 = maxProduct(A+mp[mp.size()-1]+1, n-mp[mp.size()-1]-1);
            cout << "2~~" << left2 << "   " << right2 << endl;
            maxp = max(left2, maxp);
            maxp = max(right2, maxp);
        }
        return maxp;
    }
};

const int N = 4;

int main() {
    int input[N] = {1, 0, 0, 0};
    Solution s;
    cout << s.maxProduct(input, N) << endl;
    return 0;
}
