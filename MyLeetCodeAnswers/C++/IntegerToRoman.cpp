#include <iostream>
#include <string>
#include <vector>
using namespace std;

    int dictKey[13] = {1000, 900, 500, 400, 100, 90, 50, 40, 10, 9, 5, 4, 1};
    string dictValue[13] = {"M", "CM", "D", "CD", "C", "XC", "L", "XL", "X", "IX", "V", "IV", "I"};
string intToRoman(int num) {
    string result = "";
    for (int i = 0; i < 13; ++i) {
        while (num - dictKey[i] >= 0) {
            result += dictValue[i];
            num -= dictKey[i];
        }
    }
    return result;
}

int main() {
    int input;
    while (cin >> input) {
        cout << intToRoman(input) << endl;
    }
    return 0;
}
