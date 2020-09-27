package 刷题.准备题;

/**
 * https://leetcode-cn.com/problems/powx-n/
 *
 * @author zhushuangquan
 */
public class _50_Pow {
    static public double myPow(double x, int n) {
        if (n == 0) return 1;
        if (n == -1) return 1 / x;
        double half = myPow(x, n >> 1);
        half *= half;
        return (n & 1) == 1 ? half * x : half;
    }
}