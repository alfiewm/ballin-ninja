/**
 * Source   : https://leetcode.com/problems/minimum-size-subarray-sum/
 * Author   : Wang Meng
 * Date     : 2016-03-13
 * Given an array of n positive integers and a positive integer s, find the minimal length of a subarray of which the sum ≥ s. If there isn't one, return 0 instead.
 * <p>
 * For example, given the array [2,3,1,2,4,3] and s = 7,
 * the subarray [4,3] has the minimal length under the problem constraint.
 * <p>
 * click to show more practice.
 * <p>
 * More practice:
 * If you have figured out the O(n) solution, try coding another solution of which the time complexity is O(n log n).
 */
public class KthLargestElementInAnArray {
    public int findKthLargest(int[] nums, int k) {
        if (nums == null || nums.length < k || k <= 0) return -1;
        return findKthLargest(nums, 0, nums.length - 1, k);
    }

    private int findKthLargest(int[] nums, int start, int end, int k) {
        if (start >= end) {
            return nums[start];
        }
        int pos = partition(nums, start, end);
        if ((pos - start) == k - 1) {
            // this is the number
            return nums[pos];
        } else if ((pos - start) > k - 1) {
            return findKthLargest(nums, start, pos - 1, k);
        } else {
            return findKthLargest(nums, pos + 1, end, k - (pos - start + 1));
        }
    }

    private int partition(int[] nums, int start, int end) {
        int key = nums[start];
        while (start < end) {
            while (start < end && nums[end] <= key) end--;
            nums[start] = nums[end];
            while (start < end && nums[start] >= key) start++;
            nums[end] = nums[start];
        }
        nums[start] = key;
        return start;
    }

    public static void main(String[] args) {
        int[] nums = {1, 2, 3, 5, 4, 9};
        int[] nums2 = {7, 6, 5, 4, 3, 2, 1};
        System.out.println(new KthLargestElementInAnArray().findKthLargest(nums2, 5));
    }
}
