// Source   : https://oj.leetcode.com/problems/longest-substring-without-repeating-characters/
// Authoer  : Wang Meng
// Date     : 2014-11-20(modified)

/*
 * Given a string, find the length of the longest substring without repeating characters.
 * For example, the longest substring without repeating letters for "abcabcbb" is "abc", which the length is 3.
 * For "bbbbb" the longest substring is "b", with the length of 1.
 */

// solution from discuss area, 核心想法是使用hashtable，而且不仅仅存储某个字符是否出现过，而是充分利用存储其上次出现的index
class Solution {
public:
    int lengthOfLongestSubstring(string s) {
        int idx[256];
        memset(idx, -1, sizeof(idx));
        int maxL = 0;
        int start = 0;
        for (int i = 0; i < s.size(); ++i) {
            if (idx[s[i]] >= start) {
                start = idx[s[i]] + 1;
            }
            maxL = max(maxL, i - start + 1);
            idx[s[i]] = i;
        }
        return maxL;
    }
};

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