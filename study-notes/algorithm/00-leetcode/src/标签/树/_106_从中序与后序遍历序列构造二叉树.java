package 标签.树;

/**
 * 根据一棵树的中序遍历与后序遍历构造二叉树。
 *
 * 注意:
 * 你可以假设树中没有重复的元素。
 *
 * 例如，给出
 *
 * 中序遍历 inorder = [9,3,15,20,7]
 * 后序遍历 postorder = [9,15,7,20,3]
 * 返回如下的二叉树：
 *
 *     3
 *    / \
 *   9  20
 *     /  \
 *    15   7
 *
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/construct-binary-tree-from-inorder-and-postorder-traversal
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 */
public class _106_从中序与后序遍历序列构造二叉树 {
    public TreeNode recursive(int[] inorder, int[] postorder) {
        return recursive(inorder, 0, inorder.length - 1, postorder, 0, postorder.length - 1);
    }

    public TreeNode recursive(int[] inorder, int iStart, int iEnd, int[] postorder, int pStart, int pEnd) {
        if (iStart > iEnd || pStart > pEnd) return null;
        TreeNode root = new TreeNode(postorder[pEnd]);
        if (pEnd ==  pStart) return root;
        int i = iEnd;
        while (i >= iStart) {
            if (inorder[i] == postorder[pEnd]) break;
            i--;
        }
        root.left = recursive(inorder, iStart, i - 1, postorder, pStart, pStart + (i -iStart - 1) );
        root.right = recursive(inorder, i + 1, iEnd, postorder, pStart + (i -iStart), pEnd - 1);
        return root;
    }
}
