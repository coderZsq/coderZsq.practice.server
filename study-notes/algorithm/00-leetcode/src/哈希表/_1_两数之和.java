package 哈希表;

import java.util.HashMap;

/**
 * https://leetcode-cn.com/problems/two-sum/
 *
 * @author zhushuangquan
 */
public class _1_两数之和 {
    public int[] twoSum(int[] nums, int target) {
        HashMap<Integer, Integer> map = new HashMap<>();
        for (int i = 0; i < nums.length; i++) {
            Integer key = target - nums[i];
            if (map.containsKey(key)) {
                return new int[]{map.get(key), i};
            }
            map.put(nums[i], i);
        }
        return null;
    }
}
