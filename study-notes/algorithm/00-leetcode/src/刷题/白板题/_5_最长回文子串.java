package 刷题.白板题;

/**
 * https://leetcode-cn.com/problems/longest-palindromic-substring/
 *
 * @author zhushuangquan
 */
public class _5_最长回文子串 {
    public String longestPalindrome(String s) {
        if (s == null) return null;
        char[] cs = s.toCharArray();
        if (cs.length <= 1) return s;
        boolean[][] dp = new boolean[cs.length][cs.length];
        int begin = 0;
        int maxLen = 1;
        for (int row = cs.length - 1; row >= 0; row--) {
            for (int col = row; col < cs.length; col++) {
                int len = col - row + 1;
                dp[row][col] = (cs[row] == cs[col]) && (len <= 2 || dp[row + 1][col - 1]);
                if (dp[row][col] && len > maxLen) {
                    maxLen = len;
                    begin = row;
                }
            }
        }
        return new String(cs, begin, maxLen);
    }
}
