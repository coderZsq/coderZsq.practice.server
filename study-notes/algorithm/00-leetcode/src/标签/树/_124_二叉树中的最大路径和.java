package 标签.树;

/**
 * https://leetcode-cn.com/problems/binary-tree-maximum-path-sum/
 *
 * @author zhushuangquan
 */
public class _124_二叉树中的最大路径和 {
    private int sum = Integer.MIN_VALUE;

    public int maxPathSum(TreeNode root) {
        value(root);
        return sum;
    }

    /**
     * 计算node提供给父节点的最大路径值
     */
    private int value(TreeNode node) {
        if (node == null) return 0;
        int lv = Math.max(value(node.left), 0);
        int rv = Math.max(value(node.right), 0);
        sum = Math.max(sum, node.val + lv + rv);
        return node.val + Math.max(lv, rv);
    }
}
