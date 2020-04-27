package 标签.数学;

/**
 * https://leetcode-cn.com/problems/yuan-quan-zhong-zui-hou-sheng-xia-de-shu-zi-lcof/
 *
 * @author zhushuangquan
 */
public class _面试题62_圆圈中最后剩下的数字 {
    // f(n, m) = (f(n - 1, m) + m) % n
    public int lastRemaining(int n, int m) {
        return (n == 1) ? 0 : (lastRemaining(n - 1, m) + m) % n;
    }

    public static void main(String[] args) {
        _面试题62_圆圈中最后剩下的数字 o = new _面试题62_圆圈中最后剩下的数字();
        System.out.println(o.lastRemaining(5, 3));
    }
}

class _面试题62_圆圈中最后剩下的数字2 {
    // f(1, 3) = 0
    // f(2, 3) = (f(1, 3) + 3) % 2
    // ...
    // f(7, 3) = (f(6, 3) + 3) % 7
    // f(8, 3) = (f(7, 3) + 3) % 8
    // f(9, 3) = (f(8, 3) + 3) % 9
    // f(10, 3) = (f(9, 3) + 3) % 10
    public int lastRemaining(int n, int m) {
        int res = 0;
        for (int i = 2; i <= n; i++) { // i是数据规模, 代表有多少个数字(有多少个人)
            res = (res + m) % i;
        }
        return res;
    }

    public static void main(String[] args) {
        _面试题62_圆圈中最后剩下的数字 o = new _面试题62_圆圈中最后剩下的数字();
        System.out.println(o.lastRemaining(10, 17));
    }
}