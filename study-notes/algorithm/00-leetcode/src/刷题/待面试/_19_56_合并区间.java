package 刷题.待面试;

import java.util.Arrays;
import java.util.Comparator;

/**
 * https://leetcode-cn.com/problems/merge-intervals/
 *
 * @author zhushuangquan
 */
public class _19_56_合并区间 {
    public int[][] merge(int[][] intervals) {
        if (intervals == null || intervals.length == 0) return null;
        Arrays.sort(intervals, Comparator.comparingInt(m -> m[0]));
        int[][] res = new int[intervals.length][2];
        int idx = -1;
        for (int[] interval : intervals) {
            if (idx == -1 || res[idx][1] < interval[0]) {
                res[++idx] = interval;
            } else {
                res[idx][1] = Math.max(res[idx][1], interval[1]);
            }
        }
        return Arrays.copyOf(res, idx + 1);
    }
}
