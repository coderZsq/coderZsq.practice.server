package 刷题.待面试;

import java.util.PriorityQueue;

/**
 * https://leetcode-cn.com/problems/kth-largest-element-in-an-array/
 *
 * @author zhushuangquan
 */
class _23_215_数组中的第K个最大元素 {
    public int findKthLargest(int[] nums, int k) {
        if (nums == null || nums.length == 0) return 0;
        PriorityQueue<Integer> queue = new PriorityQueue<>();
        for (int i = 0; i < nums.length; i++) {
            if (queue.size() < k) {
                queue.offer(nums[i]);
            } else if (queue.peek() < nums[i]) {
                queue.poll();
                queue.offer(nums[i]);
            }
        }
        return queue.peek();
    }
}
