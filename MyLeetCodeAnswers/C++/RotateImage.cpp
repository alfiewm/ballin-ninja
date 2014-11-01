#include <iostream>
#include <vector>
using namespace std;

/*My solution*/
class Solution {
public:
    void rotate(vector<vector<int> > &matrix) {
        int n = matrix.size();
        for (int i = 0; i < n/2; ++i) {
            // be carefull about the edges, n-1-i
            for (int j = i; j < n-1-i; ++j) {
                rotateNode(i, j, matrix, n);
            }
        }
    }

    void rotateNode(int x, int y, vector<vector<int> > &matrix, int n) {
        int tmp = matrix[x][y];
        matrix[x][y] = matrix[n-1-y][x];
        matrix[n-1-y][x] = matrix[n-1-x][n-1-y];
        matrix[n-1-x][n-1-y] = matrix[y][n-1-x];
        matrix[y][n-1-x] = tmp;
    }
};

int main() {
    vector<vector<int> > matrix;
    int line1[4] = {1,2,3,4};
    vector<int> V1(line1, line1+4);
    matrix.push_back(V1);
    int line2[4] = {5,6,7,8};
    vector<int> V2(line2, line2+4);
    matrix.push_back(V2);
    int line3[4] = {9,10,11,12};
    vector<int> V3(line3, line3+4);
    matrix.push_back(V3);
    int line4[4] = {13,14,15,16};
    vector<int> V4(line4, line4+4);
    matrix.push_back(V4);

    for (int i = 0; i < matrix.size(); ++i) {
        for (int j = 0; j < matrix[i].size(); ++j) {
            cout << matrix[i][j] << "\t";
        }
        cout << endl;
    }
    cout << "######################" << endl;
    Solution s;
    s.rotate(matrix);
    for (int i = 0; i < matrix.size(); ++i) {
        for (int j = 0; j < matrix[i].size(); ++j) {
            cout << matrix[i][j] << "\t";
        }
        cout << endl;
    }
    return 0;
}
