package 刷题.高频题;

import 标签.树.TreeNode;

/**
 * https://leetcode-cn.com/problems/flatten-binary-tree-to-linked-list/
 *
 * @author zhushuangquan
 */
public class _114_二叉树展开为链表 {
    // 2. 保留后序遍历的上一个节点
    private TreeNode prev;

    public void flatten(TreeNode root) {
        if (root == null) return;
        // 1. 进行后序遍历, 先访问right节点
        flatten(root.right);
        flatten(root.left);
        // 3. 将上一个节点嫁接到当前节点的右边并删除左节点
        if (prev != null) {
            root.right = prev;
            root.left = null;
        }
        // 4. 将遍历到的节点指向上一个节点
        prev = root;
    }
}