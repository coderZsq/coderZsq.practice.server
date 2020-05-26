package 刷题.高频题;

import java.util.Map;

/**
 * 设计一个支持 push ，pop ，top 操作，并能在常数时间内检索到最小元素的栈。
 *
 * push(x) —— 将元素 x 推入栈中。
 * pop() —— 删除栈顶的元素。
 * top() —— 获取栈顶元素。
 * getMin() —— 检索栈中的最小元素。
 *  
 *
 * 示例:
 *
 * 输入：
 * ["MinStack","push","push","push","getMin","pop","top","getMin"]
 * [[],[-2],[0],[-3],[],[],[],[]]
 *
 * 输出：
 * [null,null,null,null,-3,null,0,-2]
 *
 * 解释：
 * MinStack minStack = new MinStack();
 * minStack.push(-2);
 * minStack.push(0);
 * minStack.push(-3);
 * minStack.getMin();   --> 返回 -3.
 * minStack.pop();
 * minStack.top();      --> 返回 0.
 * minStack.getMin();   --> 返回 -2.
 *  
 *
 * 提示：
 *
 * pop、top 和 getMin 操作总是在 非空栈 上调用。
 *
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/min-stack
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 */
public class _155_最小栈 {
    class MinStack {
        // 2. 定义虚拟头结点
        private Node head;

        // 6. 初始化虚拟头结点 注意值为最大值 用于比较
        public MinStack() {
            head = new Node(0, Integer.MAX_VALUE, null);
        }

        // 7. 入栈 插入新的头节点
        public void push(int x) {
            head = new Node(x, Math.min(x, head.min), head);
        }

        // 5. 出栈 舍弃当前头节点
        public void pop() {
            head = head.next;
        }

        // 3. 获取栈顶取链表头节点的值
        public int top() {
            return head.val;
        }

        // 4. 获取栈顶取链表头节点最小值
        public int getMin() {
            return head.min;
        }

        // 1. 创建链表节点类, 同时存储值和最小值
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
