// Source   : https://oj.leetcode.com/problems/search-in-rotated-sorted-array-ii/
// Authoer  : Wang Meng
// Date     : 2014-11-23

/*
 * Follow up for "Search in Rotated Sorted Array":
 * What if duplicates are allowed?
 * 
 * Would this affect the run-time complexity? How and why?
 * 
 * Write a function to determine if a given target is in the array.
 */

// Solution from discuss area, worst case would be "[1111...111115], 5", O(n) complexity
class Solution {
public:
    bool search(int A[], int n, int target) {
        if (n<1) return -1;
        int low(0), high(n-1);
        while (low <= high) {
            int mid = (low+high)/2;
            if (A[mid] == target) return true;
            if (A[low] < A[mid]) { // left part is sorted
                if (target >= A[low] && target < A[mid]) high = mid-1;
                else low = mid + 1;
            } else if (A[low] > A[mid]) { // right part is sorted
                if (target > A[mid] && target <= A[high]) low = mid+1;
                else high = mid-1;
            } else { // cannot determine, only know that A[low] is not what we are looking for
                low++;
            }
        }
        return false;
    }
};