package 标签.字符串;

import 标签.树.TreeNode;
import 标签.树.printer.BinaryTreeInfo;
import 标签.树.printer.BinaryTrees;

/**
 * https://leetcode-cn.com/problems/subtree-of-another-tree/
 *
 * @author zhushuangquan
 */
public class _572_另一个树的子树 {
    public static void main(String[] args) {
        TreeNode root = new TreeNode(3);
        root.right = new TreeNode(5);
        root.left = new TreeNode(4);
        root.left.left = new TreeNode(1);
        root.left.right = new TreeNode(2);
        BinaryTrees.println(new BinaryTreeInfo() {
            @Override
            public Object root() {
                return root;
            }

            @Override
            public Object left(Object node) {
                return ((TreeNode) node).left;
            }

            @Override
            public Object right(Object node) {
                return ((TreeNode) node).right;
            }

            @Override
            public Object string(Object node) {
                return ((TreeNode) node).val;
            }
        });
        _572_另一个树的子树 o = new _572_另一个树的子树();
        System.out.println(o.postSerialize(root));
    }

    public boolean isSubtree(TreeNode s, TreeNode t) {
        if (s == null || t == null) return false;
        return contains(postSerialize(s), postSerialize(t));
    }

    private boolean contains(String text, String pattern) {
        char[] textChars = text.toCharArray();
        int tlen = textChars.length;
        if (tlen == 0) return false;
        char[] patternChars = pattern.toCharArray();
        int plen = patternChars.length;
        if (plen == 0) return false;
        if (tlen < plen) return false;

        // next表
        int[] next = next(pattern);

        int pi = 0, ti = 0, lenDelta = tlen - plen;
        while (pi < plen && ti - pi <= lenDelta) {
            if (pi < 0 || textChars[ti] == patternChars[pi]) {
                ti++;
                pi++;
            } else {
                pi = next[pi];
            }
        }
        return pi == plen;
    }

    private int[] next(String pattern) {
        char[] chars = pattern.toCharArray();
        int[] next = new int[chars.length];

        // n == next[i]
        next[0] = -1;
        int i = 0;
        int n = -1;
        int iMax = chars.length - 1;
        while (i < iMax) {
            if (n < 0 || chars[i] == chars[n]) {
                ++i;
                ++n;
                if (chars[i] == chars[n]) {
                    next[i] = next[n];
                } else {
                    next[i] = n;
                }
            } else {
                n = next[n];
            }
        }
        return next;
    }

    /**
     * 利用后序遍历的方式进行序列化
     *
     * @param root 树的根节点
     * @return
     */
    private String postSerialize(TreeNode root) {
        StringBuilder sb = new StringBuilder();
        postSerialize(root, sb);
        return sb.toString();
    }

    private void postSerialize(TreeNode node, StringBuilder sb) {
        if (node.left == null) {
            sb.append("#!");
        } else {
            postSerialize(node.left, sb);
        }
        if (node.right == null) {
            sb.append("#!");
        } else {
            postSerialize(node.right, sb);
        }
        sb.append(node.val).append("!");
    }
}
