/*思路为先构造一个长度固定的vector，从右向左迭代计算每一行*/
class Solution {
public:
    vector<int> getRow(int rowIndex) {
        assert(rowIndex >= 0);
        vector<int> result(rowIndex+1, 1);
        for (int i = 1; i <= rowIndex; ++i) {
            for (int j = i-1; j>0; --j) {
                result[j] = result[j] + result[j-1];
            }
        }
        return result;
    }
};