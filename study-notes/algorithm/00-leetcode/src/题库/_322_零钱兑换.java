package 题库;

/**
 * https://leetcode-cn.com/problems/coin-change/
 *
 * @author zhushuangquan
 */
public class _322_零钱兑换 {
    public int coinChange(int[] coins, int amount) {
        if (amount < 1 || coins == null || coins.length == 0) return 0;
        int[] dp = new int[amount + 1];
        for (int i = 1; i <= amount; i++) {
            int min = Integer.MAX_VALUE;
            for (int coin : coins) {
                if (i < coin) continue;
                int v = dp[i - coin];
                if (v < 0 || v >= min) continue;
                min = v;
            }
            if (min == Integer.MAX_VALUE) {
                dp[i] = -1;
            } else {
                dp[i] = min + 1;
            }
        }
        return dp[amount];
    }
}
