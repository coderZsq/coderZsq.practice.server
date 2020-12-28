package 刷题.白板题;

/**
 * https://leetcode-cn.com/problems/reverse-words-in-a-string/
 *
 * @author zhushuangquan
 */
public class _151_翻转字符串里的单词 {
    public String reverseWords(String s) {
        if (s == null) return "";
        char[] chars = s.toCharArray();
        int cur = 0;
        boolean space = true;
        for (int i = 0; i < chars.length; i++) {
            if (chars[i] != ' ') {
                chars[cur++] = chars[i];
                space = false;
            } else if (space == false) {
                chars[cur++] = ' ';
                space = true;
            }
        }
        int len = space ? (cur - 1) : cur;
        if (len <= 0) return "";

        reverse(chars, 0, len);
        int prevSpaceIdx = -1;
        for (int i = 0; i < len; i++) {
            if (chars[i] != ' ') continue;
            reverse(chars, prevSpaceIdx + 1, i);
            prevSpaceIdx = i;
        }
        reverse(chars, prevSpaceIdx + 1, len);
        return new String(chars, 0, len);
    }

    private void reverse(char[] chars, int li, int ri) {
        ri--;
        while (li < ri) {
            char tmp = chars[li];
            chars[li] = chars[ri];
            chars[ri] = tmp;
            li++;
            ri--;
        }
    }
}
