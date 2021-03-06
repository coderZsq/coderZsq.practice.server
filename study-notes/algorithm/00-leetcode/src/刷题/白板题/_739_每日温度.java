package 刷题.白板题;

import java.util.Stack;

/**
 * https://leetcode-cn.com/problems/daily-temperatures/
 *
 * @author zhushuangquan
 */
public class _739_每日温度 {
    public int[] dailyTemperatures(int[] T) {
        if (T == null || T.length == 0) return null;
        int[] result = new int[T.length];
        Stack<Integer> stack = new Stack<>();
        for (int i = 0; i < T.length; i++) {
            while (!stack.isEmpty() && T[i] > T[stack.peek()]) {
                int topIdx = stack.peek();
                result[topIdx] = i - topIdx;
                stack.pop();
            }
            stack.push(i);
        }
        return result;
    }
}
