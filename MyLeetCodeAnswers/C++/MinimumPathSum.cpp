class Solution {
public:
    int minPathSum(vector<vector<int> > &grid) {
        // store intermediate result
        vector<vector<int> > op(grid);
        for (int i = 0; i < op.size(); ++i) {
            for (int j = 0; j < op[i].size(); ++j) {
                op[i][j] = -1;
            }
        }
        op[op.size()-1][op[0].size()-1] = grid[op.size()-1][op[0].size()-1];
        return minPathSum(grid, op, 0, 0);
    }
    
    int minPathSum(vector<vector<int> > &grid, vector<vector<int> > &op, int i, int j) {
        if (op[i][j] != -1) {
            return op[i][j];
        }
        int result = grid[i][j];
        int right = INT_MAX;
        int down = INT_MAX;
        if (j + 1 < grid[0].size())
            right = minPathSum(grid, op, i, j+1);
        if (i + 1 < grid.size())
            down = minPathSum(grid, op, i+1, j);
        result += min(right, down);
        op[i][j] = result;
        return result;
    }
};