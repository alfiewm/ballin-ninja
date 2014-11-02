#include <iostream>
#include <string>

using namespace std;

struct ListNode {
     int val;
    ListNode *next;
    ListNode(int x) : val(x), next(NULL) {}
  };

class Solution {
public:
    ListNode *addTwoNumbers(ListNode *l1, ListNode *l2) {
        if (l1 == NULL) return l2;
        if (l2 == NULL) return l1;
        int carry = 0;
        ListNode result(0);
        ListNode *p = &result;
        while (l1 != NULL &&l2!=NULL) {
            ListNode *cur = new ListNode(0);
            p->next = cur;
            p = cur;
            
            cur->val = (l1->val + l2->val + carry) % 10;
            carry = (l1->val + l2->val + carry) / 10;
            l1=l1->next;
            l2 = l2 ->next;
        }
        
        while (l1 != NULL) {
            p->next = l1;
            p = l1;
            int val = (p->val+carry) % 10;
            // caution, don't: p->val = (p->val+carry)%10; !!!
            carry = (p->val+carry)/10;
            p->val = val;
            l1 = l1->next;
        }
        while (l2 != NULL) {
            p->next = l2;
            p = l2;
            int val = (p->val+carry) % 10;
            carry = (p->val+carry)/10;
            p->val = val;
            l2 = l2->next;
        }
        if (carry == 1) {
            ListNode *last = new ListNode(1);
            p->next = last;
        }
        return result.next;
    }
};
int main() {
    Solution s;
    ListNode first(8);
    ListNode first2(9);
    ListNode second(1);
    first2.next = &first;
    ListNode *result = s.addTwoNumbers(&first2, &second);
    while (result != NULL) {
        cout << result->val << "--";
        result= result->next;
    }
    return 0;
}
