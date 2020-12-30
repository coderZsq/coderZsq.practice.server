package 刷题.白板题;

import java.util.ArrayList;
import java.util.List;

/**
 * https://leetcode-cn.com/problems/permutations/
 *
 * @author zhushuangquan
 */
public class _46_全排列 {
    private List<List<Integer>> list;
    private List<Integer> result;
    private int[] nums;

    public List<List<Integer>> permute(int[] nums) {
        if (nums == null) return null;
        list = new ArrayList<>();
        if (nums.length == 0) return list;
        result = new ArrayList<>();
        this.nums = nums;
        dfs(0);
        return list;
    }

    private void dfs(int idx) {
        if (idx == nums.length) {
            list.add(new ArrayList<>(result));
            return;
        }
        for (int num : nums) {
            if (result.contains(num)) continue;
            result.add(num);
            dfs(idx + 1);
            result.remove(result.size() - 1);
        }
    }
}
