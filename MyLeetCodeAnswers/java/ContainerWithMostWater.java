/**
 * Source   : https://leetcode.com/problems/container-with-most-water/
 * Author   : Wang Meng
 * Date     : 2016-03-16
 * Given n non-negative integers a1, a2, ..., an, where each represents a point at coordinate (i, ai). n vertical lines are drawn such that the two endpoints of line i is at (i, ai) and (i, 0). Find two lines, which together with x-axis forms a container, such that the container contains the most water.
 * <p>
 * Note: You may not slant the container.
 */
public class ContainerWithMostWater {
    public int maxArea(int[] height) {
        int maxA = 0;
        int left = 0;
        int right = height.length - 1;
        while (left < right) {
            maxA = Math.max(Math.min(height[left], height[right]) * (right - left), maxA);
            if (height[left] < height[right]) {
                left++;
            } else {
                right--;
            }
        }
        return maxA;
    }
}
