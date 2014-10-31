/**
 * Definition for singly-linked list.
 * struct ListNode {
 *     int val;
 *     ListNode *next;
 *     ListNode(int x) : val(x), next(NULL) {}
 * };
 */
/*双指针解法*/
class Solution {
public:
    ListNode *removeNthFromEnd(ListNode *head, int n) {
        ListNode dummy(0);
        dummy.next = head;
        ListNode *pre = &dummy;
        ListNode *p = &dummy;
        while (n--) {
            p = p->next;
        }
        while (p->next) {
            pre=pre->next;
            p = p->next;
        }
        pre->next = pre->next->next;
        return dummy.next;
    }
};

 /* My Solution, 不太好，使用了大量额外空间*/
class Solution {
public:
    ListNode *removeNthFromEnd(ListNode *head, int n) {
        stack<ListNode*> sta;
        ListNode *p = head;
        while (p) sta.push(p), p=p->next;
        int c = 0;
        while (!sta.empty()) {
            c++;
            if (c == n) {
                sta.pop();
                continue;
            } else {
                sta.top()->next = p;
                p = sta.top();
                sta.pop();
            }
        }
        return p;
    }
};