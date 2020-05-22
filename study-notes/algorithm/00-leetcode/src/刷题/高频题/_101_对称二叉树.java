package 刷题.高频题;

import 标签.树.TreeNode;

/**
 * 给定一个二叉树，检查它是否是镜像对称的。
 *
 *  
 *
 * 例如，二叉树 [1,2,2,3,4,4,3] 是对称的。
 *
 *     1
 *    / \
 *   2   2
 *  / \ / \
 * 3  4 4  3
 *  
 *
 * 但是下面这个 [1,2,2,null,3,null,3] 则不是镜像对称的:
 *
 *     1
 *    / \
 *   2   2
 *    \   \
 *    3    3
 *  
 *
 * 进阶：
 *
 * 你可以运用递归和迭代两种方法解决这个问题吗？
 *
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/symmetric-tree
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 */
public class _101_对称二叉树 {
    public boolean isSymmetric(TreeNode root) {
        // 1. 如果根节点为空则判定为对称二叉树
        if (root == null) return true;
        // 2. 定义函数判定左右子树是否互为镜像
        return isMirror(root.left, root.right);
    }

    private boolean isMirror(TreeNode left, TreeNode right) {
        // 3. 如果左右子树同时为空, 判定为对称
        if (left == null && right == null) return true;
        // 4. 如果左右子树只有一边为空, 判定为不对称
        if (left == null || right == null) return false;
        // 5. 判定 节点值相等, 左子树的左边和右子树的右边互为镜像, 右子树的左边和左子树的右边互为镜像
        return (left.val == right.val) && isMirror(left.left, right.right) && isMirror(left.right, right.left);
    }
}
