#include <iostream>
#include <vector>

using namespace std;

class Solution {
public:
    vector<vector<int> > threeSum(vector<int> &num) {
        vector<vector<int>> result;
        if (num.size() <= 2) return result;
        sort(num.begin(), num.end());
        for (int i = 0; i < num.size() - 2; ++i) {
            if (i>0 && num[i] == num[i-1]) continue;
            int rest = - num[i];
            int left = i+1, right = num.size() -1;
            while (left < right) {
                if (num[left] + num[right] == rest) {
                    vector<int> gotOne;
                    gotOne.push_back(num[i]);
                    int leftNum = num[left];
                    int rightNum = num[right];
                    gotOne.push_back(num[left]);
                    gotOne.push_back(num[right]);
                    result.push_back(gotOne);
                    //skip duplicate
                    while (left<right && num[left] == leftNum) left++;
                    while (left<right && num[right] == rightNum) right--;
                } else if (num[left] + num[right] < rest) {
                    left++;
                } else {
                    right--;
                }
            }
        }
        return result;
    }
};

int main() {
    Solution s;
    int i[6] = {-1, 0, 1, 2, -1, -4};
    vector<int> input(i, i+5);
    vector<vector<int> > result = s.threeSum(input);
    for (int i=0; i<result.size(); ++i) {
        for (int j=0; j<result[i].size(); ++j) {
            cout << result[i][j] << " ";
        }
        cout << endl;
    }
    return 0;
}
