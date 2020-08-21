package 题库;

import 标签.树.TreeNode;

/**
 * 给定一个二叉树，判断它是否是高度平衡的二叉树。
 *
 * 本题中，一棵高度平衡二叉树定义为：
 *
 * 一个二叉树每个节点 的左右两个子树的高度差的绝对值不超过1。
 *
 * 示例 1:
 *
 * 给定二叉树 [3,9,20,null,null,15,7]
 *
 *     3
 *    / \
 *   9  20
 *     /  \
 *    15   7
 * 返回 true 。
 *
 * 示例 2:
 *
 * 给定二叉树 [1,2,2,3,3,null,null,4,4]
 *
 *        1
 *       / \
 *      2   2
 *     / \
 *    3   3
 *   / \
 *  4   4
 * 返回 false 。
 *
 *  
 *
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/balanced-binary-tree
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 */
public class _110_平衡二叉树 {
    private int height(TreeNode root) {
        // 这里的 -1 没有实际意义
        if (root == null) return -1;
        return Math.max(height(root.left), height(root.right)) + 1;
    }

    public boolean isBalanced(TreeNode root) {
        if (root == null) return true;
        return Math.abs(height(root.left) - height(root.right)) < 2 && isBalanced(root.left) && isBalanced(root.right);
    }
}

class _110_平衡二叉树2 {
    private boolean flag = true;

    private int maxDepth(TreeNode root) {
        // 1. 遍历到叶子节点的递归基
        if (root == null) {
            return 0;
        } else {
            // 2. 左子树最大深度
            int lh = maxDepth(root.left);
            // 3. 右子树最大深度
            int rh = maxDepth(root.right);
            // 4. 返回左右子树中的最大深度加上根节点的深度 1
            return Math.max(lh, rh) + 1;
        }
    }

    public boolean isBalanced(TreeNode root) {
        inorder(root);
        return flag;
    }

    private void inorder(TreeNode root) {
        if (root == null) return;
        inorder(root.left);
        if (Math.abs(maxDepth(root.left) - maxDepth(root.right)) > 1) {
            flag = false;
            return;
        }
        inorder(root.right);
    }
}
