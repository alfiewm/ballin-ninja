/*
 * Given an array S of n integers, are there elements a, b, c, and d in S such that a + b + c + d = target? Find all unique quadruplets in the array which gives the sum of target.
 * 
 * Note:
 * Elements in a quadruplet (a,b,c,d) must be in non-descending order. (ie, a ≤ b ≤ c ≤ d)
 * The solution set must not contain duplicate quadruplets.
 *     For example, given array S = {1 0 -1 0 -2 2}, and target = 0.
 * 
 *     A solution set is:
 *     (-1,  0, 0, 1)
 *     (-2, -1, 1, 2)
 *     (-2,  0, 0, 2)
 */

/*So many edge conditions, good for practice*/
class Solution {
public:
    vector<vector<int> > fourSum(vector<int> &num, int target) {
        vector<vector<int> > result;
        sort(num.begin(), num.end());
        int count = num.size();
        for (int i = 0; i < count-3; ++i) {
            // -3 -3 -3 -3 0 1 2
            if (i >0 && num[i] == num[i-1]) continue;
            for (int j = i+1; j < count-2; ++j) {
                if (j>i+1 && num[j] == num[j-1]) continue;
                int rest = target - num[i] - num[j];
                int low = j+1;
                int high = count -1;
                while (low < high) {
                    if (num[low] + num[high] == rest) {
                        vector<int> v;
                        v.push_back(num[i]);
                        v.push_back(num[j]);
                        v.push_back(num[low]);
                        v.push_back(num[high]);
                        result.push_back(v);
                        // low-> -1 -1 0 0 1 1 <-high
                        while(low < high && num[low+1] == num[low]) ++low;
                        while(low < high && num[high-1] == num[high]) --high;
                        ++low;
                        --high;
                    } else if (num[low] + num[high] < rest) {
                        ++low;
                    } else {
                        --high;
                    }
                }
            }
        }
        return result;
    }
};