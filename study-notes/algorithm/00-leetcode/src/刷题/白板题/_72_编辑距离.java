package 刷题.白板题;

/**
 * https://leetcode-cn.com/problems/edit-distance/
 *
 * @author zhushuangquan
 */
public class _72_编辑距离 {
    public int minDistance(String word1, String word2) {
        if (word1 == null || word2 == null) return 0;
        char[] rows = word1.toCharArray();
        char[] cols = word2.toCharArray();
        int[][] dp = new int[rows.length + 1][cols.length + 1];
        dp[0][0] = 0;
        for (int row = 1; row <= rows.length; row++) {
            dp[row][0] = row;
        }
        for (int col = 1; col <= cols.length; col++) {
            dp[0][col] = col;
        }
        for (int row = 1; row <= rows.length; row++) {
            for (int col = 1; col <= cols.length; col++) {
                int top = dp[row - 1][col] + 1;
                int left = dp[row][col - 1] + 1;
                int leftTop = dp[row - 1][col - 1];
                if (rows[row - 1] != cols[col - 1]) {
                    leftTop++;
                }
                dp[row][col] = Math.min(Math.min(top, left), leftTop);
            }
        }
        return dp[rows.length][cols.length];
    }
}
