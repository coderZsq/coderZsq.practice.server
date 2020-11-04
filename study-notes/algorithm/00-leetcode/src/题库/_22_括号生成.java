package 题库;

import java.util.ArrayList;
import java.util.List;

/**
 * https://leetcode-cn.com/problems/generate-parentheses/
 *
 * @author zhushuangquan
 */
public class _22_括号生成 {
    public static void main(String[] args) {
        _22_括号生成 o = new _22_括号生成();
        System.out.println(o.generateParenthesis(3));
    }

    private List<String> result;

    public List<String> generateParenthesis(int n) {
        result = new ArrayList<>();
        _generate(0, 0, n, "");
        return result;
    }

    private void _generate(int left, int right, int n, String s) {
        // terminator
        if (left == n && right == n) {
            result.add(s);
            return;
        }
        // process current logic: left, right

        // drill down
        if (left < n) _generate(left + 1, right, n, s + "(");
        if (left > right) _generate(left, right + 1, n, s + ")");

        // reverse states
    }
}

class _22_括号生成2 {
    public static void main(String[] args) {
        _22_括号生成2 o = new _22_括号生成2();
        System.out.println(o.generateParenthesis(3));
    }

    public List<String> generateParenthesis(int n) {
        List<String> list = new ArrayList<>();
        if (n < 0) return list;
        dfs(0, n, n, new char[n << 1], list);
        return list;
    }

    /**
     * @param idx         搜索的层号
     * @param leftRemain  左括号的剩余数量
     * @param rightRemain 右括号的剩余数量
     * @param string      用来存放每一层的选择
     */
    private void dfs(int idx, int leftRemain, int rightRemain, char[] string, List<String> list) {
        if (idx == string.length) {
            list.add(new String(string));
            return;
        }

        // 枚举这一层所有可能的选择
        // 选择一种可能之后, 进入下一层搜索

        // 什么情况可以选择左括号? (左括号的数量 > 0)
        // 选择左括号, 然后进入下一层搜索
        if (leftRemain > 0) {
            string[idx] = '(';
            dfs(idx + 1, leftRemain - 1, rightRemain, string, list);
        }

        // 当左括号, 右括号的数量一样时, 只能选择左括号
        // 什么情况可以选择右括号? (右括号的数量 > 0) && (右括号的数量 != 左括号的数量)
        if (rightRemain > 0 && leftRemain != rightRemain) {
            string[idx] = ')';
            dfs(idx + 1, leftRemain, rightRemain - 1, string, list);
        }
    }
}
