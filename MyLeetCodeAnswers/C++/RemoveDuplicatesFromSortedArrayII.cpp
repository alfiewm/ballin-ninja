// Source   : https://oj.leetcode.com/problems/remove-duplicates-from-sorted-array-ii/7
// Authoer  : Wang Meng
// Date     : 2014-11-17

/*
 * Follow up for "Remove Duplicates":
 * What if duplicates are allowed at most twice?
 * 
 * For example,
 * Given sorted array A = [1,1,1,2,2,3],
 * 
 * Your function should return length = 5, and A is now [1,1,2,2,3].
 */


// My solution
class Solution {
public:
    int removeDuplicates(int A[], int n) {
        if (n <= 1) return n;
        int start = 0;
        for (int i = 0; i < n;) {
            if (i+1 < n && A[i+1] == A[i]) {
                int key = A[i];
                A[start++] = key;
                A[start++] = key;
                i+=2;
                while (i < n && A[i] == key) i++;
            } else {
                A[start++] = A[i++];
            }
        }
        return start;
    }
};