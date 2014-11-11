// Source   : https://oj.leetcode.com/problems/insertion-sort-list/
// Authoer  : Wang Meng
// Date     : 2014-11-11

/*
 * Sort a linked list using insertion sort.
 */
// My solution
class Solution {
public:
    ListNode *insertionSortList(ListNode *head) {
        if (head == NULL || head->next == NULL) return head;
        ListNode dummy(0);
        dummy.next = head;
        ListNode *pre = head;
        ListNode *p = head->next;
        while (p != NULL) {
            ListNode *q = &dummy;
            while (q->next->val < p->val) {
                q = q->next;
            }
            if (q->next != p) {
                pre->next = p->next;
                p->next = q->next;
                q->next = p;
                p = pre->next;
            } else {
                pre = p;
                p = p->next;
            }
        }
        return dummy.next;
    }
};