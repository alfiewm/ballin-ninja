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

// recursive solution，每个元素都可以选择在不在一个子集中
class Solution {
public:
    vector<vector<int> > subsets(vector<int> &S) {
        sort(S.begin(), S.end());
        vector<vector<int> > res;
        vector<int> cur;
        subsetsR(cur, res, S, 0);
        return res;
    }
    
    void subsetsR(vector<int> &cur, vector<vector<int> > &res, vector<int> &S, int index) {
        if (index == S.size()) res.push_back(cur);
        else {
            vector<int> noIndexElement = cur;
            vector<int> hasIndexElement = cur; hasIndexElement.push_back(S[index]);
            subsetsR(noIndexElement, res, S, index+1);
            subsetsR(hasIndexElement, res, S, index+1);
        }
    }
};

// DP solution from discuss area，从0个元素，1一个元素依次推演而来
class Solution {
public:
    vector<vector<int> > subsets(vector<int> &S) {
        sort(S.begin(), S.end());
        vector<vector<int> > res;
        res.push_back(vector<int>());
        for (int i=0; i<S.size(); ++i) {
            int size = res.size();
            for (int j=0; j<size; ++j) {
                res.push_back(res[j]);
                res[size+j].push_back(S[i]);
            }
        }
        return res;
    }
};


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