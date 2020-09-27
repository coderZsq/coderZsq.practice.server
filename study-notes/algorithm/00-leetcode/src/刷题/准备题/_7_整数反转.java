package 刷题.准备题;

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
            res = prevRes * 10 + mod;
            if ((res - mod) / 10 != prevRes) return 0;
            x /= 10;
        }
        return res;
    }
}
 