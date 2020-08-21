package 题库;

import 标签.树.TreeNode;

/**
 * 给你一棵所有节点为非负值的二叉搜索树，请你计算树中任意两节点的差的绝对值的最小值。
 *
 *
 *
 * 示例：
 *
 * 输入：
 *
 * 1
 *  \
 *   3
 *  /
 * 2
 *
 * 输出：
 * 1
 *
 * 解释：
 * 最小绝对差为 1，其中 2 和 1 的差的绝对值为 1（或者 2 和 3）。
 *
 *
 * 提示：
 *
 * 树中至少有 2 个节点。
 * 本题与 783 https://leetcode-cn.com/problems/minimum-distance-between-bst-nodes/ 相同
 *
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/minimum-absolute-difference-in-bst/
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 */
public class _530_二叉搜索树的最小绝对差 {
    private Integer min = Integer.MAX_VALUE;
    private Integer prev = null;

    public int getMinimumDifference(TreeNode root) {
        inorder(root);
        return min;
    }

    public void inorder(TreeNode node) {
        if (node == null) return;
        inorder(node.left);
        // 中序遍历, 比较前一个节点和当前节点的差值
        if (prev != null) {
            min = Math.min(min, node.val - prev);
        }
        prev = node.val;
        inorder(node.right);
    }
}
