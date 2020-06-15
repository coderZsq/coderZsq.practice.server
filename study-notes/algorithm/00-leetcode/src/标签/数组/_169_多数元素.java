package 标签.数组;

/**
 * 给定一个大小为 n 的数组，找到其中的多数元素。多数元素是指在数组中出现次数大于 ⌊ n/2 ⌋ 的元素。
 *
 * 你可以假设数组是非空的，并且给定的数组总是存在多数元素。
 *
 *  
 *
 * 示例 1:
 *
 * 输入: [3,2,3]
 * 输出: 3
 * 示例 2:
 *
 * 输入: [2,2,1,1,1,2,2]
 * 输出: 2
 *
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/majority-element
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 */
public class _169_多数元素 {
    public int majorityElement(int[] nums) {
        return majorityElement(nums, nums[0], 0);
    }

    private int majorityElement(int[] nums, int val, int start) {
        // 1. 定义当前区间的众数数量
        int count = 0;
        // 2. 保存当前区间的起始值
        int begin = start;
        while (start < nums.length) {
            // 3. 如果值相同则加1, 值不同则减1
            if (nums[start] == val) {
                count++;
            } else {
                count--;
            }
            // 4. 如果数量大于一半 提前返回众数
            if (count > (nums.length - begin) >> 1) return val;
            // 5. 如果数量小于0, 则重新开始计数
            if (count < 0) return majorityElement(nums, nums[start], start);
            start++;
        }
        return val;
    }
}
