/**
 * Source   : https://leetcode.com/problems/house-robber/
 * Author   : Wang Meng
 * Date     : 2016-03-16
 * You are a professional robber planning to rob houses along a street. Each house has a certain amount of money stashed, the only constraint stopping you from robbing each of them is that adjacent houses have security system connected and it will automatically contact the police if two adjacent houses were broken into on the same night.
 * <p>
 * Given a list of non-negative integers representing the amount of money of each house, determine the maximum amount of money you can rob tonight without alerting the police.
 */
public class HouseRobber {
    public int rob(int[] nums) {
        if (nums == null) return 0;
        int[] rets = new int[nums.length];
        for (int i = 0; i < rets.length; ++i) {
            rets[i] = -1;
        }
        return rob(nums, 0, rets);
    }

    public int rob(int[] nums, int start, int[] rets) {
        if (start > nums.length - 1) return 0;
        if (rets[start] != -1) return rets[start];
        if (start == nums.length - 1) {
            rets[start] = nums[start];
        } else if (start == nums.length - 2) {
            rets[start] = Math.max(nums[start], nums[start + 1]);
        } else {
            int startFrom1 = Math.max(rob(nums, start + 2, rets), rob(nums, start + 3, rets));
            int startFrom2 = Math.max(rob(nums, start + 3, rets), rob(nums, start + 4, rets));
            rets[start] = Math.max(startFrom1 + nums[start], startFrom2 + nums[start + 1]);
        }
        return rets[start];
    }

    // https://leetcode.com/discuss/30020/java-o-n-solution-space-o-1
    public int rob2(int[] nums) {
        int robbedPrevious = 0;
        int notRobbedPrevious = 0;
        for (int i = 0; i < nums.length; ++i) {
            int robbCurrent = notRobbedPrevious + nums[i];
            int notRobCurrent = Math.max(robbedPrevious, notRobbedPrevious);
            robbedPrevious = robbCurrent;
            notRobbedPrevious = notRobCurrent;
        }
        return Math.max(robbedPrevious, notRobbedPrevious);
    }

    public static void main(String[] args) {
        int[] nums = {1, 2, 1, 1};
        System.out.println(new HouseRobber().rob(nums));
        System.out.println(new HouseRobber().rob2(nums));
    }
}
