// Source   : https://oj.leetcode.com/problems/generate-parentheses/
// Authoer  : Wang Meng
// Date     : 2014-11-16

/*
 * Given n pairs of parentheses, write a function to generate all combinations of well-formed parentheses.
 * 
 * For example, given n = 3, a solution set is:
 * 
 * "((()))", "(()())", "(())()", "()(())", "()()()"
 */

class Solution {
public:
    vector<string> generateParenthesis(int n) {
        vector<string> result;
        if (n < 1) return result;
        int lc(0);
        int rc(0);
        return generateParenthesis(n, lc, rc);
    }
    
    vector<string> generateParenthesis(int n, int lc, int rc) {
        if (rc == n) return vector<string>(1, "");
        vector<string> res;
        // option 1 : put (
        if (lc < n) {
            vector<string> tmp = generateParenthesis(n, lc+1, rc);
            for (int i = 0; i < tmp.size(); ++i) {
                res.push_back("(" + tmp[i]);
            }
        }
        if (lc > rc) {
            // if left count > right count, can put )
            vector<string> tmp = generateParenthesis(n, lc, rc+1);
            for (int i = 0; i< tmp.size(); ++i) {
                res.push_back(")" + tmp[i]);
            }
        }
        return res;
    }
};