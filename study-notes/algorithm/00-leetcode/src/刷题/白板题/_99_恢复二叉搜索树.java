package 刷题.白板题;

import 标签.树.TreeNode;

/**
 * https://leetcode-cn.com/problems/recover-binary-search-tree/
 *
 * @author zhushuangquan
 */
public class _99_恢复二叉搜索树 {
    private TreeNode prev;
    private TreeNode first;
    private TreeNode second;

    public void recoverTree(TreeNode root) {
        findWrongNodes(root);
        int tmp = first.val;
        first.val = second.val;
        second.val = tmp;
    }

    private void findWrongNodes(TreeNode root) {
        if (root == null) return;
        findWrongNodes(root.left);
        find(root);
        findWrongNodes(root.right);
    }

    private void find(TreeNode node) {
        if (prev != null && prev.val > node.val) {
            second = node;
            if (first != null) return;
            first = prev;
        }
        prev = node;
    }
}
