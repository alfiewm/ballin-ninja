/**
 * Source   : https://leetcode.com/problems/merge-two-sorted-lists/
 * Author   : Wang Meng
 * Date     : 2016-03-19
 * <p>
 * Given a linked list, swap every two adjacent nodes and return its head.
 * <p>
 * For example,
 * Given 1->2->3->4, you should return the list as 2->1->4->3.
 * <p>
 * Your algorithm should use only constant space. You may not modify the values in the list, only nodes itself can be changed.
 */
public class SwapNodesInPairs {
    // recursive solution
    public ListNode swapPairs(ListNode head) {
        if (head == null || head.next == null) return head;
        ListNode ret = head.next;
        ListNode p = head.next.next;
        head.next.next = head;
        head.next = swapPairs(p);
        return ret;
    }
}
