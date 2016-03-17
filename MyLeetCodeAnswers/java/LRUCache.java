import java.util.HashMap;
import java.util.Map;

/**
 * Source   : https://leetcode.com/problems/lru-cache/
 * Author   : Wang Meng
 * Date     : 2016-03-16
 * Design and implement a data structure for Least Recently Used (LRU) cache. It should support the following operations: get and set.
 * <p>
 * get(key) - Get the value (will always be positive) of the key if the key exists in the cache, otherwise return -1.
 * set(key, value) - Set or insert the value if the key is not already present. When the cache reached its capacity, it should invalidate the least recently used item before inserting a new item. * Created by meng on 16/3/17.
 */
public class LRUCache {
    /* 借助 LinkedList的实现,提交的时候遇到TimeLimitExceed, 看来还是要实现一个自己的双向链表
        private static class Node {
            int key;
            int value;

            Node(int key, int value) {
                this.key = key;
                this.value = value;
            }

            @Override
            public String toString() {
                return "[" + key + ", " + value + "]";
            }
        }

        private int capacity;
        private LinkedList<Node> dList;
        private Map<Integer, Node> map;

        public LRUCache(int capacity) {
            this.capacity = capacity;
            dList = new LinkedList<>();
            map = new HashMap<>();
        }

        public int get(int key) {
            if (map.containsKey(key)) {
                Node node = map.get(key);
                dList.remove(node);// 效率比较低,因为LinkedList的Node是private的,无法直接从外面将node移到头,只能遍历, maybe 自己实现一个双向链表?
                dList.offerFirst(node);
                return node.value;
            } else {
                return -1;
            }
        }

        public void set(int key, int value) {
            if (map.containsKey(key)) {
                Node node = map.get(key);
                node.value = value;
            } else {
                Node node = null;
                if (dList.size() == capacity) {
                    node = dList.pollLast();
                    map.remove(node.key, node);
                }
                if (node == null) {
                    node = new Node(key, value);
                } else {
                    node.key = key;
                    node.value = value;
                }
                dList.offerFirst(node);
                map.put(key, node);
            }
        }

        @Override
        public String toString() {
            return dList.toString();
        }*/
    private static class Node {
        int key;
        int value;
        Node prev;
        Node next;

        Node(int key, int value) {
            this.key = key;
            this.value = value;
            prev = null;
            next = null;
        }

        @Override
        public String toString() {
            return "[" + key + ", " + value + "]";
        }
    }

    private int capacity;
    private Map<Integer, Node> map;
    private Node listHead;
    private Node listTail;

    public LRUCache(int capacity) {
        this.capacity = capacity;
        map = new HashMap<>();
        listHead = null;
        listTail = null;
    }

    private void moveToHead(Node node) {
        if (node == null || node.prev == null) return;
        node.prev.next = node.next;
        if (node.next != null) {
            node.next.prev = node.prev;
        } else {
            listTail = node.prev;
        }
        node.next = listHead;
        listHead.prev = node;
        node.prev = null;
        listHead = node;
    }

    public int get(int key) {
        if (map.containsKey(key)) {
            Node node = map.get(key);
            moveToHead(node);
            return node.value;
        } else {
            return -1;
        }
    }

    public void set(int key, int value) {
        if (map.containsKey(key)) {
            Node node = map.get(key);
            node.value = value;
            moveToHead(node);
        } else {
            if (map.size() == capacity) {
                map.remove(listTail.key, listTail);
                if (listTail.prev == null) {
                    listTail = null;
                    listHead = null;
                } else {
                    listTail = listTail.prev;
                    listTail.next.prev = null;
                    listTail.next = null;
                }
            }
            Node node = new Node(key, value);
            if (listHead == null) {
                listHead = node;
                listTail = node;
            } else {
                node.next = listHead;
                listHead.prev = node;
                listHead = node;
            }
            map.put(key, node);
        }
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder("[");
        Node p = listHead;
        while (p != null) {
            sb.append(p).append(" ");
            p = p.next;
        }
        sb.append("]");
        return sb.toString();
    }

    public static void main(String[] args) {
        LRUCache lruCache = new LRUCache(2);
        lruCache.set(2, 1);
        System.out.println(lruCache);
        lruCache.set(1, 1);
        System.out.println(lruCache);
        System.out.println(lruCache.get(2));
        System.out.println(lruCache);
        lruCache.set(4, 1);
        System.out.println(lruCache);
        System.out.println(lruCache.get(1));
        System.out.println(lruCache.get(2));
        lruCache.set(1, 5);
        System.out.println(lruCache);
        lruCache.set(5, 23);
        System.out.println(lruCache);
        lruCache.set(7, 8);
        System.out.println(lruCache);
        lruCache.set(10, 1);
        System.out.println(lruCache);
        System.out.println(lruCache.get(5));
        System.out.println(lruCache);
        System.out.println(lruCache.get(9));
        System.out.println(lruCache);
    }
}
