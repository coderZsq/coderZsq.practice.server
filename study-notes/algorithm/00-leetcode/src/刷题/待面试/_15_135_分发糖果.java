package 刷题.待面试;

/**
 * https://leetcode-cn.com/problems/candy/
 *
 * @author zhushuangquan
 */
public class _15_135_分发糖果 {
    public int candy(int[] ratings) {
        if (ratings == null || ratings.length == 0) return 0;
        int[] candies = new int[ratings.length];
        for (int i = 0; i < candies.length; i++) {
            candies[i] = 1;
        }
        for (int i = 1; i < ratings.length; i++) {
            if (ratings[i - 1] < ratings[i]) {
                candies[i] = Math.max(candies[i], candies[i - 1] + 1);
            }
        }
        for (int i = ratings.length - 2; i >= 0; i--) {
            if (ratings[i] > ratings[i + 1]) {
                candies[i] = Math.max(candies[i], candies[i + 1] + 1);
            }
        }
        int candy = 0;
        for (int i : candies) {
            candy += i;
        }
        return candy;
    }
}
