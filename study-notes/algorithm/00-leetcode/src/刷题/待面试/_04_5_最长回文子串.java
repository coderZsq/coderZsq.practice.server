package 刷题.待面试;

/**
 * https://leetcode-cn.com/problems/longest-palindromic-substring/
 *
 * @author zhushuangquan
 */
public class _04_5_最长回文子串 {
    public String longestPalindrome(String s) {
        if (s == null || s.length() == 0) return null;
        char[] chars = s.toCharArray();
        boolean[][] dp = new boolean[chars.length][chars.length];
        int begin = 0;
        int maxLen = 0;
        for (int i = chars.length - 1; i >= 0; i--) {
            for (int j = i; j < chars.length; j++) {
                int len = j - i + 1;
                dp[i][j] = ((chars[i] == chars[j]) && ((len <= 2) || dp[i + 1][j - 1]));
                if (dp[i][j] && len > maxLen) {
                    maxLen = len;
                    begin = i;
                }
            }
        }
        return new String(chars, begin, maxLen);
    }
}
