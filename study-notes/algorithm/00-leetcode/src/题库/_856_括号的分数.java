package 题库;

import java.util.Stack;

/**
 * https://leetcode-cn.com/problems/score-of-parentheses/
 *
 * @author zhushuangquan
 */
public class _856_括号的分数 {
    public static int scoreOfParentheses(String S) {
        if (S == null || S.length() == 0) return 0;
        char[] chars = S.toCharArray();
        // 定义一个左括号的标记
        boolean bracket = true;
        int sum = 0;
        Stack stack = new Stack();
        for (int i = 0; i < chars.length; i++) {
            char c = chars[i];
            if (c == '(') {
                stack.push(c);
                bracket = true;
            } else {
                if (bracket) {
                    sum += Math.pow(2, stack.size() - 1);
                    bracket = false;
                }
                stack.pop();
            }
        }
        return sum;
    }
}
