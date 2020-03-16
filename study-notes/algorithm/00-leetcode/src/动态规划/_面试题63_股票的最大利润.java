package 动态规划;

/**
 * https://leetcode-cn.com/problems/gu-piao-de-zui-da-li-run-lcof/
 *
 * @author zhushuangquan
 */
public class _面试题63_股票的最大利润 {
    public int maxProfit(int[] prices) {
        if (prices == null || prices.length == 0) return 0;
        // 前面扫描过的最小价值
        int minPrice = prices[0];
        // 前面扫描过的最大利润
        int maxProfit = 0;
        // 扫描所有的价格
        for (int i = 1; i < prices.length; i++) {
            if (prices[i] < minPrice) {
                minPrice = prices[i];
            } else { // 把第i天的股票卖出
                maxProfit = Math.max(maxProfit, prices[i] - minPrice);
            }
        }
        return maxProfit;
    }
}
