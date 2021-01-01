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
        int maxPorfit = 0;
        for (int i = 1; i < prices.length; i++) {
            if (prices[i] < minValue) {
                minValue = prices[i];
            } else {
                maxPorfit = Math.max(maxPorfit, prices[i] - minValue);
            }
        }
        return maxPorfit;
    }
}
