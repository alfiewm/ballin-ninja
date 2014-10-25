#include <iostream>
#include <vector>
#include <cmath>
using namespace std;
/*Gray Code 是一种编码，将自然排序的数字右移与原数字异或即为对应的编码*/
class Solution {
public:
    vector<int> grayCode(int n) {
        vector<int> result;
        int count = 1 << n;
        result.reserve(count);
        for (int i = 0; i < count; ++i) {
            result.push_back(binaryToGray(i));
        }
        return result;
    }

    int binaryToGray(int num) {
        return num^(num>>1);
    }
};
/*g观察n=1,n=2,n=3,发现后面的是在前一个来个倒影，然后最高位加1或者0，太有才了*/
class Solution2 {
public:
    vector<int> grayCode(int n) {
        vector<int> result;
        result.push_back(0);

        for (int i = 0; i < n; ++i) {
            const int highestBit = 1 << i;
            for (int j = result.size() -1; j >= 0; --j) {
                result.push_back(result[j] | highestBit);
            }
        }
        return result;
    }
};
int main() {
    Solution s;
    int n;
    while (cin >> n) {
        vector<int> result = s.grayCode(n);
        for (int i = 0; i < result.size(); ++i) {
            cout << result[i] << " ";
        }
    }
    return 0;
}
