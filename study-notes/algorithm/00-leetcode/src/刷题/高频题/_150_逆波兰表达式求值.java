package 刷题.高频题;

import java.util.Stack;

/**
 * https://leetcode-cn.com/problems/evaluate-reverse-polish-notation/
 *
 * @author zhushuangquan
 */
public class _150_逆波兰表达式求值 {

    /*
     * 四则运算的表达式可以分为3种
     * 前缀表达式 (prefix expression), 又称为波兰表达式
     *   + 1 2
     *   + 2 * 3 4
     *   + 9 * - 4 1 2
     * 中缀表达式 (infix expression)
     *   1 + 2
     *   2 + 3 * 4
     *   9 + (4 - 1) * 2
     * 后缀表达式 (postfix expression), 又称为逆波兰表达式
     *   1 2 +
     *   2 3 4 * +
     *   9 4 1 - 2 * +
     * */

    public int evalRPN(String[] tokens) {
        // 2. 定义一个栈来保存遇到操作符之前的数字
        Stack<Integer> stack = new Stack<>();
        // 1. 遍历每个字符串
        for (String token : tokens) {
            switch (token) {
                // 3. 遇到操作符 将栈中前两项数字进行操作压入栈
                case "+":
                    stack.push(stack.pop() + stack.pop());
                    break;
                case "-":
                    stack.push(-stack.pop() + stack.pop());
                    break;
                case "*":
                    stack.push(stack.pop() * stack.pop());
                    break;
                case "/":
                    Integer right = stack.pop();
                    stack.push(stack.pop() / right);
                    break;
                default:
                    // 4. 将数字压入栈中
                    stack.push(Integer.parseInt(token));
            }
        }
        // 5. 栈中的最后的数为最后答案
        return stack.pop();
    }
}

