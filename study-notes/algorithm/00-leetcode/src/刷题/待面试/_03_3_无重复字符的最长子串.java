package 刷题.待面试;

import java.util.HashMap;
import java.util.Map;

/**
 * https://leetcode-cn.com/problems/longest-substring-without-repeating-characters/
 *
 * @author zhushuangquan
 */
public class _03_3_无重复字符的最长子串 {
    public int lengthOfLongestSubstring(String s) {
        if (s == null || s.length() == 0) return 0;
        Map<Character, Integer> prevIdxes = new HashMap<>();
        char[] chars = s.toCharArray();
        prevIdxes.put(chars[0], 0);
        int li = 0;
        int max = 1;
        for (int i = 1; i < chars.length; i++) {
            Integer pi = prevIdxes.get(chars[i]);
            if (pi != null && li <= pi) {
                li = pi + 1;
            }
            prevIdxes.put(chars[i], i);
            max = Math.max(max, i - li + 1);
        }
        return max;
    }
}
