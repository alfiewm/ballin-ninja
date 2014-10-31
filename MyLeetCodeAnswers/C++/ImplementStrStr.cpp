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
