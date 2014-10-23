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
        // �����еĵ����������Ƕ����Ƶģ�ones �����˳���һ�ε�1��twos����Ŀǰ������2�ε�1����������
        int ones = 0, twos = 0, threes = 0;
        for (int i = 0; i < n; i++) {
            twos |= ones & A[i];
            ones ^= A[i];// ���3�� �� ��� 1�εĽ����һ����

            //����ones �� twos �ѳ�����3�ε�λ������Ϊ0 ��ȡ��֮��1��λ��Ϊ0��
            threes = ones & twos;
            ones &= ~threes;
            twos &= ~threes;
        }
        return ones;
    }
};
