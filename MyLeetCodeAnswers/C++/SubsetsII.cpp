// Source   : https://oj.leetcode.com/problems/subsets-ii/
// Authoer  : Wang Meng
// Date     : 2014-11-22

/*
 * Given a collection of integers that might contain duplicates, S, return all possible subsets.
 * 
 * Note:
 * Elements in a subset must be in non-descending order.
 * The solution set must not contain duplicate subsets.
 * For example,
 * If S = [1,2,2], a solution is:
 * 
 * [
 *   [2],
 *   [1],
 *   [1,2,2],
 *   [2,2],
 *   [1,2],
 *   []
 * ]
 */

// DP solution, 多画，多观察，还是能够发现规律的
class Solution {
public:
    vector<vector<int> > subsetsWithDup(vector<int> &S) {
        vector<vector<int> > res;
        sort(S.begin(), S.end());
        res.push_back(vector<int>());
        int size(0);
        for (int i=0; i<S.size(); ++i) {
        	// 如果是从夫数字的话，从上次解雇偶的size处开始增加新子集
            int startIndex = (i>0 && S[i]==S[i-1]) ? size : 0;
            size = res.size();
            for (int j=startIndex; j<size; ++j) {
                res.push_back(res[j]);
                res[j+size-startIndex].push_back(S[i]);
            }
        }
        return res;
    }
};