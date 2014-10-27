/*It's basically a 双指针问题，先排序，确定第一个数字位置，另外两个位置使用两个指针进行逼近*/

class Solution {
public:
    int threeSumClosest(vector<int> &num, int target) {
        int clo = INT_MAX;
        int result;
        sort(num.begin(), num.end());
        for (int i=0; i < num.size() -2; ++i) {
            int j = i+1;
            int k = num.size() -1;
            while (j < k) {
                const int sum =num[i] + num[j] + num[k];
                const int gap = abs(sum - target);
                if (gap < clo) {
                    result = sum;
                    clo = gap;
                }
                if (sum < target) j++;
                else k--;
            }
        }
        return result;
    }
};