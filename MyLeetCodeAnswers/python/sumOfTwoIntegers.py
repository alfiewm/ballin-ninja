#!/usr/bin/python
# -*- coding: UTF-8 -*-
#Calculate the sum of two integers a and b, but you are not allowed to use the operator + and -.
#
#Example:
#Given a = 1 and b = 2, return 3.
class Solution(object):
    def getSum(self, a, b):
        """
        :type a: int
        :type b: int
        :rtype: int
        """
        MAX_INT = 0x7FFFFFFF
        MIN_INT = 0x80000000
        MASK = 0x100000000
        while b:
            a, b = (a ^ b) % MASK, ((a & b) << 1) % MASK
        return a if a <= MAX_INT else ~((a % MIN_INT) ^ MAX_INT)

# explaination
# while循环意思很清楚，异或计算不需要进位的bit，与&计算需要进位的bit，进位就是左移位
# java中整型的取值范围是-2^31~2^31-1; 而python中数值类型都是64bit，相当于java中的long，所以有一个转换

# test cases
p=Solution();
print p.getSum(1, 2);
print p.getSum(0, 1);
print p.getSum(3, 3);
print p.getSum(8, 4);
print p.getSum(1000, 24);
print p.getSum(16, 0);
print p.getSum(322, 678);
print p.getSum(-1, 0);
print p.getSum(-1, 1);
print p.getSum(0x80000000, 0x80000000);
print p.getSum(0x7FFFFFFF, 0x7FFFFFFF);
print p.getSum(0x7FFFFFFF, 0x80000000);
