package 刷题.高频题;

import org.junit.Assert;
import org.junit.Test;

import java.util.HashMap;
import java.util.Stack;

/**
 * 给定一个只包括 '('，')'，'{'，'}'，'['，']' 的字符串，判断字符串是否有效。
 * <p>
 * 有效字符串需满足：
 * <p>
 * 左括号必须用相同类型的右括号闭合。
 * 左括号必须以正确的顺序闭合。
 * 注意空字符串可被认为是有效字符串。
 * <p>
 * 示例 1:
 * <p>
 * 输入: "()"
 * 输出: true
 * 示例 2:
 * <p>
 * 输入: "()[]{}"
 * 输出: true
 * 示例 3:
 * <p>
 * 输入: "(]"
 * 输出: false
 * 示例 4:
 * <p>
 * 输入: "([)]"
 * 输出: false
 * 示例 5:
 * <p>
 * 输入: "{[]}"
 * 输出: true
 * <p>
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/valid-parentheses
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 */
public class _20_有效的括号 {
    // 1. 使用哈希表装载括号
    static HashMap<Character, Character> map = new HashMap<>();

    // 2. 初始化括号
    static {
        map.put('(', ')');
        map.put('{', '}');
        map.put('[', ']');
    }

    public boolean isValid(String s) {
        int len = s.length();

        // 4. 创建一个栈
        Stack<Character> stack = new Stack();

        // 3. 遍历每一个字符
        for (int i = 0; i < len; i++) {
            char c = s.charAt(i);
            // 5. 如果哈希表中包含key, 则表示是左括号
            if (map.containsKey(c)) {
                // 6. 则直接压入栈
                stack.push(c);
            } else {
                // 7. 如果是右括号且栈为空, 则返回false
                if (stack.isEmpty()) return false;
                // 8. 弹出栈中的左括号
                char left = stack.pop();
                // 9. 从哈希表中取出对应的右括号进行匹配, 如不成功返回false
                if (c != map.get(left)) return false;
            }

        }
        // 10. 判断栈是否为空
        return stack.isEmpty();
    }

    @Test
    public void test() {
        Assert.assertEquals(new 标签.栈._20_有效的括号().isValid("()"), new _20_有效的括号().isValid("()"));
        Assert.assertEquals(new 标签.栈._20_有效的括号().isValid("()[]{}"), new _20_有效的括号().isValid("()[]{}"));
        Assert.assertEquals(new 标签.栈._20_有效的括号().isValid("(]"), new _20_有效的括号().isValid("(]"));
        Assert.assertEquals(new 标签.栈._20_有效的括号().isValid("([)]"), new _20_有效的括号().isValid("([)]"));
        Assert.assertEquals(new 标签.栈._20_有效的括号().isValid("{[]}"), new _20_有效的括号().isValid("{[]}"));

    }
}
