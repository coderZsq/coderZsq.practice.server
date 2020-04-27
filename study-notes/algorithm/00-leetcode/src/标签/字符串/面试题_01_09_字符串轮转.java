package 标签.字符串;

/**
 * https://leetcode-cn.com/problems/string-rotation-lcci/
 *
 * @author zhushuangquan
 */
public class 面试题_01_09_字符串轮转 {
    // kMP
    public static void main(String[] args) {
        面试题_01_09_字符串轮转 o = new 面试题_01_09_字符串轮转();
        System.out.println(o.isFlipedString("cdab", "abdd"));
    }

    // 旋转词
    public boolean isFlipedString(String s1, String s2) {
        if ((s1 == null) || s2 == null) return false;
        if (s1.length() != s2.length()) return false;
        return (s1 + s1).contains(s2);
    }
}
