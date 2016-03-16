/**
 * Source   : https://leetcode.com/problems/add-two-numbers/
 * Author   : Wang Meng
 * Date     : 2016-03-15
 * You are given two linked lists representing two non-negative numbers. The digits are stored in reverse order and each of their nodes contain a single digit. Add the two numbers and return it as a linked list.
 * <p>
 * Input: (2 -> 4 -> 3) + (5 -> 6 -> 4)
 * Output: 7 -> 0 -> 8
 */
public class AddTwoNumbers {
    public ListNode addTwoNumbers(ListNode l1, ListNode l2) {
        ListNode dummyHead = new ListNode(0);
        ListNode p = dummyHead;
        int up = 0;
        while (l1 != null && l2 != null) {
            ListNode newNode = new ListNode(0);
            newNode.val = (l1.val + l2.val + up) % 10;
            p.next = newNode;
            p = newNode;
            up = (l1.val + l2.val + up) / 10;
            l1 = l1.next;
            l2 = l2.next;
        }
        if (l1 != null) {
            while (l1 != null) {
                int sum = l1.val + up;
                l1.val = sum % 10;
                up = sum / 10;
                p.next = l1;
                p = l1;
                l1 = l1.next;
            }
        }
        if (l2 != null) {
            while (l2 != null) {
                int sum = l2.val + up;
                l2.val = sum % 10;
                up = sum / 10;
                p.next = l2;
                p = l2;
                l2 = l2.next;
            }
        }
        if (up > 0) {
            p.next = new ListNode(1);
        }
        return dummyHead.next;
    }
}
