package 设计;

import java.util.HashMap;
import java.util.Map;

/**
 * https://leetcode-cn.com/problems/lru-cache/
 *
 * @author zhushuangquan
 */
public class _146_LRU缓存机制 {
    public static void main(String[] args) {
        LRUCache cache = new LRUCache(2 /* 缓存容量 */);
        cache.put(1, 1);
        cache.put(2, 2);
        Asserts.test(cache.get(1) == 1); // 返回 1
        cache.put(3, 3); // 该操作会使得密钥 2 作废
        Asserts.test(cache.get(2) == -1); // 返回 -1 (未找到)
        cache.put(4, 4); // 该操作会使得密钥 1 作废
        Asserts.test(cache.get(1) == -1); // 返回 -1 (未找到)
        Asserts.test(cache.get(3) == 3); // 返回 3
        Asserts.test(cache.get(4) == 4); // 返回 4
    }

    static class LRUCache {
        private Map<Integer, Node> map;
        private int capacity;
        // 虚拟头结点
        private Node first;
        // 虚拟尾结点
        private Node last;

        public LRUCache(int capacity) {
            map = new HashMap<>(capacity);
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

        /**
         * @param node 将node节点插入到first节点的后面
         */
        private void addAfterFirst(Node node) {
            // node与first.next
            node.next = first.next;
            first.next.prev = node;

            // node与first
            first.next = node;
            node.prev = first;
        }

        /**
         * @param node 从双向链表中删除node节点
         */
        private void removeNode(Node node) {
            node.next.prev = node.prev;
            node.prev.next = node.next;
        }

        public void put(int key, int value) {
            Node node = map.get(key);
            if (node != null) {
                node.value = value;
                removeNode(node);
            } else { // 添加一对新的key-value
                if (map.size() == capacity) {
                    // 淘汰最近最少使用的node
                    removeNode(map.remove(last.prev.key));
                    // map.remove(last.prev.key);
                    // removeNode(last.prev);
                }
                map.put(key, node = new Node(key, value));
            }
            addAfterFirst(node);
        }

        private static class Node {
            public Node prev;
            public Node next;
            private int key;
            private int value;

            public Node(int key, int value) {
                this.key = key;
                this.value = value;
            }

            public Node() {
            }
        }
    }
}
