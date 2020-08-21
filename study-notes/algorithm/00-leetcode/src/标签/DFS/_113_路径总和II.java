package 标签.DFS;

import 标签.树.TreeNode;

import java.util.ArrayList;
import java.util.List;

/**
 * https://leetcode-cn.com/problems/path-sum-ii/
 *
 * @author zhushuangquan
 */
public class _113_路径总和II {
    // 此题的坑是: sum和节点的val可能是负数, 路径要求到达叶子节点
    public List<List<Integer>> pathSum(TreeNode root, int sum) {
        List<List<Integer>> list = new ArrayList<>();
        if (root == null) return list;
        dfs(root, sum, new ArrayList<>(), list);
        return list;
    }

    private void dfs(TreeNode node, int remain, List<Integer> nums, List<List<Integer>> list) {
        if (node == null) return;
        remain -= node.val;
        nums.add(node.val);
        // remain == 0不代表结束了, 还要求到达叶子节点(因为可能后面通过正, 负数重新让remain == 0)
        if (node.left == null && node.right == null) {
            if (remain == 0) list.add(new ArrayList<>(nums));
        } else {
            dfs(node.left, remain, nums, list);
            dfs(node.right, remain, nums, list);
        }
        nums.remove(nums.size() - 1);
    }
}
