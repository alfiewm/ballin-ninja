// Source   : https://oj.leetcode.com/problems/longest-substring-without-repeating-characters/
// Authoer  : Wang Meng
// Date     : 2014-11-19

/*
 * Given a string, find the length of the longest substring without repeating characters.
 * For example, the longest substring without repeating letters for "abcabcbb" is "abc", which the length is 3.
 * For "bbbbb" the longest substring is "b", with the length of 1.
 */

// My solution
class Solution {
public:
    int lengthOfLongestSubstring(string s) {
        if (s.empty()) return 0;
        int start = 0;
        int cur = 1;
        int maxL = 1;
        int curCount = 1;
        while (cur != s.size()) {
            int dup = s.find(s[cur], start);
            if (dup == cur) {
                curCount++;
                maxL = (maxL > curCount) ? maxL : curCount;
            } else {
                curCount -= (dup - start);
                start = dup+1;
            }
            cur++;
        }
        return maxL;
    }
};