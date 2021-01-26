package 题库;

/**
 * https://leetcode-cn.com/problems/number-of-equivalent-domino-pairs/
 *
 * @author zhushuangquan
 */
public class _1128_等价多米诺骨牌对的数量 {
    public int numEquivDominoPairs(int[][] dominoes) {
        int[] bucket = new int[100];
        int ret = 0;
        for (int[] dominoe : dominoes) {
            int val;
            if (dominoe[0] < dominoe[1]) {
                val = dominoe[0] * 10 + dominoe[1];
            } else {
                val = dominoe[1] * 10 + dominoe[0];
            }
            ret += bucket[val]++;
        }
        return ret;
    }
}
