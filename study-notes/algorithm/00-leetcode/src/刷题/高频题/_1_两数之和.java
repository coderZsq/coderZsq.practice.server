package 刷题.高频题;

import java.util.HashMap;

/**
 * 给定一个整数数组 nums 和一个目标值 target，请你在该数组中找出和为目标值的那 两个 整数，并返回他们的数组下标。
 * 
 * 你可以假设每种输入只会对应一个答案。但是，数组中同一个元素不能使用两遍。
 * 
 * 示例:
 * 
 * 给定 nums = [2, 7, 11, 15], target = 9
 * 
 * 因为 nums[0] + nums[1] = 2 + 7 = 9
 * 所以返回 [0, 1]
 * 
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/two-sum
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 */
public class _1_两数之和 {
    public static int[] twoSum(int[] nums, int target) {
        HashMap<Integer, Integer> map = new HashMap<>(nums.length);
        for (int i = 0; i < nums.length; i++) {
            // 2. 从哈希表中找 (目标值 - 遍历到的新值) 的值
            // 找到  说明之前遍历到的值 + 新值 = 目标值
            // 没找到 说明之前没有任何值 + 新值 = 目标值
            // idx : 之前遍历到的值对应的nums数组的下标
            Integer idx = map.get(target - nums[i]);
            // 3. 找到 则返回[之前遍历到的值对应的nums数组的下标, 新遍历的下标]
            if (idx != null) return new int[]{idx, i};
            // 1. 使用哈希表的 key 唯一性
            // key   : nums数组遍历到的值
            // value : nums数组的下标
            map.put(nums[i], i);
        }
        return null;
    }
}
