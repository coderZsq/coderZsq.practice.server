package 题库;

/**
 * 实现 strStr() 函数。
 *
 * 给定一个 haystack 字符串和一个 needle 字符串，在 haystack 字符串中找出 needle 字符串出现的第一个位置 (从0开始)。如果不存在，则返回  -1。
 *
 * 示例 1:
 *
 * 输入: haystack = "hello", needle = "ll"
 * 输出: 2
 * 示例 2:
 *
 * 输入: haystack = "aaaaa", needle = "bba"
 * 输出: -1
 * 说明:
 *
 * 当 needle 是空字符串时，我们应当返回什么值呢？这是一个在面试中很好的问题。
 *
 * 对于本题而言，当 needle 是空字符串时我们应当返回 0 。这与C语言的 strstr() 以及 Java的 indexOf() 定义相符。
 *
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/implement-strstr
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 */
public class _28_实现strStr {
    public static int strStr(String haystack, String needle) {
        // 1. 取出原始串和模式串的长度
        int len1 = haystack.length();
        int len2 = needle.length();
        // 2. 判断边界条件
        if (len2 > len1) return -1;
        if (len2 == 0) return 0;
        // 3. 开始遍历原始串和模式串的差值
        for (int i = 0; i < len1 - len2 + 1; i++) {
            // 4. 当原始串遍历到的字符和模式串收尾相等时截取长度判断相等
            if (haystack.charAt(i) == needle.charAt(0) && haystack.substring(i, i + len2).equals(needle)) return i;
        }
        return -1;
    }
}
