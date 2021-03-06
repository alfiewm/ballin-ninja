class Solution {
public:
    void sortColors(int A[], int n) {
        int redIndex(0), blueIndex(n-1);
        for (int i = 0; i < blueIndex+1;) {
            if (A[i] == 0) {
                swap(A[redIndex++], A[i++]);
            } else if (A[i] == 2) {
                swap(A[blueIndex--], A[i]);
            } else {
                ++i;
            }
        }
    }
};