#include <iostream>
#include <vector>
using namespace std;

/*Standard solution*/
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
