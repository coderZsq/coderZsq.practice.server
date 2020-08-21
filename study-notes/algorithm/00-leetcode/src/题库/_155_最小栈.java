package 题库;

import java.util.Stack;

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

class _155_最小栈2 {
    class MinStack {
        /* 用来存放正常数据 */
        private Stack<Integer> stack;
        /* 用来存放最小数据 */
        private Stack<Integer> minStack;

        public MinStack() {
            stack = new Stack<>();
            minStack = new Stack<>();
        }

        public void push(int x) {
            stack.push(x);
            if (minStack.isEmpty()) {
                minStack.push(x);
            } else {
                minStack.push(Math.min(x, minStack.peek()));
            }
        }

        public void pop() {
            stack.pop();
            minStack.pop();
        }

        public int top() {
            return stack.peek();
        }

        public int getMin() {
            return minStack.peek();
        }
    }
}