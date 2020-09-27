package 刷题.准备题;

/**
 * https://leetcode-cn.com/problems/sub-sort-lcci/
 *
 * @author zhushuangquan
 */
public class _面试题_16_16_部分排序 {
    /**
     * { 1, 5, 4, 3, 2, 6, 7 }
     */
    public int[] subSort(int[] array) {
        if (array == null || array.length == 0) return new int[]{-1, -1};

        int r = -1;
        int max = array[0];
        for (int i = 1; i < array.length; i++) {
            if (array[i] >= max) {
                max = array[i];
            } else {
                r = i;
            }
        }

        if (r == -1) return new int[]{-1, -1};

        int l = -1;
        int min = array[array.length - 1];
        for (int i = array.length - 2; i >= 0; i--) {
            if (array[i] <= min) {
                min = array[i];
            } else {
                l = i;
            }
        }

        return new int[]{l, r};
    }
}
