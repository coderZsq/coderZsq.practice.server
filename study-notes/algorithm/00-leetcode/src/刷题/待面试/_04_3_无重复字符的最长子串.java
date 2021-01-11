package 刷题.待面试;

import java.util.HashMap;
import java.util.Map;

/**
 * https://leetcode-cn.com/problems/longest-substring-without-repeating-characters/
 *
 * @author zhushuangquan
 */
public class _04_3_无重复字符的最长子串 {
    public int lengthOfLongestSubstring(String s) {
        if (s == null || s.length() == 0) return 0;
        Map<Character, Integer> prevIdx = new HashMap<>();
        char[] chars = s.toCharArray();
        int li = 0;
        int max = 0;
        for (int i = 0; i < chars.length; i++) {
            Integer pi = prevIdx.get(chars[i]);
            if (pi != null && li <= pi) {
                li = pi + 1;
            }
            prevIdx.put(chars[i], i);
            max = Math.max(max, i - li + 1);
        }
        return max;
    }
}
