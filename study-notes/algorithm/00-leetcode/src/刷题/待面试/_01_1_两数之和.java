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
        Map<Integer, Integer> prevIdx = new HashMap<>(nums.length);
        for (int i = 0; i < nums.length; i++) {
            Integer pi = prevIdx.get(target - nums[i]);
            if (pi != null) return new int[]{pi, i};
            prevIdx.put(nums[i], i);
        }
        return null;
    }
}
