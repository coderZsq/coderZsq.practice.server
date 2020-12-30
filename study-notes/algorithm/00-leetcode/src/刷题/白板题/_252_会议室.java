package 刷题.白板题;

import java.util.Arrays;
import java.util.Comparator;

/**
 * https://leetcode-cn.com/problems/meeting-rooms/
 *
 * @author zhushuangquan
 */
public class _252_会议室 {
    public boolean canAttendMeetings(int[][] intervals) {
        if (intervals == null || intervals.length == 0) return true;
        Arrays.sort(intervals, Comparator.comparingInt(m -> m[0]));
        for (int i = 1; i < intervals.length; i++) {
            if (intervals[i - 1][1] > intervals[i][0]) return false;
        }
        return true;
    }
}
