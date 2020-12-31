package 刷题.待面试;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * https://leetcode-cn.com/problems/3sum/
 *
 * @author zhushuangquan
 */
public class _09_15_三数之和 {
    public List<List<Integer>> threeSum(int[] nums) {
        if (nums == null) return null;
        List<List<Integer>> lists = new ArrayList<>();
        if (nums.length < 3) return lists;

        Arrays.sort(nums);

        int lastIdx = nums.length - 3;
        int lastR = nums.length - 1;
        for (int i = 0; i <= lastIdx; i++) {
            if (i > 0 && nums[i] == nums[i - 1]) continue;
            int l = i + 1;
            int r = lastR;
            int remain = -nums[i];
            while (l < r) {
                int sumLR = nums[l] + nums[r];
                if (sumLR == remain) {
                    lists.add(Arrays.asList(nums[i], nums[l], nums[r]));
                    while (l < r && nums[l] == nums[l + 1]) l++;
                    while (l < r && nums[r] == nums[r - 1]) r--;
                    l++;
                    r--;
                } else if (sumLR < remain) {
                    l++;
                } else {
                    r--;
                }
            }
        }
        return lists;
    }
}
