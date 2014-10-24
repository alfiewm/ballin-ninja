class Solution {
    int N = 0; 
    int count = 0;
    int *A = NULL;
public:
    int totalNQueens(int n) {
        this->N = n;
        A = new int[N];
        count = 0;
        put(0);
        delete [] A;
        A = NULL;
        return count;
    }
    // put n意思为在第n行放置第n个皇后
    int put(int n) {
        for (int i = 0; i < N; ++i) {
            A[n] = i; // 第n个皇后放置在第n行的第i个位置，关键！
            if (check(n)) {
                if (n == N-1) count++;
                else put(n+1);
            }
        }
    }
    bool check(int n) {
        for (int i = 0; i < n; ++i) {
            if (A[i]==A[n] || fabs(i-n) == fabs(A[i] - A[n])) {
                return false;
            }
        }
        return true;
    }
};