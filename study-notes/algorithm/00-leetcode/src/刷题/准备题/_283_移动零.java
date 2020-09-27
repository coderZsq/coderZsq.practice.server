package 刷题.准备题;

/**
 * https://leetcode-cn.com/problems/move-zeroes/
 *
 * @author zhushuangquan
 */
public class _283_移动零 {
    public void moveZeroes(int[] nums) {
        if (nums.length == 0 && nums == null) return;
        for (int i = 0, cur = 0; i < nums.length; i++) {
            if (nums[i] == 0) continue;
            if (cur != i) {
                nums[cur] = nums[i];
                nums[i] = 0;
            }
            cur++;
        }
    }
}