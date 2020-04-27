package 标签.栈;

/**
 * https://leetcode-cn.com/problems/min-stack-lcci/
 *
 * @author zhushuangquan
 */
public class 面试题_03_02_栈的最小值 {
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
            public int val;
            public int min;
            public Node next;

            public Node(int val, int min, Node next) {
                this.val = val;
                this.min = min;
                this.next = next;
            }
        }
    }
}
