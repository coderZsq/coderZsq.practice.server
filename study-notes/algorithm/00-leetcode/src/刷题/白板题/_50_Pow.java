package 刷题.白板题;

/**
 * https://leetcode-cn.com/problems/powx-n/
 *
 * @author zhushuangquan
 */
public class _50_Pow {
    public double myPow(double x, int n) {
        boolean neg = n < 0;
        double res = 1.0;
        long y = neg ? -(long)n : n;
        while (y != 0) {
            if ((y & 1) == 1) {
                res *= x;
            }
            x *= x;
            y >>= 1;
        }
        return neg ? 1 / res : res;
    }
}