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
    ListNode *swapPairs(ListNode *head) {
        if (head == NULL || head->next == NULL) return head;
        ListNode *newHead = head->next;
        ListNode *pre = NULL;
        ListNode *cur = head;
        ListNode *ne = NULL;
        while (cur != NULL && cur->next != NULL) {
            ne = cur->next->next;
            if (pre != NULL) {
                pre->next = cur->next;
            }
            cur->next->next = cur;
            cur->next = ne;
            pre = cur;
            cur = ne;
        }
        return newHead;
    }
};

/*可以设置一个假头，辅助遍历！*/
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
    ListNode *swapPairs(ListNode *head) {
        if (head == NULL || head->next == NULL) return head;
        ListNode *dummy = new ListNode(-1);
        dummy->next = head;
        ListNode *pre = dummy;
        ListNode *cur = head;
        ListNode *ne = NULL;
        while (cur != NULL && cur->next != NULL) {
            ne = cur->next->next;
            pre->next = cur->next;
            cur->next->next = cur;
            cur->next = ne;
            pre = cur;
            cur = ne;
        }
        return dummy->next;
    }
};