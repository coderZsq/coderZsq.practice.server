package 标签.树;

import java.util.LinkedList;
import java.util.Queue;
import java.util.Stack;

/**
 * https://leetcode-cn.com/problems/validate-binary-search-tree/
 *
 * @author zhushuangquan
 */
public class _98_验证二叉搜索树 {

    private Queue<TreeNode> nodes = new LinkedList<>();
    private Queue<Integer> mins = new LinkedList<>();
    private Queue<Integer> maxes = new LinkedList<>();

    private void offer(TreeNode node, Integer min, Integer max) {
        if (node == null) return;
        nodes.offer(node);
        mins.offer(min);
        maxes.offer(max);
    }

    public boolean isValidBST(TreeNode root) {
        if (root == null) return true;

        offer(root, null, null);

        while (!nodes.isEmpty()) {
            TreeNode node = nodes.poll();
            Integer min = mins.poll();
            Integer max = maxes.poll();
            if (min != null && node.val <= min) return false;
            if (max != null && node.val >= max) return false;

            offer(node.left, min, node.val);
            offer(node.right, node.val, max);
        }

        return true;
    }

}

class _98_验证二叉搜索树2 {
    public boolean isValidBST1(TreeNode root) {
        return isValidBST(root, null, null);
    }

    private boolean isValidBST(TreeNode root, Integer min, Integer max) {
        if (root == null) return true;
        if (min != null && root.val <= min) return false;
        if (max != null && root.val >= max) return false;
        if (!isValidBST(root.left, min, root.val)) return false;
        if (!isValidBST(root.right, root.val, max)) return false;
        return true;
    }

}
class _98_验证二叉搜索树3 {
    public boolean isValidBST2(TreeNode root) {
        if (root == null) return true;
        Stack<TreeNode> stack = new Stack<>();
        Integer last = null;
        while (true) {
            while (root != null) {
                stack.push(root);
                root = root.left;
            }
            if (stack.isEmpty()) break;
            root = stack.pop();

            if (last != null && root.val <= last) return false;
            last = root.val;

            root = root.right;
        }
        return true;
    }
}

class _98_验证二叉搜索树4 {
    private Integer last;

    public boolean isValidBST3(TreeNode root) {
        if (root == null) return true;

        if (!isValidBST3(root.left)) return false;

        if (last != null && root.val <= last) return false;
        last = root.val;

        if (!isValidBST3(root.right)) return false;

        return true;
    }
}
