package 刷题.白板题;

/**
 * https://leetcode-cn.com/problems/sort-colors/
 *
 * @author zhushuangquan
 */
public class _75_颜色分类 {
    public void sortColors(int[] nums) {
        int l = 0;
        int r = nums.length - 1;
        int cur = 0;
        while (cur <= r) {
            if (nums[cur] > 1) {
                swap(nums, cur, r--);
            } else if (nums[cur] == 1) {
                cur++;
            } else  {
                swap(nums, cur++, l++);
            }
        }
    }

    private void swap(int[] nums, int i, int j) {
        int tmp = nums[i];
        nums[i] = nums[j];
        nums[j] = tmp;
    }
}
