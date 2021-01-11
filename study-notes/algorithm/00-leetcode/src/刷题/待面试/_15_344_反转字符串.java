package 刷题.待面试;

/**
 * https://leetcode-cn.com/problems/reverse-string/
 *
 * @author zhushuangquan
 */
public class _15_344_反转字符串 {
    public void reverseString(char[] s) {
        int l = 0;
        int r = s.length - 1;
        while (l < r) {
            char tmp = s[l];
            s[l] = s[r];
            s[r] = tmp;
            l++;
            r--;
        }
    }
}
