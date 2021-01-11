package 刷题.待面试;

/**
 * https://leetcode-cn.com/problems/edit-distance/
 *
 * @author zhushuangquan
 */
public class _16_72_编辑距离 {
    public int minDistance(String word1, String word2) {
        if (word1 == null || word2 == null) return 0;
        char[] c1 = word1.toCharArray();
        char[] c2 = word2.toCharArray();
        int[][] dp = new int[c1.length + 1][c2.length + 1];
        dp[0][0] = 0;
        for (int i = 1; i <= c1.length; i++) {
            dp[i][0] = i;
        }
        for (int j = 1; j <= c2.length; j++) {
            dp[0][j] = j;
        }
        for (int i = 1; i <= c1.length; i++) {
            for (int j = 1; j <= c2.length; j++) {
                int top = dp[i - 1][j] + 1;
                int left = dp[i][j - 1] + 1;
                int leftTop = dp[i - 1][j - 1];
                if (c1[i - 1] != c2[j - 1]) {
                    leftTop++;
                }
                dp[i][j] = Math.min(Math.min(left, top), leftTop);
            }
        }
        return dp[c1.length][c2.length];
    }
}
