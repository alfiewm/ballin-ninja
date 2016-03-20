import java.util.ArrayList;
import java.util.List;

/**
 * Source   : https://leetcode.com/problems/permutations/
 * Author   : Wang Meng
 * Date     : 2016-03-20
 * Given a collection of distinct numbers, return all possible permutations.
 * <p>
 * For example,
 * [1,2,3] have the following permutations:
 * [1,2,3], [1,3,2], [2,1,3], [2,3,1], [3,1,2], and [3,2,1].
 */
public class Permutations {
    public List<List<Integer>> permute(int[] nums) {
        List<List<Integer>> ret = new ArrayList<>();
        if (nums == null || nums.length <= 0) return ret;
        permute(nums, 0, ret);
        return ret;
    }

    private void permute(int[] nums, int start, List<List<Integer>> ret) {
        if (start == nums.length - 1) {
            List<Integer> one = new ArrayList<>();
            for (int num : nums) {
                one.add(num);
            }
            ret.add(one);
            return;
        }
        for (int i = start; i < nums.length; ++i) {
            swap(nums, start, i);
            permute(nums, start + 1, ret);
            swap(nums, start, i);
        }
    }

    private void swap(int[] nums, int a, int b) {
        int tmp = nums[a];
        nums[a] = nums[b];
        nums[b] = tmp;
    }
}
