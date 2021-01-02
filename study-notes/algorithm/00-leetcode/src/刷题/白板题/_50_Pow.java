package 刷题.白板题;

/**
 * https://leetcode-cn.com/problems/powx-n/
 *
 * @author zhushuangquan
 */
public class _50_Pow {
    public double myPow(double x, int n) {
        long y = (n < 0) ? -((long) n) : n;
        double res = 1.0;
        while (y > 0) {
            if ((y & 1) == 1) {
                res *= x;
            }
            x *= x;
            y >>= 1;
        }
        return (n < 0) ? (1 / res) : res;
    }
}