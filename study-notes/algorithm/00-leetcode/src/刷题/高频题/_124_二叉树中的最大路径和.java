package 刷题.高频题;

import 标签.树.TreeNode;

/**
 * https://leetcode-cn.com/problems/binary-tree-maximum-path-sum/
 *
 * @author zhushuangquan
 */
public class _124_二叉树中的最大路径和 {
    // 1. 定义返回的最大路径和, 默认最小值
    private int sum = Integer.MIN_VALUE;

    public int maxPathSum(TreeNode root) {
        // 7.调用获取
        value(root);
        // 2. 返回最大路径和
        return sum;
    }

    /**
     * 定义穿过当前节点的最子路径和
     * @param node 当前节点
     * @return 最大子路径和
     */
    private int value(TreeNode node) {
        // 3. 遍历到null节点返回结束
        if (node == null) return 0;
        // 4. 获取左右子树的最大路径和, 如果是负数则舍弃
        int lv = Math.max(value(node.left), 0);
        int rv = Math.max(value(node.right), 0);
        // 5. 判断穿过当前节点的最大值和目前的最大值对比 获取最大值
        sum = Math.max(sum, lv + rv + node.val);
        // 6. 当前节点成为父节点的左子树或右子树时只能选择一边
        return node.val + Math.max(lv, rv);
    }
}
