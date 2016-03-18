import java.util.Arrays;

/**
 * Source   : https://leetcode.com/problems/3sum-closest/
 * Author   : Wang Meng
 * Date     : 2016-03-18
 * Given an array S of n integers, find three integers in S such that the sum is closest to a given number, target. Return the sum of the three integers. You may assume that each input would have exactly one solution.
 * <p>
 * For example, given array S = {-1 2 1 -4}, and target = 1.
 * <p>
 * The sum that is closest to the target is 2. (-1 + 2 + 1 = 2).
 */
public class ThreeSumClosest {
    public int threeSumClosest(int[] nums, int target) {
        Arrays.sort(nums);
        int clo = Integer.MAX_VALUE;
        int low, high, ret = 0;
        for (int i = 0; i < nums.length - 2; ++i) {
            low = i + 1;
            high = nums.length - 1;
            while (low < high) {
                int sum = nums[i] + nums[low] + nums[high];
                int gap = Math.abs(sum - target);
                if (gap < clo) {
                    clo = gap;
                    ret = sum;
                }
                if (sum < target) low++;
                else high--;
            }
        }
        return ret;
    }
}
