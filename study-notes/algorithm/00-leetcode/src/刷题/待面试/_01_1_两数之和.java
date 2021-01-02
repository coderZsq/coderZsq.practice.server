package 刷题.待面试;

import java.util.HashMap;
import java.util.Map;

/**
 * https://leetcode-cn.com/problems/two-sum/
 *
 * @author zhushuangquan
 */
public class _01_1_两数之和 {
    public int[] twoSum(int[] nums, int target) {
        if (nums == null || nums.length == 0) return null;
        Map<Integer, Integer> map = new HashMap<>(nums.length);
        for (int i = 0; i < nums.length; i++) {
            Integer idx = map.get(target - nums[i]);
            if (idx != null) return new int[]{idx, i};
            map.put(nums[i], i);
        }
        return null;
    }
}
