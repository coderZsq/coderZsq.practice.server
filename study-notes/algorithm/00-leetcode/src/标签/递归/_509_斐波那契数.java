package 标签.递归;

/**
 * https://leetcode-cn.com/problems/fibonacci-number
 *
 * @author zhushuangquan
 */
public class _509_斐波那契数 {
    public int fib(int N) {
        if (N <= 1) return N;
        int first = 0;
        int second = 1;
        for (int i = 2; i <= N; i++) {
            int sum = first + second;
            first = second;
            second = sum;
        }
        return second;
    }
}