#!/usr/bin/python
# -*- coding: UTF-8 -*-
# Reverse a singly linked list.

# Definition for singly-linked list.
# class ListNode(object):
#     def __init__(self, x):
#         self.val = x
#         self.next = None

from leetcommon import ListNode

class Solution(object):
    def reverseList(self, head):
        """
        :type head: ListNode
        :rtype: ListNode
        """
        pre = None
        cur = head
        while cur != None:
            nextp = cur.next
            cur.next = pre
            pre = cur
            cur = nextp
        return pre

head = None
#head = ListNode(1)
#head.next = ListNode(2)
#head.next.next = ListNode(3)
s = Solution()
print s.reverseList(head)
