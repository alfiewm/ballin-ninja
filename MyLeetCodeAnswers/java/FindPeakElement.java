/**
 * A peak element is an element that is greater than its neighbors.
 * <p/>
 * Given an input array where num[i] ≠ num[i+1], find a peak element and return its index.
 * <p/>
 * The array may contain multiple peaks, in that case return the index to any one of the peaks is fine.
 * <p/>
 * You may imagine that num[-1] = num[n] = -∞.
 * <p/>
 * For example, in array [1, 2, 3, 1], 3 is a peak element and your function should return the index number 2.
 * <p/>
 * click to show spoilers.
 * <p/>
 * Note:
 * Your solution should be in logarithmic complexity.
 * <p/>
 * 先观察，假设nums[-1]是负无穷，则从第2个数开始，只要比前一个小，则前一个即为一个peak，观察到这个规律后
 * 就可以应用二分法、或者递归查找
 */
public class FindPeakElement {
  // O(n) solution
  public int findPeakElement(int[] nums) {
    if (nums == null || nums.length <= 0) {
      return -1;
    }
    for (int i = 1; i < nums.length; ++i) {
      if (nums[i] < nums[i - 1]) {
        return i - 1;
      }
    }
    return nums.length - 1;
  }

  // O(lgn) recursive solution
  public int findPeakElement2(int[] nums) {
    return recursiveHelper(nums, 0, nums.length - 1);
  }

  public int recursiveHelper(int[] nums, int start, int end) {
    if (start == end) return start;
    else {
      int mid1 = (start + end) / 2;
      int mid2 = mid1 + 1;
      if (nums[mid1] > nums[mid2]) {
        return recursiveHelper(nums, start, mid1);
      } else {
        return recursiveHelper(nums, mid2, end);
      }
    }
  }

  // O(lgn) binary search solution
  public int findPeakElement3(int[] nums) {
    int low = 0;
    int high = nums.length - 1;
    while (low < high) {
      int mid = (low + high) / 2;
      int mid2 = mid + 1;
      if (nums[mid] > nums[mid2]) {
        high = mid;
      } else {
        low = mid2;
      }
    }
    return low;
  }

  public static void main(String[] args) {
    int[] input = new int[5];
    input[0] = 11;
    input[1] = 7;
    input[2] = 2;
    input[3] = 15;
    input[4] = 14;
    System.out.println(new FindPeakElement().findPeakElement(input));
  }
}
