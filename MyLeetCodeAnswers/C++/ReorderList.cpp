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
    void reorderList(ListNode *head) {
        if (head == NULL) return;
        ListNode *slow = head;
        ListNode *fast = head;
        while (fast != NULL && fast->next != NULL) {
            slow = slow->next;
            fast = fast->next->next;
        }
        ListNode *rear = slow->next;
        slow->next = NULL;

        stack<ListNode*> sta;
        while (rear != NULL) {
            sta.push(rear);
            rear=rear->next;
        }
        ListNode dummy(0);
        ListNode *pre = &dummy;
        ListNode *p = head;
        while (!sta.empty()) {
            pre->next = p;
            pre = p;
            p = p->next;
            pre->next = sta.top();
            pre = sta.top();
            sta.pop();
        }
        if (p != NULL) pre->next = p;
        else pre->next = NULL;
        head = dummy.next;
    }
};