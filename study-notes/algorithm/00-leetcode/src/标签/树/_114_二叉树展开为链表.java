package 标签.树;

import java.util.Stack;

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

class _114_二叉树展开为链表2 {
    public void flatten(TreeNode root) {
        if (root == null) return;

        if (root.left != null) {
            // 保留之前的right
            TreeNode oldRight = root.right;
            // 将left嫁接到right
            root.right = root.left;
            // 清空left
            root.left = null;
            // 将旧的right嫁接到root的最右下角
            TreeNode rightMost = root;
            while (rightMost.right != null) {
                rightMost = rightMost.right;
            }
            rightMost.right = oldRight;
        }

        flatten(root.right);
    }
}

class _114_二叉树展开为链表3 {
    public void flatten(TreeNode root) {
        while (root != null) {
            if (root.left != null) {
                // 保留之前的right
                TreeNode oldRight = root.right;
                // 将left嫁接到right
                root.right = root.left;
                // 清空left
                root.left = null;
                // 将旧的right嫁接到root的最右下角
                TreeNode rightMost = root;
                while (rightMost.right != null) {
                    rightMost = rightMost.right;
                }
                rightMost.right = oldRight;
            }
            root = root.right;
        }
    }
}

class _114_二叉树展开为链表4 {
    public void flatten(TreeNode root) {
        if (root == null) return;

        Stack<TreeNode> stack = new Stack<>();
        stack.push(root);

        TreeNode prev = null;
        while (!stack.isEmpty()) {
            TreeNode top = stack.peek();
            // 如果栈顶节点是叶子节点
            boolean leaf = (top.left == null) && (top.right == null);
            // 如果上一次访问的节点是栈顶节点的子节点
            boolean preIsChild = (prev != null) && ((prev == top.left) || (prev == top.right));
            if (leaf || preIsChild) {
                if (prev != null) {
                    top.right = prev;
                    top.left = null;
                }
                prev = top;

                // 弹出栈顶节点
                stack.pop();

                // 标记为已访问
                prev = top;
            } else {
                if (top.left != null) {
                    stack.push(top.left);
                }
                if (top.right != null) {
                    stack.push(top.right);
                }
            }
        }
    }
}
