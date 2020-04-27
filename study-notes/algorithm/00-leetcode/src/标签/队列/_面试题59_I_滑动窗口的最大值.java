package 标签.队列;

import java.util.Deque;
import java.util.LinkedList;

/**
 * https://leetcode-cn.com/problems/hua-dong-chuang-kou-de-zui-da-zhi-lcof/
 *
 * @author zhushuangquan
 */
public class _面试题59_I_滑动窗口的最大值 {
    public int[] maxSlidingWindow(int[] nums, int k) {
        if (nums == null || nums.length == 0 || k < 1) return new int[0];
        if (k == 1) return nums;

        int[] maxes = new int[nums.length - k + 1];
        // peek: 取值 (偷偷瞥一眼)
        // poll: 删除 (削)
        // offer: 添加 (入队)
        Deque<Integer> deque = new LinkedList<>();
        for (int i = 0; i < nums.length; i++) {
            // 只要nums[队尾] <= nums[i], 就删除队尾
            while (!deque.isEmpty() && nums[i] >= nums[deque.peekLast()]) {
                deque.pollLast();
            }
            // 将i加到队尾
            deque.offerLast(i);
            // 检查窗口的索引是否合法
            int w = i - k + 1;
            if (w < 0) continue;
            // 检查队头的合法性
            if (deque.peekFirst() < w) {
                // 队头不合法(失效, 不在滑动窗口索引范围内)
                deque.pollFirst();
            }
            // 设置窗口的最大值
            maxes[w] = nums[deque.peekFirst()];
        }
        return maxes;
    }
}
