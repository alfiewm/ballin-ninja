/**
 * Source   : https://leetcode.com/problems/reverse-linked-list/
 * Author   : Wang Meng
 * Date     : 2016-03-21
 * <p>
 * Reverse a singly linked list.
 */
public class ReverseLinkedList {
    public ListNode reverseList(ListNode head) {
        if (head == null) return null;
        ListNode pre = null;
        ListNode cur = head;
        ListNode next;
        while (cur != null) {
            next = cur.next;
            cur.next = pre;
            pre = cur;
            cur = next;
        }
        return pre;
    }
}
