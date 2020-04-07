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
        // System.out.println(n + "_" + (n >> 1));
        double half = myPow(x, n >> 1);
        half *= half;
        // 是否为负数
        // x = (n < 0) ? (1 / x) : x;
        // 是否为奇数
        return (n & 1) == 1 ? (half * x) : half;
    }

    public static void main(String[] args) {
        // myPow(2, -2);
        // System.out.println(-1 >> 10);
        System.out.println(-5 >> 1);
    }
}

class _50_Pow2 {
    static public double myPow(double x, int n) {
        long y = (n < 0) ? -((long) n) : n;
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
        return (n < 0) ? (1 / res) : res;
    }

    public static void main(String[] args) {
        // 1010
        // myPow(0, 10);
        System.out.println(myPow(2, -2));
    }
}

class powMod {
    static public int powMod(int x, int y, int z) {
        if (y < 0 || z == 0) return 0;
        int res = 1 % z;
        x %= z;
        while (y > 0) {
            if ((y & 1) == 1) {
                // 如果最后一个二进制位是1, 就累乘上x
                res = (res * x) % z;
            }
            x = (x * x) % z;
            // 舍弃掉最后一个二进制位
            y >>= 1;
        }
        return res;
    }

    public static void main(String[] args) {
        System.out.println(powMod(123, 456, 789));
    }
}

class powMod2 {
    // 2^100 % 6 = (2^50 * 2^50) % 6 = ((2^50 % 6) * (2^50 % 6)) % 6
    // 2^101 % 6 = (2^50 * 2^50 * 2) % 6 = ((2^50 % 6) * (2^50 % 6) * (2 % 6)) % 6
    static public int powMod(int x, int y, int z) {
        if (y < 0 || z == 0) return 0;
        if (y == 0) return 1 % z;
        int half = powMod(x, y >> 1, z);
        half *= half;
        if ((y & 1) == 0) { // 偶数
            return half % z;
        } else { // 奇数
            return (half * (x % z)) % z;
        }
    }

    public static void main(String[] args) {
        System.out.println(powMod(123, 456, 789));
    }
}