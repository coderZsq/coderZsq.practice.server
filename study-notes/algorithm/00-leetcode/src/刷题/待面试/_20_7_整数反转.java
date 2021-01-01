package 刷题.待面试;

/**
 * https://leetcode-cn.com/problems/reverse-integer/
 *
 * @author zhushuangquan
 */
public class _20_7_整数反转 {
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
 