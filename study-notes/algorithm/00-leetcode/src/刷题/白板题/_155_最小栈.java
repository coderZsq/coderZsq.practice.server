package 刷题.白板题;

/**
 * https://leetcode-cn.com/problems/min-stack/
 *
 * @author zhushuangquan
 */
public class _155_最小栈 {
    class MinStack {
        private Node head;

        public MinStack() {
            head = new Node(0, Integer.MAX_VALUE, null);
        }

        public void push(int x) {
            head = new Node(x, Math.min(x, head.min), head);
        }

        public void pop() {
            head = head.next;
        }

        public int top() {
            return head.val;
        }

        public int getMin() {
            return head.min;
        }

        private class Node {
            int val;
            int min;
            Node next;

            public Node(int val, int min, Node next) {
                this.val = val;
                this.min = min;
                this.next = next;
            }
        }
    }
}