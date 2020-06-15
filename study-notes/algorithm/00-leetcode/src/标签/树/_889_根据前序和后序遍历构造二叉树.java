package 标签.树;

/**
 * 返回与给定的前序和后序遍历匹配的任何二叉树。
 *
 *  pre 和 post 遍历中的值是不同的正整数。
 *
 *  
 *
 * 示例：
 *
 * 输入：pre = [1,2,4,5,3,6,7], post = [4,5,2,6,7,3,1]
 * 输出：[1,2,3,4,5,6,7]
 *  
 *
 * 提示：
 *
 * 1 <= pre.length == post.length <= 30
 * pre[] 和 post[] 都是 1, 2, ..., pre.length 的排列
 * 每个输入保证至少有一个答案。如果有多个答案，可以返回其中一个。
 *
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/construct-binary-tree-from-preorder-and-postorder-traversal
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 */
public class _889_根据前序和后序遍历构造二叉树 {
    int[] pre, post;
    public TreeNode constructFromPrePost(int[] pre, int[] post) {
        this.pre = pre;
        this.post = post;
        return recursive(0, 0, pre.length);
    }

    public TreeNode recursive(int i0, int i1, int N) {
        if (N == 0) return null;
        TreeNode root = new TreeNode(pre[i0]);
        if (N == 1) return root;

        int L = 1;
        for (; L < N; ++L)
            if (post[i1 + L-1] == pre[i0 + 1])
                break;

        root.left = recursive(i0+1, i1, L);
        root.right = recursive(i0+L+1, i1+L, N-1-L);
        return root;
    }
}
