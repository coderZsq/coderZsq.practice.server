package 刷题.待面试;

/**
 * https://leetcode-cn.com/problems/longest-substring-without-repeating-characters/
 *
 * @author zhushuangquan
 */
public class _03_3_无重复字符的最长子串 {
    public int lengthOfLongestSubstring(String s) {
        if (s == null || s.length() == 0) return 0;
        char[] chars = s.toCharArray();
        int[] pis = new int[128];
        for (int i = 0; i < 128; i++) {
            pis[i] = -1;
        }
        int li = 0;
        int max = 1;
        for (int i = 0; i < chars.length; i++) {
            int c = chars[i];
            li = Math.max(li, pis[c] + 1);
            max = Math.max(max, i - li + 1);
            pis[c] = i;
        }
        return max;
    }
}
