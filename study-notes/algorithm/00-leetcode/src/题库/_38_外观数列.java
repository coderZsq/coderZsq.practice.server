package 题库;

/**
 * 「外观数列」是一个整数序列，从数字 1 开始，序列中的每一项都是对前一项的描述。前五项如下：
 *
 * 1.     1
 * 2.     11
 * 3.     21
 * 4.     1211
 * 5.     111221
 * 1 被读作  "one 1"  ("一个一") , 即 11。
 * 11 被读作 "two 1s" ("两个一"）, 即 21。
 * 21 被读作 "one 2",  "one 1" （"一个二" ,  "一个一") , 即 1211。
 *
 * 给定一个正整数 n（1 ≤ n ≤ 30），输出外观数列的第 n 项。
 *
 * 注意：整数序列中的每一项将表示为一个字符串。
 *
 *  
 *
 * 示例 1:
 *
 * 输入: 1
 * 输出: "1"
 * 解释：这是一个基本样例。
 * 示例 2:
 *
 * 输入: 4
 * 输出: "1211"
 * 解释：当 n = 3 时，序列是 "21"，其中我们有 "2" 和 "1" 两组，"2" 可以读作 "12"，也就是出现频次 = 1 而 值 = 2；类似 "1" 可以读作 "11"。所以答案是 "12" 和 "11" 组合在一起，也就是 "1211"。
 *
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/count-and-say
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 */
public class _38_外观数列 {
    public static String countAndSay(int n) {
        if (n == 1) return "1";
        // 1. 递归, 缩小规模
        char[] chars = countAndSay(n - 1).toCharArray();
        // 2. loc 为起始点, offset 为相同字符的偏移
        int loc = 0, offset = 1;
        StringBuilder s = new StringBuilder();
        while (offset < chars.length) {
            // 4. 如果前一个和后一个不相等
            if (chars[loc] != chars[offset]) {
                // 6. 计算长度
                s.append(offset - loc).append(chars[loc]);
                // 5. 动态规划
                loc = offset;
            }
            // 3. 循环
            offset++;
        }
        // 7. 补上 n == 2 的情况
        s.append(offset - loc).append(chars[loc]);
        return s.toString();
    }
}
