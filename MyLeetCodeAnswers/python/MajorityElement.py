#!/usr/bin/python
# -*- coding: UTF-8 -*-

# Given an array of size n, find the majority element. The majority element is
# the element that appears more than ⌊ n/2 ⌋ times.
# You may assume that the array is non-empty and the majority element always exist in the array.

import datetime

# solution from discussion. 充分利用题目条件，假设所给数组一定存在解，将解数与其他数配对删除，剩下的数就是了，genius
class Solution3(object):
    def majorityElement(self, nums):
        """
        :type nums: List[int]
        :rtype: int
        """
        me = nums[0]
        count = 0
        for num in nums:
            if count == 0:
                count = 1
                me = num
            elif num == me:
                count = count + 1
            else:
                count = count - 1
        return me

# solution use binary search
class Solution2(object):
    def majorityElement(self, nums):
        """
        :type nums: List[int]
        :rtype: int
        """
        if nums is None:
            return None
        nums.sort()
        n = len(nums)
        for i, num in enumerate(nums):
            num = nums[i]
            low = i + 1
            high = n - 1
            while low <= high:
                mid = (low + high) / 2
                if nums[mid] == num:
                    low = mid + 1
                else:
                    high = mid - 1
            if low - i > n /2:
                return num
        return None


# first straight solution, TLE
class Solution1(object):
    def majorityElement(self, nums):
        """
        :type nums: List[int]
        :rtype: int
        """
        if nums is None:
            return None
        nums.sort()
        n = len(nums)
        for i, num in enumerate(nums):
            num = nums[i]
            count = 1
            while i+1 < n and nums[i+1] == num:
                count = count + 1
                i = i + 1
            if count > n / 2:
                return num
        return None

nums = [1,2,2]
# nums = [3,2,2,1,1,1,1]
# nums = [1,2,3,2,2,2,3,3,3,3,3,3]
# nums = [1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1]
s1 = Solution1()
s2 = Solution2()
s3 = Solution3()
print datetime.datetime.now()
print s1.majorityElement(nums)
print datetime.datetime.now()
print s2.majorityElement(nums)
print datetime.datetime.now()
print s3.majorityElement(nums)
print datetime.datetime.now()
