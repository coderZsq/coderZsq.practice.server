package 栈;

import java.util.Stack;

/**
 * https://leetcode-cn.com/problems/evaluate-reverse-polish-notation/
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
        Stack<Integer> stack = new Stack<Integer>();
        for (String token : tokens) {
            switch (token) {
                case "+":
                    stack.push(stack.pop() + stack.pop());
                    break;
                case "*":
                    stack.push(stack.pop() * stack.pop());
                    break;
                case "-":
                    stack.push(-stack.pop() + stack.pop());
                    break;
                case "/":
                    Integer right = stack.pop();
                    stack.push(stack.pop() / right);
                    break;
                default:
                    stack.push(Integer.parseInt(token));
                    break;
            }
        }
        return stack.pop();
    }

    private  boolean isOperator(String token) {
        return "+-*/".contains(token);
    }

    private int calculate(Integer right, Integer left, String operator) {
        switch (operator) {
            case "+": return left + right;
            case "-": return left - right;
            case "*": return left * right;
            default: return left / right;
        }
    }

    public int evalRPN1(String[] tokens) {
        Stack<Integer> stack = new Stack<Integer>();
        for (String token : tokens) {
            stack.push(isOperator(token)
                    ? calculate(stack.pop(), stack.pop(), token)
                    : Integer.parseInt(token));
        }
        return stack.pop();
    }

    public int evalRPN2(String[] tokens) {
        Stack<Integer> stack = new Stack<Integer>();
        for (String token : tokens) {
            if (isOperator(token)) {
                Integer right = stack.pop();
                Integer left = stack.pop();
                stack.push(calculate(left, right, token));
            } else {
                stack.push(Integer.parseInt(token));
            }
        }
        return stack.pop();
    }

    public int evalRPN3(String[] tokens) {
        Stack<Integer> stack = new Stack<Integer>();
        for (String token : tokens) {
            if (isOperator(token)) {
                Integer left = stack.pop();
                Integer right = stack.pop();
                stack.push(calculate(left, right, token));
            } else {
                stack.push(Integer.parseInt(token));
            }
        }
        return stack.pop();
    }
}
