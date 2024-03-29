package 标签.树;

import java.util.Arrays;
import java.util.Stack;

/**
 * https://leetcode-cn.com/problems/maximum-binary-tree/
 *
 * @author zhushuangquan
 */
public class _654_最大二叉树 {
    public static void main(String[] args) {
        _654_最大二叉树 o = new _654_最大二叉树();
        int[] nums = {3, 2, 1, 6, 0, 5};
        System.out.println(Arrays.toString(o.parentIndexes(nums)));
    }

    public TreeNode constructMaximumBinaryTree(int[] nums) {
        if (nums == null) return null;
        return findRoot(nums, 0, nums.length);
    }

    /**
     * 找出[l, r)范围的根节点
     */
    private TreeNode findRoot(int[] nums, int l, int r) {
        if (l == r) return null;

        // 找出[l, r)范围内最大值的索引
        int maxIdx = l;
        for (int i = l + 1; i < r; i++) {
            if (nums[i] > nums[maxIdx]) maxIdx = i;
        }
        TreeNode root = new TreeNode(nums[maxIdx]);
        root.left = findRoot(nums, l, maxIdx);
        root.right = findRoot(nums, maxIdx + 1, r);
        return root;
    }

    /**
     * 题目变种
     *
     * 返回一个数组, 数组里面存着每个节点的父节点的索引 (如果没有父节点, 就存-1)
     */
    public int[] parentIndexes(int[] nums) {
        if (nums == null || nums.length == 0) return null;
        /*
         * 1. 扫描一遍所有的元素
         * 2. 保持栈从栈底到栈顶是单调递减的
         * */

        int[] lis = new int[nums.length];
        int[] ris = new int[nums.length];
        Stack<Integer> stack = new Stack<>();
        // 初始化
        for (int i = 0; i < nums.length; i++) {
            ris[i] = -1;
            lis[i] = -1;
        }
        for (int i = 0; i < nums.length; i++) {
            while (!stack.isEmpty() && nums[i] > nums[stack.peek()]) {
                ris[stack.pop()] = i;
            }
            if (!stack.isEmpty()) {
                lis[i] = stack.peek();
            }
            stack.push(i);
        }

        int[] pis = new int[nums.length];
        for (int i = 0; i < pis.length; i++) {
            if (lis[i] == -1 && ris[i] == -1) {
                // i位置是根节点
                pis[i] = -1;
                continue;
            }
            if (lis[i] == -1) {
                pis[i] = ris[i];
            } else if (ris[i] == -1) {
                pis[i] = lis[i];
            } else if (nums[lis[i]] < nums[ris[i]]) {
                pis[i] = lis[i];
            } else {
                pis[i] = ris[i];
            }
        }
        return pis;
    }
}
