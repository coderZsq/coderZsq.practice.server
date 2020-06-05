package 刷题.高频题;

/**
 * 给定一个数组 nums，编写一个函数将所有 0 移动到数组的末尾，同时保持非零元素的相对顺序。
 * <p>
 * 示例:
 * <p>
 * 输入: [0,1,0,3,12]
 * 输出: [1,3,12,0,0]
 * 说明:
 * <p>
 * 必须在原数组上操作，不能拷贝额外的数组。
 * 尽量减少操作次数。
 * <p>
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/move-zeroes
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 */
public class _283_移动零 {
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
