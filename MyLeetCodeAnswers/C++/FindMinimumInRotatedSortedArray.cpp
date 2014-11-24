// Source   : https://oj.leetcode.com/problems/find-minimum-in-rotated-sorted-array/
// Authoer  : Wang Meng
// Date     : 2014-11-24

/*
 * Suppose a sorted array is rotated at some pivot unknown to you beforehand.
 * 
 * (i.e., 0 1 2 4 5 6 7 might become 4 5 6 7 0 1 2).
 * 
 * Find the minimum element.
 * 
 * You may assume no duplicate exists in the array.
 */

class Solution {
public:
    int findMin(vector<int> &num) {
        assert(num.size() > 0);
        int low = 0;
        int high = num.size() - 1;
        while (low < high) {
            if (num[low] < num[high]) break;
            
            int mid = (low + high) /2;
            if (num[mid] < num[low]) {
                high = mid;
            } else {
                low = mid + 1;
            }
        }
        
        return num[low];
    }
};