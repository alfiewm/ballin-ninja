/**
 * Source   : https://leetcode.com/problems/unique-paths/
 * Author   : Wang Meng
 * Date     : 2016-03-20
 * <p>
 * A robot is located at the top-left corner of a m x n grid (marked 'Start' in the diagram below).
 * <p>
 * The robot can only move either down or right at any point in time. The robot is trying to reach the bottom-right corner of the grid (marked 'Finish' in the diagram below).
 * <p>
 * How many possible unique paths are there?
 */
public class UniquePaths {
    public int uniquePaths(int m, int n) {
        if (m <= 0 || n <= 0) return 0;
        int[][] paths = new int[m + 1][n + 1];
        for (int i = 1; i < m + 1; ++i) {
            for (int j = 1; j < n + 1; ++j) {
                paths[i][j] = -1;
            }
        }
        return uniquePaths(paths, m, n);
    }

    public int uniquePaths(int[][] paths, int m, int n) {
        if (paths[m][n] != -1) return paths[m][n];
        int path = 1;
        if (m > 1 && n > 1) {
            path = uniquePaths(paths, m - 1, n) + uniquePaths(paths, m, n - 1);
        }
        paths[m][n] = path;
        return path;
    }
}
