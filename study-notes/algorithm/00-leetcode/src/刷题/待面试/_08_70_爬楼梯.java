package 刷题.待面试;

/**
 * https://leetcode-cn.com/problems/climbing-stairs/
 *
 * @author zhushuangquan
 */
public class _08_70_爬楼梯 {
    public int climbStairs(int n) {
        int first = 1;
        int second = 1;
        for (int i = 2; i <= n; i++) {
            int third = first + second;
            first = second;
            second = third;
        }
        return second;
    }
}
