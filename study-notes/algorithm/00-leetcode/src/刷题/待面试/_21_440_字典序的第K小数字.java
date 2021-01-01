package 刷题.待面试;

/**
 * https://leetcode-cn.com/problems/k-th-smallest-in-lexicographical-order/
 *
 * @author zhushuangquan
 */
public class _21_440_字典序的第K小数字 {
    public int findKthNumber(int n, int k) {
        int cur = 1;
        k--;
        while (k > 0) {
            int steps = getSteps(n, cur, cur + 1);
            if (steps > k) {
                cur *= 10;
                k--;
            } else {
                cur++;
                k -= steps;
            }
        }
        return cur;
    }

    private int getSteps(int n, long cur, long next) {
        int steps = 0;
        while (cur <= n) {
            steps += Math.min(n + 1, next) - cur;
            cur *= 10;
            next *= 10;
        }
        return steps;
    }
}
