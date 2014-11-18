// Source   : https://oj.leetcode.com/problems/sort-list/
// Authoer  : Wang Meng
// Date     : 2014-11-18

/*
 * Sort a linked list in O(n log n) time using constant space complexity.
 */

// My merge sort solution, AC
class Solution {
public:
    ListNode *sortList(ListNode *head) {
        if (head == NULL || head->next == NULL) return head;
        ListNode *slow = head;
        ListNode *fast = head->next;
        while (fast != NULL && fast->next != NULL) {
            slow = slow->next;
            fast = fast->next->next;
        }
        fast = slow->next;
        slow->next = NULL;
        slow = sortList(head);
        fast = sortList(fast);
        return merge(slow, fast);
    }
    
    ListNode *merge(ListNode *fh, ListNode *sh) {
        if (fh == NULL) return sh;
        if (sh == NULL) return fh;
        ListNode dummy(0);
        ListNode *p = &dummy;
        while (fh != NULL && sh != NULL) {
            if (fh->val <= sh->val) {
                p->next = fh;
                fh = fh->next;
                p = p->next;
                p->next = NULL;
            } else {
                p->next = sh;
                sh = sh->next;
                p = p->next;
                p->next = NULL;
            }
        }
        if (fh != NULL) p->next = fh;
        if (sh != NULL) p->next = sh;
        return dummy.next;
    }
};

// My quick sort solution, TLE %>_<%
class Solution {
public:
    ListNode *sortList(ListNode *head) {
        if (head == NULL || head->next == NULL) return head;
        ListNode smaller(0);
        ListNode bigger(0);
        ListNode *s = &smaller;
        ListNode *b = &bigger;
        ListNode *p = head->next;
        while (p != NULL) {
            if (p->val <= head->val) {
                s->next = p;
                s = p;
                p = p->next;
                s->next = NULL;
            } else {
                b->next = p;
                b = p;
                p = p->next;
                b->next = NULL;
            }
        }
        s = sortList(smaller.next);
        if (s!= NULL) {
            p = s;
            while (p->next != NULL) p = p->next;
            p->next = head;
        }
        b = sortList(bigger.next);
        head->next = b;
        return s;
    }
};