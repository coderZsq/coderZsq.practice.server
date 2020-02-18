package 数组;

/**
 * https://leetcode-cn.com/problems/sub-sort-lcci/
 *
 * @author zhushuangquan
 */
public class _面试题_16_16_部分排序 {
    /**
     * { 1, 5, 4, 3, 2, 6, 7 }
     */
    public int[] subSort(int[] nums) {
        if (nums.length == 0) return new int[]{-1, -1};

        // 从左扫描到右寻找逆序对 (正序: 逐渐变大)
        int max = nums[0];
        // 用来记录最右的那个逆序对位置
        int r = -1;
        for (int i = 1; i < nums.length; i++) {
            int v = nums[i];
            if (v >= max) {
                max = v;
            } else {
                r = i;
            }
        }

        // 提前结束
        if (r == -1) return new int[]{-1, -1};

        // 从右扫描到左寻找逆序对 (正序: 逐渐变小)
        int min = nums[nums.length - 1];
        // 用来记录最左的那个逆序对位置
        int l = -1;
        for (int i = nums.length - 2; i >= 0; i--) {
            int v = nums[i];
            if (v <= min) {
                min = v;
            } else {
                l = i;
            }
        }

        return new int[]{l, r};
    }
}
