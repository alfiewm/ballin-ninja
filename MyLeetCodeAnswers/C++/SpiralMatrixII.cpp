// Source   : https://oj.leetcode.com/problems/spiral-matrix-ii/
// Authoer  : Wang Meng
// Date     : 2014-11-24

/*
 * Given an integer n, generate a square matrix filled with elements from 1 to n2 in spiral order.
 * 
 * For example,
 * Given n = 3,
 * 
 * You should return the following matrix:
 * [
 *  [ 1, 2, 3 ],
 *  [ 8, 9, 4 ],
 *  [ 7, 6, 5 ]
 * ]
*/

// 解法类似Spiral Matrix，状态转换，层次遍历赋值
class Solution {
public:
    vector<vector<int> > generateMatrix(int n) {
        vector<vector<int> > res(n, vector<int>(n, 0));
        int layer = 0;
        int state = 1;
        int row(0), col(0);
        for (int i = 0; i < n*n; ++i) {
            res[row][col] = i+1;
            switch(state) {
            case 1: // go right
                if (col == n - layer - 1) {
                    row++;
                    state = 2;
                } else col++;
                break;
            case 2: // go down
                if (row == n - layer - 1) {
                    col--;
                    state=3;
                } else row++;
                break;
            case 3: // go left
                if (col == layer) {
                    row--;
                    state=4;
                } else col--;
                break;
            case 4: // go up
                if (row == layer+1) {
                    col++;
                    state=1;
                    layer++;
                } else row--;
                break;
            }
        }
        return res;
    }
};