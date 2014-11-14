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