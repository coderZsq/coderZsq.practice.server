package 刷题.待面试;

import java.util.HashMap;
import java.util.Map;
import java.util.Stack;

/**
 * https://leetcode-cn.com/problems/valid-parentheses/
 *
 * @author zhushuangquan
 */
public class _13_20_有效的括号 {
    private static Map<Character, Character> map = new HashMap<>();

    static {
        map.put('(', ')');
        map.put('[', ']');
        map.put('{', '}');
    }

    public boolean isValid(String s) {
        if (s == null || s.length() == 0) return false;
        char[] chars = s.toCharArray();
        Stack<Character> stack = new Stack<>();
        for (int i = 0; i < chars.length; i++) {
            char c = chars[i];
            if (map.containsKey(c)) {
                stack.push(c);
            } else {
                if (stack.isEmpty()) return false;
                char left = stack.pop();
                if (map.get(left) != c) return false;
            }
        }
        return stack.isEmpty();
    }
}