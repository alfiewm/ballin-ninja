// Source   : https://oj.leetcode.com/problems/subsets/
// Authoer  : Wang Meng
// Date     : 2014-11-12

/*
 * Given a set of distinct integers, S, return all possible subsets.
 * 
 * Note:
 * Elements in a subset must be in non-descending order.
 * The solution set must not contain duplicate subsets.
 * For example,
 * If S = [1,2,3], a solution is:
 * 
 * [
 *   [3],
 *   [1],
 *   [2],
 *   [1,2,3],
 *   [1,3],
 *   [2,3],
 *   [1,2],
 *   []
 * ]
 */

// idea from discuss area, 实现思路：最终结果的个数为2的S.size()次方，对于编号为x的结果，将x看成2进制数字，从低位到高位遍历j，如果是1就将对应位置的S[j]加入到result[x]中
class Solution {
public:
    vector<vector<int> > subsets(vector<int> &S) {
        sort(S.begin(), S.end());
        int eleNum = S.size();
        int resNum = (1<<eleNum);
        vector<vector<int> > result(resNum, vector<int>());
        for (int i=0; i<resNum; ++i) {
            for (int j=0; j<eleNum; ++j) {
                if ((i>>j)&1 == 1) {
                    result[i].push_back(S[j]);
                }
            }
        }
        return result;
    }
};