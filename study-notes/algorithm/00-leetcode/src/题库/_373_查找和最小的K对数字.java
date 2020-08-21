package 题库;

import java.util.ArrayList;
import java.util.List;
import java.util.PriorityQueue;

/**
 * 给定两个以升序排列的整形数组 nums1 和 nums2, 以及一个整数 k。
 *
 * 定义一对值 (u,v)，其中第一个元素来自 nums1，第二个元素来自 nums2。
 *
 * 找到和最小的 k 对数字 (u1,v1), (u2,v2) ... (uk,vk)。
 *
 * 示例 1:
 *
 * 输入: nums1 = [1,7,11], nums2 = [2,4,6], k = 3
 * 输出: [1,2],[1,4],[1,6]
 * 解释: 返回序列中的前 3 对数：
 *      [1,2],[1,4],[1,6],[7,2],[7,4],[11,2],[7,6],[11,4],[11,6]
 * 示例 2:
 *
 * 输入: nums1 = [1,1,2], nums2 = [1,2,3], k = 2
 * 输出: [1,1],[1,1]
 * 解释: 返回序列中的前 2 对数：
 *      [1,1],[1,1],[1,2],[2,1],[1,2],[2,2],[1,3],[1,3],[2,3]
 * 示例 3:
 *
 * 输入: nums1 = [1,2], nums2 = [3], k = 3
 * 输出: [1,3],[2,3]
 * 解释: 也可能序列中所有的数对都被返回:[1,3],[2,3]
 *
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/find-k-pairs-with-smallest-sums
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 */
public class _373_查找和最小的K对数字 {
    public static List<List<Integer>> kSmallestPairs(int[] nums1, int[] nums2, int k) {
        PriorityQueue<List<Integer>> heap = new PriorityQueue<>(k, (o1, o2) -> (o2.get(0) + o2.get(1)) - (o1.get(0) + o1.get(1)));
        int size1 = Math.min(nums1.length, k);
        int size2 = Math.min(nums2.length, k);
        for (int i = 0; i < size1; i++) {
            for (int j = 0; j < size2; j++) {
                if (heap.size() < k) {
                    List<Integer> pair = new ArrayList<>();
                    pair.add(nums1[i]);
                    pair.add(nums2[j]);
                    heap.offer(pair);
                } else if (nums1[i] + nums2[j] < heap.peek().get(0) + heap.peek().get(1)) {
                    heap.poll();
                    List<Integer> pair = new ArrayList<>();
                    pair.add(nums1[i]);
                    pair.add(nums2[j]);
                    heap.offer(pair);
                }
            }
        }
        List<List<Integer>> res = new ArrayList<>();
        while (!heap.isEmpty()) {
            res.add(0, heap.poll());
        }
        return res;
    }
}
