// Source   : https://oj.leetcode.com/problems/set-matrix-zeroes/
// Authoer  : Wang Meng
// Date     : 2014-11-21

/*
 * Given a m x n matrix, if an element is 0, set its entire row and column to 0. Do it in place.
 * 
 * click to show follow up.
 * 
 * Follow up:
 * Did you use extra space?
 * A straight forward solution using O(mn) space is probably a bad idea.
 * A simple improvement uses O(m + n) space, but still not the best solution.
 * Could you devise a constant space solution?
 */

// O(1) space, 核心想法是用两个bool保存第一行和第一列是否包含有0，然后用第一行和第一列来保存其他行列的状态
class Solution {
public:
    void setZeroes(vector<vector<int> > &matrix) {
        int m = matrix.size();
        if (m == 0) return ;
        int n = matrix[0].size();
        if (n == 0) return ;
        
        // store first row and colum whether has 0
        bool has0FirstRow = false;
        bool has0FirstColumn = false;
        for (int i=0; i<m; ++i) {
            if (matrix[i][0] == 0) {
                has0FirstColumn = true;
                break;
            } 
        }
        for (int j=0; j<n; ++j) {
            if (matrix[0][j] == 0) {
                has0FirstRow = true;
                break;
            }
        }
        
        // traversal matrix and set status on the first row and column if need to set zero
        for (int i=1; i<m; ++i) {
            for (int j=1; j<n; ++j) {
                if (matrix[i][j] == 0) {
                    matrix[0][j] = 0;
                    matrix[i][0] = 0;
                }
            }
        }
        
        // set zeros according to first row and coloumn status
        for (int i=1; i<m; ++i) {
            for (int j=1; j<n; ++j) {
                if (matrix[i][0] == 0 || matrix[0][j] == 0) {
                    matrix[i][j] = 0;
                }
            }
        }
        
        // set zeros for first row and column if ineeded
        if (has0FirstRow) {
            for (int j=0; j<n; ++j)
                matrix[0][j] = 0;
        }
        if (has0FirstColumn) {
            for (int i=0; i<m; ++i)
                matrix[i][0] = 0;
        }
    }
};

// my solution, not good, O(m+n) space, O(mn) time
class Solution {
public:
    void setZeroes(vector<vector<int> > &matrix) {
        int m = matrix.size();
        if (m == 0) return ;
        int n = matrix[0].size();
        if (n == 0) return ;
        vector<int> ivec(m, 0);
        vector<int> jvec(n, 0);
        for (int i = 0; i < m; ++i) {
            for (int j = 0; j < n; ++j) {
                if (matrix[i][j] == 0) {
                    ivec[i] = 1;
                    jvec[j] = 1;
                }
            }
        }
        for (int i=0; i<m; ++i) {
            if (ivec[i] == 1) {
                for (int j=0; j <n; ++j) {
                    matrix[i][j] = 0;
                }
            }
        }
        
        for (int j=0; j<n; ++j) {
            if (jvec[j] == 1) {
                for (int i=0; i<m; ++i) {
                    matrix[i][j] = 0;
                }
            }
        }
    }
};