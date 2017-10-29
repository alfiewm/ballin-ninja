#!/usr/bin/python
# -*- coding: UTF-8 -*-
#Write a program to find the node at which the intersection of two singly linked lists begins.
# For example, the following two linked lists:
#
# A:          a1 → a2
#                    ↘
#                      c1 → c2 → c3
#                    ↗
# B:     b1 → b2 → b3
# begin to intersect at node c1.
#
#
# Notes:
#
# If the two linked lists have no intersection at all, return null.
# The linked lists must retain their original structure after the function returns.
# You may assume there are no cycles anywhere in the entire linked structure.
# Your code should preferably run in O(n) time and use only O(1) memory.

from leetcommon import ListNode

# 下面的解法通过连接A链表，使用快慢指针检查是否存在交点，然后通过快慢指针的运行路径倍数关系计算得出
# 相遇点与B链表头同步前进交汇处即为两链表相交点。
class Solution1(object):
    def getIntersectionNode(self, headA, headB):
        """
        :type head1, head1: ListNode
        :rtype: ListNode
        """
        if headA is None or headB is None:
            return None
        # check if has intersection
        pa = headA
        while pa.next != None:
            pa = pa.next
        paend = pa
        pa.next = headA
        p1 = headB
        p2 = headB
        while p1 != None and p2 != None and p2.next != None:
            p1 = p1.next
            p2 = p2.next.next
            if p1 == p2:
                break
        if p1 != p2 or headB.next is None:
            paend.next = None
            return None
        p3 = headB
        while p3 != p1:
            p1 = p1.next
            p3 = p3.next
        paend.next = None
        return p3

# 更天才的解法，from leetcode discussion
# 也是使用双指针，不过目的是两个指针走同样的距离到达链表交点，方法是A指针到达表尾后继续从B链表头开始
# 而B指针到达表尾后从A链表头开始，相交那一点即为交点，so cool! 如果走到头还没有相交，那就是没有交点。
# 下面的代码可以理解为将链表尾的None也看做一个交点，这样就不用考虑不相交的情况了，genius
class Solution2(object):
    def getIntersectionNode(self, headA, headB):
        """
        :type head1, head1: ListNode
        :rtype: ListNode
        """
        if headA is None or headB is None:
            return None
        pa = headA
        pb = headB
        while pa != pb:
            pa = headB if pa is None else pa.next
            pb = headA if pb is None else pb.next
        return pa

# headA = None
headA = ListNode(1)
headA.next = ListNode(2)
headA.next.next = ListNode(3)
headA.next.next.next = ListNode(4)
# headB = None
# headB = headA.next
headB = ListNode(11)
headB.next = headA.next.next.next
# headB.next = ListNode(12)
print headA
print headB

s = Solution2()
intersection = s.getIntersectionNode(headA, headB)
if intersection is None:
    print "no intersection"
else:
    print intersection.val

print headA
print headB
