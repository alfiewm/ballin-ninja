// Source   : https://oj.leetcode.com/problems/path-sum/
// Authoer  : Wang Meng
// Date     : 2014-11-13

/*
 * Given a matrix of m x n elements (m rows, n columns), return all elements of the matrix in spiral order.
 * 
 * For example,
 * Given the following matrix:
 * 
 * [
 *  [ 1, 2, 3 ],
 *  [ 4, 5, 6 ],
 *  [ 7, 8, 9 ]
 * ]
 * You should return [1,2,3,6,9,8,7,4,5].
 */

class Solution {
public:
    vector<int> spiralOrder(vector<vector<int> > &matrix) {
        int rows = matrix.size();
        if (rows == 0) return vector<int>();
        int cols = matrix[0].size();
        int row(0), col(0), layer(0); // layer represents one circle
        vector<int> res;
        res.push_back(matrix[0][0]);
        int state = 1;
        for (int step = 1; step < rows*cols; step++) {
            switch(state) {
            case 1: // go right
                if (col == cols - layer -1) {
                    row++;
                    state=2;
                } else col ++;
                break;
            case 2: // go down
                if (row == rows - layer -1) {
                    col--;
                    state=3;
                } else row ++;
                break;
            case 3: // go left
                if (col == layer) {
                    row--;
                    state=4;
                } else col --;
                break;
            case 4: // go up
                if (row == layer+1) {
                    col++;
                    state=1;
                    layer++;
                } else row --;
                break;
            }
            res.push_back(matrix[row][col]);
        }
        return res;
    }
};