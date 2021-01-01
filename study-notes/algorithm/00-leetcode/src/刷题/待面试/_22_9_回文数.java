package 刷题.待面试;

/**
 * https://leetcode-cn.com/problems/palindrome-number/
 *
 * @author zhushuangquan
 */
public class _22_9_回文数 {
    public boolean isPalindrome(int x) {
        if (x < 0 || x % 10 == 0 && x != 0) return false;
        int r = 0;
        while (x > r) {
            int mod = x % 10;
            r = r * 10 + mod;
            x /= 10;
        }
        return x == r || x == r / 10;
    }
}
