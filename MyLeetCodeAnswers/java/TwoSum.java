import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

/**
 * Source   : https://leetcode.com/problems/two-sum/
 * Author   : Wang Meng
 * Date     : 2016-03-15
 * <p>
 * Given an array of integers, return indices of the two numbers such that they add up to a specific target.
 * <p>
 * You may assume that each input would have exactly one solution.
 * <p>
 * Example:
 * Given nums = [2, 7, 11, 15], target = 9,
 * <p>
 * Because nums[0] + nums[1] = 2 + 7 = 9,
 * return [0, 1].
 */
public class TwoSum {
    public static int[] twoSum(int[] nums, int target) {
        Map<Integer, Integer> map = new HashMap<>();
        for (int i = 0; i < nums.length; ++i) {
            map.put(nums[i], i);
        }
        for (int i = 0; i < nums.length; ++i) {
            if (map.containsKey(target - nums[i]) && map.get(target - nums[i]) > i) {
                return new int[]{i, map.get(target - nums[i])};
            }
        }
        return null;
    }

    public static void main(String[] args) {
        int[] nums = {7, 11, 15, 2};
        System.out.println(Arrays.toString(TwoSum.twoSum(nums, 9)));
    }
}
