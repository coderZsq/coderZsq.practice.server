package 题库;

import 标签.树.Node;

import java.util.ArrayList;
import java.util.List;

/**
 * 给定一个 N 叉树，返回其节点值的后序遍历。
 *
 * 例如，给定一个 3叉树 :
 *
 *  
 *
 *
 *
 *  
 *
 * 返回其后序遍历: [5,6,3,2,4,1].
 *
 *  
 *
 * 说明: 递归法很简单，你可以使用迭代法完成此题吗?
 *
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/n-ary-tree-postorder-traversal
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 */
public class _590_N叉树的后序遍历 {
    public List<Integer> postorder(Node root) {
        List<Integer> res = new ArrayList<>();
        postorder(root, res);
        return res;
    }

    private void postorder(Node root, List<Integer> res) {
        if (root == null) return;
        int size = root.children.size();
        for (int i = 0; i < size; i++) {
            postorder(root.children.get(i), res);
        }
        res.add(root.val);
    }
}
