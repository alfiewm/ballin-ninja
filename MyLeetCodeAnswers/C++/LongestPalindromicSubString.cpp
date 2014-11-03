#include <stdio.h>
#include <stdlib.h>
#include <time.h>
#include <iostream>
#include <vector>
#include <algorithm>
#include <assert.h>
using namespace std;

/*From chen hao, 利用了回文字串中心对称的特性，每次找中心点向两边延展*/
class Solution3 {
public:
    string findPalindrome(string s, int left, int right) {
        int n = s.size();
        while (left>=0 && right <=n-1 && s[left] == s[right]) {
            left--;
            right++;
        }
        return s.substr(left+1, right-left-1);
    }
    string longestPalindrome(string s) {
        int n = s.size();
        if (n <= 1) return s;
        string longest;
        string str;
        for (int i=0; i<n-1; ++i) {
            str = findPalindrome(s, i, i);
            if (str.size() > longest.size()) {
                longest = str;
            }
            str = findPalindrome(s, i, i+1);
            if (str.size() > longest.size()) {
                longest = str;
            }
        }
        return longest;
    }
};

/*OneNote 字符串处理算法, 依然TLE*/
class Solution2 {
public:
    int LCS[1000][1000];
    string longestPalindrome(string s) {
        string origin = s;
        reverse(s.begin(), s.end());
        int n = s.size();
        assert(n <= 1000);
        // 查找origin和s的最长公共子串
        int maxL = 0;
        for (int i = 0; i < n; ++i) {
            for (int j = 0; j <n; ++j) {
                if (s[i] == origin[j]) {
                    LCS[i][j] = calcuate(i,j);
                    maxL = max(LCS[i][j], maxL);
                } else LCS[i][j] = 0;
            }
        }
        for (int i = 0; i < n; ++i) {
            for (int j = 0; j <n; ++j) {
                if (LCS[i][j] == maxL) {
                    return origin.substr(j-maxL+1, maxL);
                }
            }
        }
        return "";
    }

    int calcuate(int i, int j) {
        if (i-1 < 0 || j-1 < 0) {
            return 1;
        } else {
            return LCS[i-1][j-1]+1;
        }
    }
};


/*暴力算法，TLE*/
class Solution {
public:
    string longestPalindrome(string s) {
        string max = "";
        string origin = s;
        reverse(s.begin(), s.end());
        for (int i = 0; i < s.size() - max.size(); ++i) {
            for (int j = s.size(); j > (i+max.size()); j--) {
                string test = s.substr(i, j-i);
                if (origin.find(test) != string::npos) {
                    max = test;
                }
            }
        }
        return max;
    }
};

int main () {
    string s;
    Solution so;
    Solution2 so2;
    Solution3 so3;
    while (cin >> s) {
        cout << so.longestPalindrome(s) << endl;
        cout << so2.longestPalindrome(s) << endl;
        cout << so3.longestPalindrome(s) << endl;
    }
    return 0;
}
