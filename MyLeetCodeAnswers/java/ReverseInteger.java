/**
 * Source   : https://leetcode.com/problems/reverse-integer/
 * Author   : Wang Meng
 * Date     : 2016-03-16
 *
 * 注意Integer可能溢出!!!!!!!!!!!
 *
 * Reverse digits of an integer.
 * <p>
 * Example1: x = 123, return 321
 * Example2: x = -123, return -321
 * <p>
 * click to show spoilers.
 * <p>
 * Have you thought about this?
 * Here are some good questions to ask before coding. Bonus points for you if you have already thought through this!
 * <p>
 * If the integer's last digit is 0, what should the output be? ie, cases such as 10, 100.
 * <p>
 * Did you notice that the reversed integer might overflow? Assume the input is a 32-bit integer, then the reverse of 1000000003 overflows. How should you handle such cases?
 * <p>
 * For the purpose of this problem, assume that your function returns 0 when the reversed integer overflows.
 */
public class ReverseInteger {
    public class Solution {
        public int reverse(int x) {
            int n = Math.abs(x);
            int ret = 0;
            while (n != 0) {
                if (ret > (Integer.MAX_VALUE - n % 10) / 10) {
                    // overflow
                    return 0;
                }
                ret = ret * 10 + n % 10;
                n /= 10;
            }
            return (x > 0) ? ret : -ret;
        }
    }
}
