package 刷题.白板题;

import 标签.树.TreeNode;

/**
 * https://leetcode-cn.com/problems/flatten-binary-tree-to-linked-list/
 *
 * @author zhushuangquan
 */
public class _114_二叉树展开为链表 {
    private TreeNode prev;
    public void flatten(TreeNode root) {
        if (root == null) return;
        flatten(root.right);
        flatten(root.left);
        if (prev != null) {
            root.right = prev;
            root.left = null;
        }
        prev = root;
    }
}