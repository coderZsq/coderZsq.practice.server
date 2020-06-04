package 刷题.高频题;

/**
 * 给定一个数组，它的第 i 个元素是一支给定股票第 i 天的价格。
 * <p>
 * 如果你最多只允许完成一笔交易（即买入和卖出一支股票一次），设计一个算法来计算你所能获取的最大利润。
 * <p>
 * 注意：你不能在买入股票前卖出股票。
 * <p>
 *  
 * <p>
 * 示例 1:
 * <p>
 * 输入: [7,1,5,3,6,4]
 * 输出: 5
 * 解释: 在第 2 天（股票价格 = 1）的时候买入，在第 5 天（股票价格 = 6）的时候卖出，最大利润 = 6-1 = 5 。
 * 注意利润不能是 7-1 = 6, 因为卖出价格需要大于买入价格；同时，你不能在买入前卖出股票。
 * 示例 2:
 * <p>
 * 输入: [7,6,4,3,1]
 * 输出: 0
 * 解释: 在这种情况下, 没有交易完成, 所以最大利润为 0。
 * <p>
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/best-time-to-buy-and-sell-stock
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 */
public class _121_买卖股票的最佳时机 {
    public int maxProfit(int[] prices) {
        if (prices == null || prices.length == 0) return 0;
        // 1. 定义买入最小值
        int minPrice = prices[0];
        // 2. 定义最大利润
        int maxProfit = 0;
        for (int i = 1; i < prices.length; i++) {
            // 3. 判断更新最小买入价
            if (prices[i] < minPrice) {
                minPrice = prices[i];
            } else {
                // 4. 更新最大利润
                maxProfit = Math.max(maxProfit, prices[i] - minPrice);
            }
        }
        return maxProfit;
    }
}
