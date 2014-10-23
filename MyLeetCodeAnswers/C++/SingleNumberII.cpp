// Source : https://oj.leetcode.com/problems/single-number-ii/
// Author : Meng Wang
// Date   : 2014-10-23

/**********************************************************************************
*
* Given an array of integers, every element appears three times except for one. Find that single one.
*
* Note:
* Your algorithm should have a linear runtime complexity. Could you implement it without using extra memory?
*
*
**********************************************************************************/

class Solution {
public:
    int singleNumber(int A[], int n) {
        // 把所有的的数都看成是二进制的，ones 代表了出现一次的1，twos代表目前出现了2次的1，依次类推
        int ones = 0, twos = 0, threes = 0;
        for (int i = 0; i < n; i++) {
            twos |= ones & A[i];
            ones ^= A[i];// 异或3次 和 异或 1次的结果是一样的

            //对于ones 和 twos 把出现了3次的位置设置为0 （取反之后1的位置为0）
            threes = ones & twos;
            ones &= ~threes;
            twos &= ~threes;
        }
        return ones;
    }
};
