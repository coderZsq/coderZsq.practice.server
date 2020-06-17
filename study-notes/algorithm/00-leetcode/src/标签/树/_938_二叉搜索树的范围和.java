package 标签.树;

/**
 * 给定二叉搜索树的根结点 root，返回 L 和 R（含）之间的所有结点的值的和。
 *
 * 二叉搜索树保证具有唯一的值。
 *
 *  
 *
 * 示例 1：
 *
 * 输入：root = [10,5,15,3,7,null,18], L = 7, R = 15
 * 输出：32
 * 示例 2：
 *
 * 输入：root = [10,5,15,3,7,13,18,1,null,6], L = 6, R = 10
 * 输出：23
 *  
 *
 * 提示：
 *
 * 树中的结点数量最多为 10000 个。
 * 最终的答案保证小于 2^31。
 *
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/range-sum-of-bst
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 */
public class _938_二叉搜索树的范围和 {
    public int rangeSumBST(TreeNode root, int L, int R) {
        if (root == null) return 0;
        if (root.val >= L && root.val <= R) {
            // 分治
            return root.val + rangeSumBST(root.left, L, R) + rangeSumBST(root.right, L, R);
        } else if (root.val < L) {
            // 如果小于左范围, 则往右子树查找
            return rangeSumBST(root.right, L, R);
        } else {
            // 如果大于右范围, 则往左子树查找
            return rangeSumBST(root.left, L, R);
        }
    }
}

class _938_二叉搜索树的范围和2 {
    private int res = 0;
    public int rangeSumBST(TreeNode root, int L, int R) {
        inorder(root, L, R);
        return res;
    }

    private void inorder(TreeNode root, int L, int R) {
        if (root == null) return;
        inorder(root.left, L, R);
        if (root.val >= L && root.val <= R) {
            res += root.val;
        }
        inorder(root.right, L, R);
    }
}
