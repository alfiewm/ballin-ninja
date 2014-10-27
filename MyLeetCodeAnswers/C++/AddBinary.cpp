#include <iostream>
#include <string>

using namespace std;

class Solution {
public:
    string addBinary(string a, string b) {
        string c = "";
        bool up = false;
        int ia=a.size() -1, ib=b.size()-1;
        for (; ia >=0 && ib>=0; ia--, ib--) {
            if (a[ia] == '1' && b[ib] == '1') {
                if (up) {
                    c = "1" + c;
                } else {
                    c = "0" + c;
                }
                up = true;
                cout << "if1 c = " << c << endl;
            } else if (a[ia] == '0' && b[ib] == '0') {
                if (up) c = "1" + c;
                else c = "0" + c;
                up = false;
                cout << "if2 c = " << c << endl;
            } else {
                if (up) c = "0" + c;
                else c = "1" + c;
                cout << "if3 c = " << c << endl;
            }
        }
        cout << "before while 1 c = " << c << " " << up << endl;
        while (ia >= 0) {
            if (up) {
                if (a[ia] == '1') {
                    c = "0" + c;
                } else {
                    up = false;
                    c = "1" + c;
                }
            } else {
                c = a[ia] + c;
            }
            ia--;
        }
        cout << "before while 2 c = " << c << " " << up << endl;
        while (ib >= 0) {
            if (up) {
                if (b[ib] == '1') {
                    c = "0" + c;
                } else {
                    up = false;
                    c = "1" + c;
                }
            } else {
                c = b[ib] + c;
            }
            ib--;
        }
        /*SHIT! be carefully man!*/
        if (up) c = "1" + c;
        return c;
    }
};

int main() {
    Solution s;
    string a, b;
    while (cin >> a >> b) {
        cout << s.addBinary(a, b) << endl;
    }
    return 0;
}
