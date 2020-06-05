package 标签.数组;

/**
 * https://leetcode-cn.com/problems/move-zeroes/
 *
 * @author zhushuangquan
 */
public class _283_移动零 {
    public void moveZeroes(int[] nums) {
        if (nums == null) return;
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

class _283_移动零2 {
    public void moveZeroes(int[] nums) {
        if (nums == null || nums.length == 0) return;
        // 1. 双指针解法
        for (int i = 0, j = 0; j < nums.length; j++) {
            // 2. 找到nums[j]不等于0为止
            if (nums[j] == 0) continue;
            // 3. 不等则交换
            int tmp = nums[i];
            nums[i] = nums[j];
            nums[j] = tmp;
            // 4. i进位
            i++;
        }
    }
}