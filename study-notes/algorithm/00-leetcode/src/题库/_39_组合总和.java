package 题库;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * https://leetcode-cn.com/problems/combination-sum/
 *
 * @author zhushuangquan
 */
public class _39_组合总和 {
    public List<List<Integer>> combinationSum(int[] candidates, int target) {
        if (candidates == null || candidates.length == 0) return null;
        List<List<Integer>> list = new ArrayList<>();
        List<Integer> nums = new ArrayList<>();
        // 配合begin用于去重, 保证数字是由小到大的顺序选择的
        Arrays.sort(candidates);
        dfs(0, target, candidates, nums, list);
        return list;
    }

    /**
     * @param begin  从哪个位置的数开始选取 (用于去重, 保证数字是由小到大的顺序选择的)
     * @param remain 还剩多少凑够target
     */
    private void dfs(int begin, int remain, int[] candidates, List<Integer> nums, List<List<Integer>> list) {
        if (remain == 0) {
            list.add(new ArrayList<>(nums));
            return;
        }

        for (int i = begin; i < candidates.length; i++) {
            // 如果candidates[i]超过remain, 那么后面的数值必然超过remain
            if (remain < candidates[i]) return;
            nums.add(candidates[i]);
            dfs(i, remain - candidates[i], candidates, nums, list);
            nums.remove(nums.size() - 1);
        }
    }

}
