// Source   : https://oj.leetcode.com/problems/path-sum/
// Authoer  : Wang Meng
// Date     : 2014-11-14

/*
 * Given a list, rotate the list to the right by k places, where k is non-negative.
 * 
 * For example:
 * Given 1->2->3->4->5->NULL and k = 2,
 * return 4->5->1->2->3->NULL.
*/

// Another Soluton From Discuss Area, 主要的改进是在第一次计算list长度的时候将其组成一个环，这样第二次遍历不用遍历完整的list，而且代码更优雅
ListNode *rotateRight(ListNode *head, int k) {
    if (head == NULL || head->next == NULL || k == 0) return head;
    int len = 1;
    ListNode *tail = head;

    /* find the end of list */
    while (tail->next != NULL) {
        tail = tail->next;
        len++;
    }

    /* form a circle */
    tail->next = head;
    k = k % len;
    for (int i = 0; i < len - k; i++) {
        tail = tail->next;
    }
    head = tail->next;
    tail->next = NULL;
    return head;
}


// My Solution
class Solution {
public:
    ListNode *rotateRight(ListNode *head, int k) {
        if (head == NULL || head->next == NULL || k == 0) return head;
        ListNode *slow = head;
        ListNode *fast = head;

        // count nodes number
        int count = 1;
        for (; fast->next!=NULL; fast=fast->next) count++;
        k = k%count;

        fast = head;
        while (k != 0) {
            fast=fast->next;
            k--;
        }
        while (fast->next != NULL) {
            fast = fast->next;
            slow = slow->next;
        }
        fast->next = head;
        head = slow->next;
        slow->next = NULL;
        return head;
    }
};