package 标签.栈;

import java.util.Stack;

/**
 * 实现一个基本的计算器来计算一个简单的字符串表达式的值。
 * <p>
 * 字符串表达式可以包含左括号 ( ，右括号 )，加号 + ，减号 -，非负整数和空格  。
 * <p>
 * 示例 1:
 * <p>
 * 输入: "1 + 1"
 * 输出: 2
 * 示例 2:
 * <p>
 * 输入: " 2-1 + 2 "
 * 输出: 3
 * 示例 3:
 * <p>
 * 输入: "(1+(4+5+2)-3)+(6+8)"
 * 输出: 23
 * 说明：
 * <p>
 * 你可以假设所给定的表达式都是有效的。
 * 请不要使用内置的库函数 eval。
 * <p>
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/basic-calculator
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 */
public class _224_基本计算器 {
    public static int calculate(String s) {
        if (s == null || s.length() == 0) return 0;
        int len = s.length();
        char[] chars = s.toCharArray();
        int operand = 0;
        int result = 0;
        int sign = 1;
        Stack<Integer> stack = new Stack<>();
        for (int i = 0; i < len; i++) {
            char c = chars[i];
            if (Character.isDigit(c)) {
                operand = operand * 10 + (c - '0');
            } else if (c == '+') {
                result += sign * operand;
                sign = 1;
                operand = 0;
            } else if (c == '-') {
                result += sign * operand;
                sign = -1;
                operand = 0;
            } else if (c == '(') {
                stack.push(result);
                stack.push(sign);
                sign = 1;
                operand = 0;
                result = 0;
            } else if (c == ')') {
                result += sign * operand;
                result *= stack.pop();
                result += stack.pop();
                sign = 1;
                operand = 0;
            }
        }
        return result + sign * operand;
    }
}
