package 题库;

import 标签.树.TreeNode;

/**
 * 给定一个二叉搜索树的根节点 root 和一个值 key，删除二叉搜索树中的 key 对应的节点，并保证二叉搜索树的性质不变。返回二叉搜索树（有可能被更新）的根节点的引用。
 *
 * 一般来说，删除节点可分为两个步骤：
 *
 * 首先找到需要删除的节点；
 * 如果找到了，删除它。
 * 说明： 要求算法时间复杂度为 O(h)，h 为树的高度。
 *
 * 示例:
 *
 * root = [5,3,6,2,4,null,7]
 * key = 3
 *
 *     5
 *    / \
 *   3   6
 *  / \   \
 * 2   4   7
 *
 * 给定需要删除的节点值是 3，所以我们首先找到 3 这个节点，然后删除它。
 *
 * 一个正确的答案是 [5,4,6,2,null,null,7], 如下图所示。
 *
 *     5
 *    / \
 *   4   6
 *  /     \
 * 2       7
 *
 * 另一个正确答案是 [5,2,6,null,4,null,7]。
 *
 *     5
 *    / \
 *   2   6
 *    \   \
 *     4   7
 *
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/delete-node-in-a-bst
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 */
public class _450_删除二叉搜索树中的节点 {
    // 获取root的后继节点
    public int successor(TreeNode root) {
        root = root.right;
        while (root.left != null) root = root.left;
        return root.val;
    }

    // 获取root的前驱节点
    public int predecessor(TreeNode root) {
        root = root.left;
        while (root.right != null) root = root.right;
        return root.val;
    }

    public TreeNode deleteNode(TreeNode root, int key) {
        if (root == null) return null;
        // 当key值大约转入节点的值, 则说明要在右子树中查询, 反之则左子树中查询
        if (key > root.val) {
            root.right = deleteNode(root.right, key);
        } else if (key < root.val) {
            root.left = deleteNode(root.left, key);;
        } else {
            // 找到需要删除的节点
            // 1. 如果是叶子节点, 则直接删除
            if (root.left == null && root.right == null) {
                root = null;
            } else if (root.right != null) {
                // 2. 如果该节点的右节点有值
                // 2.1 找到后继的值覆盖
                root.val = successor(root);
                // 2.2 往右子树寻找, 并删除后继的值
                root.right = deleteNode(root.right, root.val);
            } else {
                // 3. 如果该节点的左节点有值
                // 3.1 找到前驱的值覆盖
                root.val = predecessor(root);
                // 3.1 往左子树寻找, 并删除前驱的值
                root.left = deleteNode(root.left, root.val);
            }
        }
        return root;
    }
}
