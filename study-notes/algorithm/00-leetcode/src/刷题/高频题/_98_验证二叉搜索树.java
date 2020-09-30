package 刷题.高频题;

import 标签.树.TreeNode;

/**
 * https://leetcode-cn.com/problems/validate-binary-search-tree/
 *
 * @author zhushuangquan
 */
public class _98_验证二叉搜索树 {
    public boolean isValidBST(TreeNode root) {
        return isValidBST(root, null, null);
    }

    private boolean isValidBST(TreeNode node, Integer min, Integer max) {
        if (node == null) return true;
        if (min != null && node.val <= min) return false;
        if (max != null && node.val >= max) return false;
        if (!isValidBST(node.left, min, node.val)) return false;
        if (!isValidBST(node.right, node.val, max)) return false;
        return true;
    }
}