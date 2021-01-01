package 刷题.待面试;

/**
 * https://leetcode-cn.com/problems/palindrome-number/
 *
 * @author zhushuangquan
 */
public class _22_9_回文数 {
    public boolean isPalindrome(int x) {
        if (x < 0 || (x % 10 == 0 && x != 0)) return false;
        int mid = 0;
        while (x > mid) {
            int mod = x % 10;
            mid = mid * 10 + mod;
            x /= 10;
        }
        return x == mid || x == mid / 10;
    }
}