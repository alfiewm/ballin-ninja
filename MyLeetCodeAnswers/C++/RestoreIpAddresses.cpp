// Source   : https://oj.leetcode.com/problems/restore-ip-addresses/
// Authoer  : Wang Meng
// Date     : 2014-11-08

/*
 * Given a string containing only digits, restore it by returning all possible valid IP address combinations.
 * 
 * For example:
 * Given "25525511135",
 * 
 * return ["255.255.11.135", "255.255.111.35"]. (Order does not matter)
 */

// My solution, 改了两遍，第一遍没有考虑到01这种不合法的情况，第二遍优化了一下，讲substr方法放到各个循环第一行，而不是在最里面
class Solution {
public:
    vector<string> restoreIpAddresses(string s) {
        vector<string> result;
        for (int i = 1; i <= 3 && s.size()>i; ++i) {
            string first = s.substr(0, i);
            if (first.size() > 1 && first[0] == '0') continue;
            for (int j = 1; j <= 3 && s.size()>i+j; ++j) {
                string second = s.substr(i, j);
                if (second.size() > 1 && second[0] == '0') continue;
                for (int k = 1; k <= 3 && s.size()>i+j+k; ++k) {
                    int l = s.size() - i - j - k;
                    if (l > 3) continue;
                    string third = s.substr(i+j, k);
                    if (third.size() > 1 && third[0] == '0') continue;
                    string forth = s.substr(i+j+k, l);
                    if (forth.size() > 1 && forth[0] == '0') continue;
                    if (atoi(first.c_str()) < 256 && atoi(second.c_str()) < 256
                            && atoi(third.c_str()) < 256 && atoi(forth.c_str()) < 256) {
                        result.push_back(first + "." + second + "." + third + "." + forth);
                    }
                }
            }
        }
        return result;
    }
};