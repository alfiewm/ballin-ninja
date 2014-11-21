// Source   : https://oj.leetcode.com/problems/reverse-words-in-a-string/
// Authoer  : Wang Meng
// Date     : 2014-11-21

#include <iostream>
#include <string>
#include <algorithm>
#include <sstream>
using namespace std;

// one solution from discuss area
class Solution {
public:
    void reverseWords(string &s) {
        string res;
        for (int i = s.size()-1; i >= 0; ) {
            while (i >= 0 && s[i] == ' ') i--;
            if (i < 0) break;
            if (!res.empty()) res.push_back(' ');
            string t;
            while (i >= 0 && s[i] != ' ') t.push_back(s[i--]);
            reverse(t.begin(), t.end());
            res.append(t);
        }
        s = res;
    }
};

// my simlest solution
class Solution {
public:
    void reverseWords(string &s) {
        stringstream ss(s);
        string result = "";
        string input;
        while (ss >> input) {
            if (result.empty()) result = input;
            else result = input + " " + result;
        }
        s = result;
    }
};

int main() {
    Solution s;
    string str = "  it    is   a  shit    ";
    s.reverseWords(str);
    cout << str << endl;
    return 0;
}
