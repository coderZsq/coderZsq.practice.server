package 题库;

/**
 * https://leetcode-cn.com/problems/string-to-integer-atoi/
 *
 * @author zhushuangquan
 */
public class _8_字符串转换整数_atoi {
    public int myAtoi(String s) {
        if (s == null || s.length() == 0) return 0;
        char[] chars = s.toCharArray();
        long num = 0;
        Boolean sign = null;
        for (int i = 0; i < chars.length; i++) {
            char c = chars[i];
            if (c >= '0' && c <= '9') {
                if (sign == null) {
                    sign = true;
                }
                if (sign) {
                    num = num * 10 + (c - '0');
                    if (num > Integer.MAX_VALUE) {
                        return Integer.MAX_VALUE;
                    }
                } else {
                    num = num * 10 - (c - '0');
                    if (num < Integer.MIN_VALUE) {
                        return Integer.MIN_VALUE;
                    }
                }
            } else if (sign != null) {
                return (int) num;
            } else if (c == '+') {
                sign = true;
            } else if (c == '-') {
                sign = false;
            } else if (c != ' ') {
                return 0;
            }
        }
        return (int) num;
    }
}
