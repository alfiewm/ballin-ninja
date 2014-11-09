// Source   : https://oj.leetcode.com/problems/partition-list/
// Authoer  : Wang Meng
// Date     : 2014-11-09

/*
 * Given a linked list and a value x, partition it such that all nodes less than x come before nodes greater than or equal to x.
 * 
 * You should preserve the original relative order of the nodes in each of the two partitions.
 * 
 * For example,
 * Given 1->4->3->2->5->2 and x = 3,
 * return 1->2->2->4->3->5.
 */

// 仿chen hao的解决分方案，更简洁利索
class Solution {
public:
    ListNode *partition(ListNode *head, int x) {
        if (head == NULL || head->next == NULL) return head;
        ListNode dummy(0);
        dummy.next = head;
        head = &dummy;
        ListNode *frontLine = NULL; // pointing to the node who's next.val >= x
        for (ListNode *p = head; p != NULL && p->next != NULL;) {
            if (frontLine == NULL && p->next->val >= x) {
                frontLine = p;
            } else if (frontLine != NULL && p->next->val < x) {
                ListNode *pNext = p->next;
                p->next = pNext->next;
                pNext->next = frontLine->next;
                frontLine->next=pNext;
                frontLine = pNext;
                continue;
            }
            p = p->next;
        }
        return dummy.next;
    }
};

// My solution
class Solution {
public:
    ListNode *partition(ListNode *head, int x) {
        if (head == NULL || head->next == NULL) return head;
        ListNode dummy(0);
        dummy.next = head;
        ListNode *pre = &dummy;
        ListNode *cur = head;
        ListNode *frontLine = NULL; // pointing to the first node who's next.val >= x
        while (cur != NULL) {
            if (cur->val >= x) {
                if (frontLine == NULL) { //第一遍这里忘记写了
                    frontLine = pre;
                }
                pre = cur;
                cur = cur->next;
            } else {
                if (frontLine != NULL) {
                    pre->next = cur->next;
                    cur->next = frontLine->next;
                    frontLine->next = cur;
                    frontLine = cur;
                    cur = pre->next;
                } else {
                    // 第一遍提交这里也忘记写了。。。
                    pre = cur;
                    cur = cur->next;
                }
            }
        }
        return dummy.next;
    }
};