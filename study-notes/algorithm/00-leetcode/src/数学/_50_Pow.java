package 数学;

/**
 * https://leetcode-cn.com/problems/powx-n/
 *
 * @author zhushuangquan
 */
public class _50_Pow {
    // T(n) = T(n / 2) + O(1) = O(logn)
    static public double myPow(double x, int n) {
        if (n == 0) return 1;
        if (n == -1) return 1 / x;
        // 是否为奇数
        boolean odd = (n & 1) == 1;
        // System.out.println(n + "_" + (n >> 1));
        double half = myPow(x, n >> 1);
        half *= half;
        // 是否为负数
        // x = (n < 0) ? (1 / x) : x;
        return odd ? (half * x) : half;
    }

    public static void main(String[] args) {
        // myPow(2, -2);
        // System.out.println(-1 >> 10);
        System.out.println(-5 >> 1);
    }
}

class _50_Pow2 {
    static public double myPow(double x, int n) {
        boolean neg = n < 0;
        long y = neg ? -((long) n) : n;
        double res = 1.0;
        while (y > 0) {
            // System.out.println(n & 1);
            if ((y & 1) == 1) {
                // 如果最后一个二进制位是1, 就累乘上x
                res *= x;
            }
            x *= x;
            // 舍弃掉最后一个二进制位
            y >>= 1;
        }
        return neg ? (1 / res) : res;
    }

    public static void main(String[] args) {
        // 1010
        // myPow(0, 10);
        System.out.println(myPow(2, -2));
    }
}
