/**
 * Source   : https://leetcode.com/problems/divide-two-integers/
 * Author   : Wang Meng
 * Date     : 2016-03-19
 * Divide two integers without using multiplication, division and mod operator.
 * <p>
 * If it is overflow, return MAX_INT.
 */
public class DivideTwoIntegers {
    public int divide(int dividend, int divisor) {
        int sign = 1;
        if (dividend < 0 && divisor > 0 || dividend > 0 && divisor < 0) sign = -1;
        long dd = Math.abs((long) dividend);
        long ds = Math.abs((long) divisor);
        long ret = 0;
        while (dd >= ds) {
            long dc = ds;
            long cret = 1;
            while ((dc << 1) < dd) {
                dc <<= 1;
                cret <<= 1;
            }
            ret += cret;
            dd -= dc;
        }
        ret = (sign > 0) ? ret : -ret;
        if (ret > Integer.MAX_VALUE || ret < Integer.MIN_VALUE) return Integer.MAX_VALUE;
        else return (int) ret;
    }
}
