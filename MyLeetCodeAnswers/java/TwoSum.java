import java.util.Arrays;

/**
 * Given an array of integers, return indices of the two numbers such that they add up to a specific target.
 * <p/>
 * You may assume that each input would have exactly one solution.
 * <p/>
 * Example:
 * Given nums = [2, 7, 11, 15], target = 9,
 * <p/>
 * Because nums[0] + nums[1] = 2 + 7 = 9,
 * return [0, 1].
 * UPDATE (2016/2/13):
 * The return format had been changed to zero-based indices. Please read the above updated description carefully.
 */
public class TwoSum {
  public int[] twoSum(int[] nums, int target) {
    int[] tmpNums = new int[nums.length];
    int[] result = new int[2];
    System.arraycopy(nums, 0, tmpNums, 0, nums.length);
    Arrays.sort(tmpNums);
    int low = 0, high = nums.length - 1;
    while (low < high) {
      if (tmpNums[low] + tmpNums[high] == target) {
        break;
      } else if (tmpNums[low] + tmpNums[high] < target) {
        low++;
      } else {
        high--;
      }
    }
    result[0] = -1;
    result[1] = -1;
    for (int i = 0; i < nums.length; ++i) {
      if (nums[i] == tmpNums[low] && result[0] < 0) {
        result[0] = i;
      } else if (nums[i] == tmpNums[high]) {
        result[1] = i;
      }
    }
    if (result[0] > result[1]) {
      int tmp = result[0];
      result[0] = result[1];
      result[1] = tmp;
    }
    return result;
  }

  public static void main(String[] args) {
    int[] input = new int[5];
    input[0] = 11;
    input[1] = 7;
    input[2] = 2;
    input[3] = 15;
    input[4] = 14;
    System.out.println(Arrays.toString(new TwoSum().twoSum(input, 9)));
  }
}
