#include <iostream>
#include <set>
using namespace std;

struct ListNode {
    int val;
    ListNode *next;
    ListNode(int x) : val(x), next(NULL) {}
};

/*space O(1) solution, 利用两个指针判断是否有cycle后的状态，( ⊙ o ⊙ )啊！*/
/*my solution is like this: using two pointers, one of them one step at a time. another pointer each take two steps. Suppose the first meet at step k,the length of the Cycle is r. so..2k-k=nr,k=nr Now, the distance between the start node of list and the start node of cycle is s. the distance between the start of list and the first meeting node is k(the pointer which wake one step at a time waked k steps).the distance between the start node of cycle and the first meeting node is m, so...s=k-m, s=nr-m=(n-1)r+(r-m),here we takes n = 1..so, using one pointer start from the start node of list, another pointer start from the first meeting node, all of them wake one step at a time, the first time they meeting each other is the start of the cycle.*/
class Solution3 {
public:
    ListNode *detectCycle(ListNode *head) {
        if (head == NULL) return head;
        // jude if has cycle
        ListNode *slow = head;
        ListNode *fast = head;
        bool isCycle = false;
        while (fast != NULL) {
            slow = slow->next;
            if (fast->next == NULL) return NULL;
            fast = fast->next->next;
            if (fast == slow) {isCycle = true;break;}
        }
        if (!isCycle) return NULL;
        // check where do the cycle start
        slow = head;
        while (slow != fast) {
            slow = slow->next;
            fast = fast->next;
        }
        return slow;
    }
};


/*space O(1) solution， 思路：拆散List，记得首先判断是否有cycle，他妹的，不允许拆散*/
class Solution2 {
public:
    ListNode *detectCycle(ListNode *head) {
        if (head == NULL) return head;
        // jude if has cycle
        ListNode *slow = head;
        ListNode *fast = head->next;
        while (fast != NULL && fast->next != NULL && fast != slow) {
            slow = slow->next;
            fast = fast->next->next;
        }
        if (fast != slow) return NULL;
        // check where do the cycle start
        ListNode *p = NULL;
        while (head->next != NULL) {
            p = head;
            head = head->next;
            p->next = NULL;
        }
        return head;
    }
};
 /*space O(n) solution*/
class Solution {
public:
    ListNode *detectCycle(ListNode *head) {
        set<ListNode*> elements;
        while (head != NULL) {
            if (elements.find(head) != elements.end()) {
                return head;
            } else {
                elements.insert(head);
                head = head->next;
            }
        }
        return NULL;
    }
};

int main() {
    ListNode *l1 = new ListNode(3);
    ListNode *l2 = new ListNode(2);
    ListNode *l3 = new ListNode(0);
    ListNode *l4 = new ListNode(-4);

    l1->next=l2;
    l2->next=l3;
    l3->next=l4;
    l4->next=l3;

    Solution s1;
    Solution2 s2;
    Solution3 s3;
    ListNode *result = s1.detectCycle(l1);
    cout << result->val << endl;
    result = s3.detectCycle(l1);
    cout << result->val << endl;
    result = s2.detectCycle(l1);
    cout << result->val << endl;
    return 0;
}
