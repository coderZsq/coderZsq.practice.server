package 刷题.高频题;

/**
 * 编写一个函数来查找字符串数组中的最长公共前缀。
 * 
 * 如果不存在公共前缀，返回空字符串 ""。
 * 
 * 示例 1:
 * 
 * 输入: ["flower","flow","flight"]
 * 输出: "fl"
 * 示例 2:
 * 
 * 输入: ["dog","racecar","car"]
 * 输出: ""
 * 解释: 输入不存在公共前缀。
 * 说明:
 * 
 * 所有输入只包含小写字母 a-z 。
 * 
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/longest-common-prefix
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 */
public class _14_最长公共前缀 {
    public String longestCommonPrefix(String[] strs) {
        if (strs == null || strs.length == 0) return "";
        // 1. 拿数组的第一个作为前缀比较串
        String prefix = strs[0];
        // 2. 遍历和后面的每一个字符串进行比较
        for (int i = 1; i < strs.length; i++) {
            // 3. 判断字符串是否以prefix前缀开头 != 0 表示不是prefix开头
            while (strs[i].indexOf(prefix) != 0) {
                // 4. 如果不匹配, 从后删减模式串
                prefix = prefix.substring(0, prefix.length() - 1);
                // 5. 当前缀模式串为空, 则判定没有前缀
                if (prefix.isEmpty()) return "";
            }
        }
        return prefix;
    }
}
