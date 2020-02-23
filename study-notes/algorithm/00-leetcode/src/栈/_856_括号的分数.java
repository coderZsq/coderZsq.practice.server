package 栈;

import java.util.Stack;

/**
 * https://leetcode-cn.com/problems/score-of-parentheses/
 *
 * @author zhushuangquan
 */
public class _856_括号的分数 {
    public int scoreOfParentheses(String S) {
        Stack<Character> stack = new Stack<>();
        char[] c = S.toCharArray();
        int sum = 0;
        boolean f = true;
        for (int i = 0; i < c.length; i++) {
            if (c[i] == '(') {
                stack.add(c[i]);
                f = true;
            } else {
                if (f) {
                    sum += Math.pow(2, stack.size() - 1);
                    stack.pop();
                    f = false;
                } else {
                    stack.pop();
                }
            }
        }
        return sum;
    }
}
