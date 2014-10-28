#include <iostream>
#include <string>
#include <algorithm>
#include <sstream>
using namespace std;

class Solution {
public:
    void reverseWords(string &s) {
        reverse(s.begin(), s.end());
        stringstream ss(s);
        string result = "";
        string input;
        while (ss >> input) {
            reverse(input.begin(), input.end());
            if (!result.empty()) result += " ";
            result += input;
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
