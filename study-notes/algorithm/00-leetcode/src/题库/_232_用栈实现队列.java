package 题库;

import java.util.Stack;

/**
 * https://leetcode-cn.com/problems/implement-queue-using-stacks/
 *
 * @author zhushuangquan
 */
public class _232_用栈实现队列 {
    private Stack<Integer> inStack;
    private Stack<Integer> outStack;

    public _232_用栈实现队列() {
        inStack = new Stack<>();
        outStack = new Stack<>();
    }

    /**
     * 入队
     */
    public void push(int x) {
        inStack.push(x);
    }

    /**
     * 出队
     */
    public int pop() {
        checkOutStack();
        return outStack.pop();
    }

    /**
     * 获取队头元素
     */
    public int peek() {
        checkOutStack();
        return outStack.peek();
    }

    /**
     * 是否为空
     */
    public boolean empty() {
        return inStack.isEmpty() && outStack.isEmpty();
    }

    private void checkOutStack() {
        if (outStack.isEmpty()) {
            while (!inStack.isEmpty()) {
                outStack.push(inStack.pop());
            }
        }
    }
}
