#!/usr/bin/python
# Define for singly-linked list.
class ListNode(object):
    def __init__(self, x):
        self.val = x
        self.next = None
    def __str__(self):
        p = self
        ret = ""
        while p != None:
            ret += (str(p.val) + " ")
            p = p.next
        return ret
