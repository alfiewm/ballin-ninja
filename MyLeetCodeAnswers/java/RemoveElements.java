/**
 * Source   : https://leetcode.com/problems/remove-duplicates-from-sorted-array/
 * Author   : Wang Meng
 * Date     : 2016-03-19
 * <p>
 * Given an array and a value, remove all instances of that value in place and return the new length.
 * <p>
 * Do not allocate extra space for another array, you must do this in place with constant memory.
 * <p>
 * The order of elements can be changed. It doesn't matter what you leave beyond the new length.
 * <p>
 * Example:
 * Given input array nums = [3,2,2,3], val = 3
 * <p>
 * Your function should return length = 2, with the first two elements of nums being 2.
 */
public class RemoveElements {
    public int removeElement(int[] nums, int val) {
        if (nums == null) return 0;
        int low = 0, high = nums.length - 1;
        while (low <= high) {
            while (low <= high && nums[low] != val) low++;
            while (low <= high && nums[high] == val) high--;
            if (low >= high) {
                return low;
            } else {
                int tmp = nums[low];
                nums[low] = nums[high];
                nums[high] = tmp;
                low++;
                high--;
            }
        }
        return low;
    }
}
