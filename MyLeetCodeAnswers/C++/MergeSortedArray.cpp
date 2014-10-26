class Solution {
public:
    void merge(int A[], int m, int B[], int n) {
        int pa = m -1;
        int pb = n -1;
        int pc = m+n-1;
        while (pa >= 0 && pb >= 0) {
            if (A[pa] > B[pb]) {
                A[pc--] = A[pa--];
            } else {
                A[pc--] = B[pb--];
            }
        }
        while (pb >= 0) {
            A[pc--] = B[pb--];
        }
    }
};