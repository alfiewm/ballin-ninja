/**
 * Source   : https://leetcode.com/problems/next-permutation/
 * Author   : Wang Meng
 * Date     : 2016-03-20
 * <p>
 * Implement next permutation, which rearranges numbers into the lexicographically next greater permutation of numbers.
 * <p>
 * If such arrangement is not possible, it must rearrange it as the lowest possible order (ie, sorted in ascending order).
 * <p>
 * The replacement must be in-place, do not allocate extra memory.
 * <p>
 * Here are some examples. Inputs are in the left-hand column and its corresponding outputs are in the right-hand column.
 * 1,2,3 → 1,3,2
 * 3,2,1 → 1,2,3
 * 1,1,5 → 1,5,1
 */
public class NextPermutation {
    public void nextPermutation(int[] nums) {
        if (nums == null || nums.length <= 1) return;
        int n = nums.length;
        int i = n - 2;
        for (; i >= 0; i--) {
            if (nums[i] < nums[i + 1]) {
                break;
            }
        }
        if (i < 0) {
            reverse(nums, 0);
        } else {
            int j = n - 1;
            for (; j > i; --j) {
                if (nums[j] > nums[i]) {
                    break;
                }
            }
            int tmp = nums[i];
            nums[i] = nums[j];
            nums[j] = tmp;
            reverse(nums, i + 1);
        }
    }

    private void reverse(int[] nums, int startIndex) {
        if (nums == null || startIndex >= nums.length - 1) return;
        int l = startIndex;
        int r = nums.length - 1;
        while (l < r) {
            int tmp = nums[l];
            nums[l] = nums[r];
            nums[r] = tmp;
            l++;
            r--;
        }
    }
}
