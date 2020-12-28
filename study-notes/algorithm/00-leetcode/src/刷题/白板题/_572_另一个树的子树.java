package 刷题.白板题;

import 标签.树.TreeNode;

/**
 * https://leetcode-cn.com/problems/subtree-of-another-tree/
 *
 * @author zhushuangquan
 */
public class _572_另一个树的子树 {
    public boolean isSubtree(TreeNode s, TreeNode t) {
        if (s == null || t == null) return false;
        return postSerialize(s).contains(postSerialize(t));
    }

    private String postSerialize(TreeNode root) {
        StringBuilder sb = new StringBuilder();
        postSerialize(root, sb);
        return sb.toString();
    }

    private void postSerialize(TreeNode node, StringBuilder sb) {
        if (node.left == null) {
            sb.append("#!");
        } else {
            postSerialize(node.left, sb);
        }
        if (node.right == null) {
            sb.append("#!");
        } else {
            postSerialize(node.right, sb);
        }
        sb.append(node.val).append("!");
    }
}
