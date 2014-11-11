// Source   : https://oj.leetcode.com/problems/decode-ways/
// Authoer  : Wang Meng
// Date     : 2014-11-11

/*
 * A message containing letters from A-Z is being encoded to numbers using the following mapping:
 * 
 * 'A' -> 1
 * 'B' -> 2
 * ...
 * 'Z' -> 26
 * Given an encoded message containing digits, determine the total number of ways to decode it.
 * 
 * For example,
 * Given encoded message "12", it could be decoded as "AB" (1 2) or "L" (12).
 * 
 * The number of ways decoding "12" is 2.
 */

// 对照修改版
class Solution {
public:
    int numDecodings(string s) {
        if (s.empty()) return 0;
        int y = s.back()=='0' ? 0 : 1;
        int x = 1;
        int result = y;
        for (int i = s.size() -2; i>=0; --i) {
            if (s[i] == '0') result = 0; // 如果是0开头，无法解码
            else result = y + (atoi(s.substr(i, 2).c_str())<=26 ? x :0);
            x = y;
            y = result;
        }
        return result;
    }
};

// Solution from discuss area.
class Solution {
public:
    int numDecodings(string s) {
        if (s.empty()) return 0;
        int t1=s.back()=='0'?0:1, t2=1, rv=t1;
        for (int i=s.size()-2; i>=0; i--) {
            if (s[i]=='0') rv=0;
            else rv=t1+(atoi(s.substr(i, 2).c_str())<=26?t2:0);
            t2=t1;
            t1=rv;
        }
        return rv;
    }
};

// My solution, Wrong Answer, 因为没有考虑错误输入的情况，例如只输入一个“0”，则应返回0
class Solution {
public:
    int numDecodings(string s) {
        if (s.empty()) return 0;
        int x = 0; // ''
        int y = 1; // '
        for (int i = s.size() -2; i>=0; --i) {
            int tmp = y;
            y = y + x;
            if (s[i]=='1' || s[i]=='2') { // 这里也有问题，29也是错误输入，输入输出没有搞清楚哇！
                x = tmp;
            } else {
                x = 0;
            }
        }
        return y + x;
    }
};