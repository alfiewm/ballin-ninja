/**
 * Source   : https://leetcode.com/problems/number-of-1-bits/
 * Author   : Wang Meng
 * Date     : 2016-03-16
 * <p>
 * Write a function that takes an unsigned integer and returns the number of ’1' bits it has (also known as the Hamming weight).
 * <p>
 * For example, the 32-bit integer ’11' has binary representation 00000000000000000000000000001011, so the function should return 3.
 */
public class NumberOf1Bits {
    // you need to treat n as an unsigned value
    public int hammingWeight(int n) {
        int count = 0;
        for (int i = 0; i < Integer.SIZE; ++i) {
            if ((n & 1) == 1) {
                count++;
            }
            n >>= 1;
        }
        return count;
    }

    // https://leetcode.com/discuss/30605/simple-java-solution-bit-shifting
    // <<< 操作符会移动符号位,补零,草
    public int hammingWeight2(int n) {
        int count = 0;
        while (n != 0) {
            count += (n & 1);
            n >>>= 1;
        }
        return count;
    }

    public static void main(String[] args) {
        System.out.println(new NumberOf1Bits().hammingWeight(-1));
    }
}
