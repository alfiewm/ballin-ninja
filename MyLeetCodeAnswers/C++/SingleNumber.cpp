#include <iostream>
using namespace std;

int main() {
    int output = 0;
    int input = 0;
    while (cin >> input) {
        output = output ^ input;
    }
    cout << output << endl;
}
