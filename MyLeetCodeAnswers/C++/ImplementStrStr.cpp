// Source   : https://oj.leetcode.com/problems/reverse-words-in-a-string/
// Authoer  : Wang Meng
// Date     : 2014-12-10

/*
 * Implement strStr().
 * 
 * Returns the index of the first occurrence of needle in haystack, or -1 if needle is not part of haystack.
 * 
 * Update (2014-11-02):
 * The signature of the function had been updated to return the index instead of the pointer. If you still see your function signature returns a char * or String, please click the reload button  to reset your code definition.
*/

// 题目更改后的solution，返回index
class Solution {
public:
    int strStr(char *haystack, char *needle) {
        if (!*needle) return 0;
        char *p = haystack;
        while (*p) {
            char *h = p;
            char *n = needle;
            while (*h && *n && *h==*n)
                h++, n++;
            if (!*n) return p - haystack;
            // 这里是关键，注意多了else后算法复杂度变为O((n-m)*m)
            else if (!*h) return -1;
            else p++;
        }
        return -1;
    }
};


#include <iostream>
using namespace std;
/*可以用KMP等算法，太复杂，面试暴力即可*/

/*暴力解法*/
class Solution {
public:
    char *strStr(char *haystack, char *needle) {
        if (!*needle) return haystack;
        char *hp = haystack;
        char *np = needle;
        while (*hp) {
            char *tmp = hp;
            np = needle;
            while (*tmp && *np) {
                if (*tmp == *np) {
                    tmp ++;
                    np ++;
                } else {
                    break;
                }
            }
            if (!*np) return hp;
            // 关键在这里，注意else
            else if (!*tmp) {
                return NULL;
            } else {
                hp++;
            }
        }
        return NULL;
    }
};

int main () {
    Solution s;
    cout << s.strStr("abcdefg", "bcd") << endl;
    cout << s.strStr("abcdefgcdfmg", "cdf") << endl;
    cout << s.strStr("abcdefg", "f") << endl;
    return 0;
}
