// Source   : https://oj.leetcode.com/problems/letter-combinations-of-a-phone-number/
// Authoer  : Wang Meng
// Date     : 2014-11-08

/*
 * Given a digit string, return all possible letter combinations that the number could represent.
 * 
 * A mapping of digit to letters (just like on the telephone buttons) is given below.
 * Input:Digit string "23"
 * Output: ["ad", "ae", "af", "bd", "be", "bf", "cd", "ce", "cf"].
 * Note:
 * Although the above answer is in lexicographical order, your answer could be in any order you want.
 */

class Solution {
public:
    string dm[10] = { " ", "@", "abc", "def", "ghi", "jkl", "mno", "pqrs", "tuv", "wxyz" };
    vector<string> letterCombinations(string digits) {
        vector<string> result;
        if (digits.size() == 0) {
            result.push_back("");
            return result;
        }
        string first = dm[digits[0] - '0'];
        vector<string> rest = letterCombinations(digits.substr(1, digits.size()-1));
        for (int i = 0; i < first.size(); ++i) {
            for (int j = 0; j < rest.size(); ++j) {
                result.push_back(first[i] + rest[j]);
            }
        }
        return result;
    }
};