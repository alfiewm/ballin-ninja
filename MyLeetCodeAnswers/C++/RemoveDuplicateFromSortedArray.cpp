class Solution {
public:
    int removeDuplicates(int A[], int n) {
        if (n <= 1) return n;
        int cur = 0;
        for (int i = 1; i < n; ++i) {
            if (A[i] == A[cur]) {
                continue;
            } else {
                A[++cur] = A[i];
            }
        }
        return cur+1;
    }
};
/*
// LeetCode, Remove Duplicates from Sorted Array // 使用 STL,时间复杂度 O(n),空间复杂度 O(1) class Solution {
public:
      int removeDuplicates(int A[], int n) {
          return distance(A, unique(A, A + n));
} };
*/