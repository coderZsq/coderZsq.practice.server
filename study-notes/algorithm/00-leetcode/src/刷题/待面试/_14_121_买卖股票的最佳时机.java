package 刷题.待面试;

/**
 * https://leetcode-cn.com/problems/best-time-to-buy-and-sell-stock/
 *
 * @author zhushuangquan
 */
public class _14_121_买卖股票的最佳时机 {
    public int maxProfit(int[] prices) {
        if (prices == null || prices.length == 0) return 0;
        int minValue = prices[0];
        int maxProfit = 0;
        for (int i = 1; i < prices.length; i++) {
            if (minValue > prices[i]) {
                minValue = prices[i];
            } else {
                maxProfit = Math.max(maxProfit, prices[i] - minValue);
            }
        }
        return maxProfit;
    }
}
