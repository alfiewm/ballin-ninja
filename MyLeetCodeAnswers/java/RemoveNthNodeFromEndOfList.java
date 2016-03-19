/**
 * Source   : https://leetcode.com/problems/remove-nth-node-from-end-of-list/
 * Author   : Wang Meng
 * Date     : 2016-03-18
 * Given a linked list, remove the nth node from the end of list and return its head.
 * <p>
 * For example,
 * <p>
 * Given linked list: 1->2->3->4->5, and n = 2.
 * <p>
 * After removing the second node from the end, the linked list becomes 1->2->3->5.
 * Note:
 * Given n will always be valid.
 * Try to do this in one pass.
 */
public class RemoveNthNodeFromEndOfList {
    public ListNode removeNthFromEnd(ListNode head, int n) {
        if (head == null || head.next == null) return null;
        ListNode p1 = head;
        ListNode p2 = head;
        while (n >= 0 && p2 != null) {
            p2 = p2.next;
            n--;
        }
        if (n >= 0) {
            return head.next;
        }
        while (p2 != null) {
            p1 = p1.next;
            p2 = p2.next;
        }
        if (p1 != null) {
            p1.next = p1.next.next;
            return head;
        } else {
            return head.next;
        }
    }

    public ListNode removeNthFromEnd2(ListNode head, int n) {
        ListNode dummyHead = new ListNode(0);
        dummyHead.next = head;
        while (n > 0) {
            head = head.next;
            n--;
        }
        ListNode p = dummyHead;
        while (head != null) {
            head = head.next;
            p = p.next;
        }
        p.next = p.next.next;
        return dummyHead.next;
    }
}
