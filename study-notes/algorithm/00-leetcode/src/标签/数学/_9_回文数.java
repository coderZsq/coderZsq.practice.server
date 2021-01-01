package 标签.数学;

/**
 * https://leetcode-cn.com/problems/palindrome-number/
 *
 * @author zhushuangquan
 */
public class _9_回文数 {
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

class _9_回文数2 {
    public boolean isPalindrome(int x) {
        if (x < 0 || (x % 10 == 0 && x != 0)) return false;
        int res = 0;
        int y = x;
        while (x != 0) {
            int mod = x % 10;
            res = res * 10 + mod;
            x /= 10;
        }
        return res == y;
    }
}

class _9_回文数3 {
    public boolean isPalindrome(int x) {
        char[] chars = String.valueOf(x).toCharArray();
        int l = 0;
        int r = chars.length - 1;
        while (l < r) {
            if (chars[l++] != chars[r--]) return false;
        }
        return true;
    }
}
