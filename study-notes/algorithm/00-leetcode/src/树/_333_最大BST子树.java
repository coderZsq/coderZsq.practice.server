package 树;

/**
 * https://leetcode-cn.com/problems/largest-bst-subtree/
 *
 * @author zhushuangquan
 */
public class _333_最大BST子树 {
    private boolean isBST(TreeNode root) {
        return false;
    }

    private int nodesCount(TreeNode root) {
        return 0;
    }

    public int largestBSTSubtree(TreeNode root) {
        if (root == null) return 0;
        if (isBST(root)) return nodesCount(root);
        return Math.max(largestBSTSubtree(root.left), largestBSTSubtree(root.right));
    }
}
