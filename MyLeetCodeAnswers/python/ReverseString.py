#!/usr/bin/python
# -*- coding: UTF-8 -*-
#Write a function that takes a string as input and returns the string reversed.
#Example:
#Given s = "hello", return "olleh".
class Solution(object):
    def reverseString(self, s):
        """
        :type s: str
        :rtype: str
        """
        return s[::-1]

#test cases
s = Solution()
print s.reverseString("Hello")
print s.reverseString("")
print s.reverseString("Hello Yo")
print s.reverseString("H")
print s.reverseString("HH")
print s.reverseString("Ha")
