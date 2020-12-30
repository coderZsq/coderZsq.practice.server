package 刷题.白板题;

import java.util.HashMap;
import java.util.Map;

/**
 * https://leetcode-cn.com/problems/lru-cache/
 *
 * @author zhushuangquan
 */
public class _146_LRU缓存机制 {
    static class LRUCache {
        private Map<Integer, Node> map;
        private int capacity;
        private Node first;
        private Node last;

        public LRUCache(int capacity) {
            map = new HashMap<>();
            this.capacity = capacity;
            first = new Node();
            last = new Node();
            first.next = last;
            last.prev = first;
        }

        public int get(int key) {
            Node node = map.get(key);
            if (node == null) return -1;
            removeNode(node);
            addAfterFirst(node);
            return node.value;
        }

        private void addAfterFirst(Node node) {
            first.next.prev = node;
            node.next = first.next;

            first.next = node;
            node.prev = first;
        }

        private void removeNode(Node node) {
            node.prev.next = node.next;
            node.next.prev = node.prev;
        }

        public void put(int key, int value) {
            Node node = map.get(key);
            if (node != null) {
                node.value = value;
                removeNode(node);
            } else {
                if (map.size() == capacity) {
                    removeNode(map.remove(last.prev.key));
                }
                map.put(key, node = new Node(key, value));
            }
            addAfterFirst(node);
        }

        static class Node {
            private int key;
            private int value;
            private Node prev;
            private Node next;

            public Node(int key, int value) {
                this.key = key;
                this.value = value;
            }

            public Node() {
            }
        }
    }
}
