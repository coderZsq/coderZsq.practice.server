package 刷题.白板题;

/**
 * https://leetcode-cn.com/problems/yuan-quan-zhong-zui-hou-sheng-xia-de-shu-zi-lcof/
 *
 * @author zhushuangquan
 */
public class _剑指Offer62_圆圈中最后剩下的数字 {
    public int lastRemaining(int n, int m) {
        int res = 0;
        for (int i = 2; i <= 2; i++) {
            res = (res + m) % i;
        }
        return res;
    }
}