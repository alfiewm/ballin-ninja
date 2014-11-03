/**
 * Definition for singly-linked list.
 * struct ListNode {
 *     int val;
 *     ListNode *next;
 *     ListNode(int x) : val(x), next(NULL) {}
 * };
 */
class Solution {
public:
    ListNode *deleteDuplicates(ListNode *head) {
        ListNode dummy(0);
        dummy.next = NULL;
        ListNode *pre = &dummy;
        ListNode *cur = head;
        while (cur != NULL) {
            int num = cur->val;
            if (cur->next != NULL && cur->next->val == num) {
                while (cur != NULL && cur->val == num) {
                    cur = cur->next;
                }
            } else {
                pre->next = cur;
                pre = cur;
                cur = cur->next;
                pre->next = NULL;
            }
        }
        return dummy.next;
    }
};