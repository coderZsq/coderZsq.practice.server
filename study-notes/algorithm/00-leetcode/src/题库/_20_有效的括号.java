package 题库;

import java.util.HashMap;
import java.util.Stack;

/**
 * https://leetcode-cn.com/problems/valid-parentheses/
 *
 * @author zhushuangquan
 */
public class _20_有效的括号 {
    private static HashMap<Character, Character> map = new HashMap<>();

    static {
        // key - value
        map.put('(', ')');
        map.put('{', '}');
        map.put('[', ']');
    }

    public boolean isValid(String s) {
        Stack<Character> stack = new Stack<>();

        int len = s.length();
        for (int i = 0; i < len; i++) {
            char c = s.charAt(i);
            if (map.containsKey(c)) { // 左括号
                stack.push(c);
            } else { // 右括号
                if (stack.isEmpty()) return false;

                char left = stack.pop();
                if (c != map.get(left)) return false;
            }
        }
        return stack.isEmpty();
    }
}

class _20_有效的括号2 {
    public boolean isValid(String s) {
        Stack<Character> stack = new Stack<>();

        int len = s.length();
        for (int i = 0; i < len; i++) {
            char c = s.charAt(i);
            if (c == '(' || c == '{' || c == '[') { // 左括号
                stack.push(c);
            } else { // 右括号
                if (stack.isEmpty()) return false;

                char left = stack.pop();
                if (left == '(' && c != ')') return false;
                if (left == '{' && c != '}') return false;
                if (left == '[' && c != ']') return false;
            }
        }
        return stack.isEmpty();
    }

}

class _20_有效的括号3 {
    public boolean isValid(String s) {
        while (s.contains("{}")
                || s.contains("[]")
                || s.contains("()")) {
            s = s.replace("{}", "");
            s = s.replace("[]", "");
            s = s.replace("()", "");
        }
        return s.isEmpty();
    }
}

class _20_有效的括号4 {
    public boolean isValid(String s) {
        if (s == null || s.length() == 0) return false;
        char[] chars = new char[s.length()];
        int i = -1;
        for (char c : s.toCharArray()) {
            if (c == '(') chars[++i] = ')';
            else if (c == '[') chars[++i] = ']';
            else if (c == '{') chars[++i] = '}';
            else if (i == -1 || chars[i--] != c) return false;
        }
        return i == -1;
    }
}