package 题库;

import java.util.Deque;
import java.util.LinkedList;

/**
 * https://leetcode-cn.com/problems/sliding-window-maximum/
 *
 * @author zhushuangquan
 */
public class _239_滑动窗口最大值 {
    public int[] maxSlidingWindow(int[] nums, int k) {
        if (nums == null || nums.length == 0 || k < 1) return new int[0];
        if (k == 1) return nums;

        int[] maxes = new int[nums.length - k + 1];
        // 当前滑动窗口的最大值索引
        int maxIdx = 0;
        // 求出前k个元素的最大值索引
        for (int i = 1; i < k; i++) {
            if (nums[i] > nums[maxIdx]) maxIdx = i;
        }
        // li是滑动窗口的最左索引
        for (int li = 0; li < maxes.length; li++) {
            // ri是滑动窗口的最右索引
            int ri = li + k - 1;
            if (maxIdx < li) { // 最大值的索引不在滑动窗口的合理范围内
                // 求求出[li, ri]范围内最大的索引
                maxIdx = li;
                for (int i = li + 1; i <= ri; i++) {
                    if (nums[i] > nums[maxIdx]) maxIdx = i;
                }
            } else if (nums[ri] >= nums[maxIdx]) { // 最大值的索引在滑动窗口的合理范围内
                maxIdx = ri;
            }
            maxes[li] = nums[maxIdx];
        }
        return maxes;
    }
}

class _239_滑动窗口最大值2 {
    public int[] maxSlidingWindow1(int[] nums, int k) {
        if (nums == null || nums.length == 0 || k < 1) return new int[0];
        if (k == 1) return nums;

        int[] maxes = new int[nums.length - k + 1];
        // peek: 取值 (偷偷瞥一眼)
        // poll: 删除 (削)
        // offer: 添加 (入队)
        Deque<Integer> deque = new LinkedList<>();
        for (int ri = 0; ri < nums.length; ri++) {
            // 只要nums[队尾] <= nums[ri], 就删除队尾
            while (!deque.isEmpty() && nums[ri] >= nums[deque.peekLast()]) {
                deque.pollLast();
            }
            // 将i加到队尾
            deque.offerLast(ri);
            // 检查窗口的索引是否合法
            int li = ri - k + 1;
            if (li < 0) continue;
            // 检查队头的合法性
            if (deque.peekFirst() < li) {
                // 队头不合法(失效, 不在滑动窗口索引范围内)
                deque.pollFirst();
            }
            // 设置窗口的最大值
            maxes[li] = nums[deque.peekFirst()];
        }
        return maxes;
    }
}
