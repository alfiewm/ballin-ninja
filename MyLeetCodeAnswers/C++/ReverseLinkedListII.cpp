// Source   : https://oj.leetcode.com/problems/reverse-linked-list-ii/
// Authoer  : Wang Meng
// Date     : 2014-11-08

/*
 * Reverse a linked list from position m to n. Do it in-place and in one-pass.
 * 
 * For example:
 * Given 1->2->3->4->5->NULL, m = 2 and n = 4,
 * 
 * return 1->4->3->2->5->NULL.
 * 
 * Note:
 * Given m, n satisfy the following condition:
 * 1 ≤ m ≤ n ≤ length of list.
 */

#include "Solution.h"

// my solution, 傻逼了，第一次计算n的时候放到了m被修改后，真是2！
class Solution {
public:
    ListNode *reverseBetween(ListNode *head, int m, int n) {
        ListNode dummy(0);
        dummy.next = head;
        ListNode *pre = &dummy;
        ListNode *p = head;
        n = n-m+1;
        while (--m) {
            pre = p;
            p = p->next;
        }
        ListNode *rtail = p;
        ListNode *tmp = NULL;
        ListNode *rpre = NULL;
        while (n--) {
            tmp = p->next;
            p->next = rpre;
            rpre = p;
            p = tmp;
        }
        rtail->next = p;
        pre->next = rpre;
        return dummy.next;
    }
};

int main() {
    Solution s;
    ListNode *head = new ListNode(5);
    s.reverseBetween(head, 1, 1);
    return 0;
}
