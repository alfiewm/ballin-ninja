import java.util.Comparator;
import java.util.PriorityQueue;

/**
 * Source   : https://leetcode.com/problems/merge-two-sorted-lists/
 * Author   : Wang Meng
 * Date     : 2016-03-19
 * <p>
 * Merge k sorted linked lists and return it as one sorted list. Analyze and describe its complexity.
 */
public class MergeKSortedLists {
    public ListNode mergeKLists(ListNode[] lists) {
        if (lists == null || lists.length == 0) return null;
        if (lists.length == 1) return lists[0];
        heapInit(lists);
        ListNode dummyHead = new ListNode(0);
        ListNode p = dummyHead;
        while (lists[0] != null) {
            p.next = lists[0];
            p = p.next;
            lists[0] = lists[0].next;
            heaplize(lists, 0);
        }
        return dummyHead.next;
    }

    private void heaplize(ListNode[] lists, int pos) {
        int i = pos;
        int lchild = i * 2 + 1;
        while (lchild < lists.length) {
            ListNode lNode = lists[lchild];
            ListNode rNode = null;
            if (lchild + 1 < lists.length) {
                rNode = lists[lchild + 1];
            }
            if (lNode == null && rNode == null) {
                return;
            } else if (lNode == null) {
                if (lists[i] == null || lists[i].val > rNode.val) {
                    lists[lchild + 1] = lists[i];
                    lists[i] = rNode;
                    i = lchild + 1;
                } else {
                    return;
                }
            } else if (rNode == null) {
                if (lists[i] == null || lists[i].val > lNode.val) {
                    lists[lchild] = lists[i];
                    lists[i] = lNode;
                    i = lchild;
                } else {
                    return;
                }
            } else {
                int minIndex = (lNode.val <= rNode.val) ? lchild : (lchild + 1);
                if (lists[i] == null || lists[i].val > lists[minIndex].val) {
                    ListNode tmp = lists[i];
                    lists[i] = lists[minIndex];
                    lists[minIndex] = tmp;
                    i = minIndex;
                } else {
                    return;
                }
            }
            lchild = i * 2 + 1;
        }
    }

    private void heapInit(ListNode[] lists) {
        for (int i = (lists.length - 2) / 2; i >= 0; --i) {
            heaplize(lists, i);
        }
    }

    // Using PriorityQueue
    public ListNode mergeKLists2(ListNode[] lists) {
        if (lists == null || lists.length == 0) return null;
        PriorityQueue<ListNode> queue = new PriorityQueue<>(lists.length, new Comparator<ListNode>() {
            @Override
            public int compare(ListNode o1, ListNode o2) {
                return o1.val - o2.val;
            }
        });
        for (ListNode node : lists) {
            if (node != null) {
                queue.offer(node);
            }
        }
        ListNode dummyHead = new ListNode(0);
        ListNode p = dummyHead;
        while (!queue.isEmpty()) {
            p.next = queue.poll();
            p = p.next;
            if (p.next != null) {
                queue.offer(p.next);
            }
        }
        return dummyHead.next;
    }
}
