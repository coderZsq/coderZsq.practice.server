package 标签.数学;

/**
 * https://leetcode-cn.com/problems/k-th-smallest-in-lexicographical-order/
 *
 * @author zhushuangquan
 */
public class _440_字典序的第K小数字 {
    public int findKthNumber(int n, int k) {
        int cur = 1;
        k--;
        while (k > 0) {
            // 计算前缀之前的step数
            int steps = getSteps(n, cur, cur + 1);
            // 前缀间距太大, 需要深入一层
            if (steps > k) {
                cur *= 10;
                // 多了一个确定节点, 继续-1
                k--;
            } else { // 间距太小, 需要扩大前缀范围
                cur++;
                k -= steps;
            }
        }
        return cur;
    }

    private int getSteps(int n, long cur, long next) {
        int res = 0;
        while (cur <= n) {
            res += Math.min(n + 1, next) - cur;
            cur *= 10;
            next *= 10;
        }
        return res;
    }
}
