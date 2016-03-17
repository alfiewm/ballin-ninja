/**
 * Source   : https://leetcode.com/problems/container-with-most-water/
 * Author   : Wang Meng
 * Date     : 2016-03-16
 * <p>
 * Given n non-negative integers a1, a2, ..., an, where each represents a point at coordinate (i, ai). n vertical lines are drawn such that the two endpoints of line i is at (i, ai) and (i, 0). Find two lines, which together with x-axis forms a container, such that the container contains the most water.
 * <p>
 * Note: You may not slant the container.
 */
public class ContainerWithMostWater {
    /**
     * 这个解法不是很直观,需要证明一下,如下:
     * 假设我们得到的不是最优解,也就是说存在一个ol<or使得两者之间的面积更大,因为我们的解法只有在两个指针相遇的时候才会终止,这说明我们的两个指针必定访问过他们中的一个,而那时另外一个不是另一个
     * 假设我们先访问到的是ol(or还没有访问到),那么这个指针不会再移动,除非以下两种情况:
     * a. 两个指针相遇,也就是说我们肯定访问过or了,这与假设相悖,不成立
     * b. 假设另外一个指针现在在orr,那么当height[orr]>height[ol]时,我们会移动ol指针,但注意这个时候ol到orr的面积必然大于ol到or的面积!这与我们的假设又违背了!
     * 所以说我们的算法成立!
     */
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
