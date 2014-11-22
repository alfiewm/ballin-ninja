// Source   : https://oj.leetcode.com/problems/search-in-rotated-sorted-array/
// Authoer  : Wang Meng
// Date     : 2014-11-22

/*
 * Suppose a sorted array is rotated at some pivot unknown to you beforehand.
 * 
 * (i.e., 0 1 2 4 5 6 7 might become 4 5 6 7 0 1 2).
 * 
 * You are given a target value to search. If found in the array return its index, otherwise return -1.
 * 
 * You may assume no duplicate exists in the array.
 */

 // My Solution, 小心判断各种边界条件，主要是mid的落点是在哪个区间
class Solution {
public:
    int search(int A[], int n, int target) {
        if (n<1) return -1;
        int low(0), high(n-1);
        while (low <= high) {
            int mid = (low+high)/2;
            if (A[mid] == target) return mid;
            else if (A[mid] > target) {
                if (A[mid] < A[low] || A[low] <= target) {
                    high = mid-1;
                } else {
                    low = mid+1;
                }
            } else { // A[mid] < target
            	// 第一次这里写了A[mid]>A[low],少了一个等号，非常重要
                if (A[mid] >= A[low] || A[low] > target) {
                    low = mid+1;
                } else {
                    high = mid-1;
                }
            }
        }
        return -1;
    }
};