// Source   : https://oj.leetcode.com/problems/word-search/
// Authoer  : Wang Meng
// Date     : 2014-11-25

/*
 * Given a 2D board and a word, find if the word exists in the grid.
 * 
 * The word can be constructed from letters of sequentially adjacent cell, where "adjacent" cells are those  * horizontally or vertically neighboring. The same letter cell may not be used more than once.
 * 
 * For example,
 * Given board =
 * 
 * [
 *   ["ABCE"],
 *   ["SFCS"],
 *   ["ADEE"]
 * ]
 * word = "ABCCED", -> returns true,
 * word = "SEE", -> returns true,
 * word = "ABCB", -> returns false.
 */

// DFS solution
class Solution {
public:
    bool exist(vector<vector<char> > &board, string word) {
        if (board.empty()) return false;
        bool **used = new bool*[board.size()];
        for (int i=0; i<board.size(); ++i) {
            used[i] = new bool[board[i].size()];
            for (int j=0; j<board[i].size(); ++j)
                used[i][j] = false;
        }
        for (int i=0; i < board.size(); ++i)
            for (int j=0; j<board[i].size(); ++j)
                if (dfs(i, j, 0, used, board, word)) return true;
        return false;
    }
private:
    bool isInBoard(int i, int j, vector<vector<char> > &board) {
        return (i>=0 && i<board.size()
            && j>=0 && j<board[i].size());
    }
    
    bool dfs(int si, int sj, int n, bool** used, vector<vector<char> > &board, string &word) {
        if (n == word.size()) return true;
        if (isInBoard(si, sj, board) && !used[si][sj] && board[si][sj] == word[n]) {
            used[si][sj] = true;
            if (dfs(si+1, sj, n+1, used, board, word)) return true;
            else if (dfs(si, sj+1, n+1, used, board, word)) return true;
            else if (dfs(si-1, sj, n+1, used, board, word)) return true;
            else if (dfs(si, sj-1, n+1, used, board, word)) return true;
            used[si][sj] = false;
        }
        return false;
    }
};