#include <iostream>
#include <vector>
using namespace std;

/*Soution from discuss*/
class Solution {
public:
    bool isValidSudoku(vector<vector<char> > &board) {
        for (int i = 0; i < 9; ++i) {
            vector<int> count1(9, 0), count2(9, 0), count3(9, 0);
            for (int j = 0; j < 9; ++j) {
                if (board[i][j] != '.') {
                    int num1 = board[i][j]-'1';
                    if (count1[num1] != 0)
                        return false;
                    else
                        count1[num1] = 1;
                }

                if (board[j][i] != '.') {
                    int num2 = board[j][i]-'1';
                    if (count2[num2] != 0)
                        return false;
                    else
                        count2[num2] = 1;
                }

                if (board[i/3*3+j/3][i%3*3+j%3] != '.') {
                    int num3 = board[i/3*3+j/3][i%3*3+j%3] - '1';
                    if (count3[num3] != 0)
                        return false;
                    else
                        count3[num3] = 1;
                }
            }
        }
        return true;
    }
};

/*My solution*/
class Solution {
public:
    int b[9];
    bool isValidSudoku(vector<vector<char> > &board) {
        // test horizontal
        for (int i = 0; i < 9; ++i) {
            reset();
            for (int j = 0; j < 9; ++j) {
                if (board[i][j] == '.') continue;
                else if (b[board[i][j] - '1']) return false;
                else b[board[i][j] - '1'] = 1;
            }
        }
        // test vertical
        for (int i = 0; i < 9; ++i) {
            reset();
            for (int j = 0; j < 9; ++j) {
                if (board[j][i] == '.') continue;
                else if (b[board[j][i] - '1']) return false;
                else b[board[j][i] - '1'] = 1;
            }
        }
        // test blocks
        for (int i = 0; i < 3; ++i) {
            for (int j = 0; j < 3; ++j) {
                reset();
                for (int k = 0; k < 3; ++k) {
                    for (int l = 0; l < 3; ++l) {
                        if (board[i*3+k][j*3+l] == '.') continue;
                        else if (b[board[i*3+k][j*3+l] - '1']) return false;
                        else b[board[i*3+k][j*3+l] - '1'] = 1;
                    }
                }
            }
        }
        return true;
    }

    void reset() {
        for (int i=0; i < 9; ++i) {
            b[i] = 0;
        }
    }
};

int main() {
    return 0;
}
