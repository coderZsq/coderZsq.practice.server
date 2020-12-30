package 刷题.白板题;

/**
 * https://leetcode-cn.com/problems/reverse-integer/
 *
 * @author zhushuangquan
 */

public class _7_整数反转 {
    public int reverse(int x) {
        int res = 0;
        while (x != 0) {
            int prevRes = res;
            int mod = x % 10;
            res = res * 10 + mod;
            if ((res - mod) / 10 != prevRes) return 0;
            x /= 10;
        }
        return res;
    }
}
 