package 刷题.白板题;

/**
 * https://leetcode-cn.com/problems/best-time-to-buy-and-sell-stock/
 *
 * @author zhushuangquan
 */
public class _121_买卖股票的最佳时机 {
    public int maxProfit(int[] prices) {
        if (prices == null || prices.length == 0) return 0;
        int minValue = prices[0];
        int maxProfit = 0;
        for (int i = 0; i < prices.length; i++) {
            if (prices[i] < minValue) {
                minValue = prices[i];
            } else {
                maxProfit = Math.max(maxProfit, prices[i] - minValue);
            }
        }
        return maxProfit;
    }
}
