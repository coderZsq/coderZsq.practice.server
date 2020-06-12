package 标签.树;

/**
 *
 * 给定一个二叉树，找出其最大深度。
 *
 * 二叉树的深度为根节点到最远叶子节点的最长路径上的节点数。
 *
 * 说明: 叶子节点是指没有子节点的节点。
 *
 * 示例：
 * 给定二叉树 [3,9,20,null,null,15,7]，
 *
 *  3
 * / \
 * 9  20
 * /  \
 * 15   7
 * 返回它的最大深度 3 。
 *
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/maximum-depth-of-binary-tree
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 */
public class _104_二叉树的最大深度 {
    public int maxDepth(TreeNode root) {
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
}
