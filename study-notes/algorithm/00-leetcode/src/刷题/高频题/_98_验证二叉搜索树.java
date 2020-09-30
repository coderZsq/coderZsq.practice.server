package 刷题.高频题;

import 标签.树.TreeNode;

/**
 * https://leetcode-cn.com/problems/validate-binary-search-tree/
 *
 * @author zhushuangquan
 */
public class _98_验证二叉搜索树 {

    public boolean isValidBST(TreeNode root) {
        // 1. 当前的root的节点没有上下界, 所以传入null
        return isValidBST(root, null, null);
    }

    /**
     *
     * @param node 当前遍历到的节点
     * @param min 当前遍历到的节点的下界
     * @param max 当前遍历到的节点的上界
     * @return
     */
    private boolean isValidBST(TreeNode node, Integer min, Integer max) {
        // 2. 当遍历到的节点为空时 表示遍历结束
        if (node == null) return true;
        // 3. 判断上下界的边界条件
        if (min != null && node.val <= min) return false;
        if (max != null && node.val >= max) return false;
        // 4. 开始遍历树, 传入边界条件 如果子树判断非二叉搜索树则提前结束
        // 4.1 左子树的上界为遍历到的节点的值
        // 4.2 右子树的下界为遍历到的节点的值
        if (!isValidBST(node.left, min, node.val)) return false;
        if (!isValidBST(node.right, node.val, max)) return false;
        // 5. 全部验证通过 则表示验证二叉搜索树成功
        return true;
    }
}