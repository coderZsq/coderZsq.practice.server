package 题库;

import java.util.ArrayList;
import java.util.List;

/**
 * 给出一个无重叠的 ，按照区间起始端点排序的区间列表。
 *
 * 在列表中插入一个新的区间，你需要确保列表中的区间仍然有序且不重叠（如果有必要的话，可以合并区间）。
 *
 *  
 *
 * 示例 1：
 *
 * 输入：intervals = [[1,3],[6,9]], newInterval = [2,5]
 * 输出：[[1,5],[6,9]]
 * 示例 2：
 *
 * 输入：intervals = [[1,2],[3,5],[6,7],[8,10],[12,16]], newInterval = [4,8]
 * 输出：[[1,2],[3,10],[12,16]]
 * 解释：这是因为新的区间 [4,8] 与 [3,5],[6,7],[8,10] 重叠。
 *  
 *
 * 注意：输入类型已在 2019 年 4 月 15 日更改。请重置为默认代码定义以获取新的方法签名。
 *
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/insert-interval
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 */

public class _57_插入区间 {
    public int[][] insert(int[][] intervals, int[] newInterval) {
        int len = intervals.length;
        //如果原数组没有元素，或最后一个区间的终点小于新区间的起点，则将新区间添加到末尾
        if (len == 0 || newInterval[0] > intervals[len - 1][1]) {
            int[][] ret = new int[len + 1][];
            ret[len] = newInterval;
            for (int i = 0; i < len; i++)
                ret[i] = intervals[i];
            return ret;
        }
        //如果第一个区间的起点大于新区间的终点，则将新区间添加到开头
        if (newInterval[1] < intervals[0][0]) {
            int[][] ret = new int[len + 1][];
            ret[0] = newInterval;
            for (int i = 1; i < len + 1; i++)
                ret[i] = intervals[i - 1];
            return ret;
        }
        //否则，先找到两个特殊的下标：left和right，left左边的区间和right右边的区间肯定不与newInterval相交
        int left = 0, right = len - 1;
        while (intervals[left][1] < newInterval[0])
            left++;
        while (intervals[right][0] > newInterval[1])
            right--;
        //然后对从left到right的区间以及新区间进行合并，合并后的区间的起点分别为begin和end，计算方式如下所示
        int begin = Math.min(intervals[left][0], newInterval[0]);
        int end = Math.max(intervals[right][1], newInterval[1]);
        //依次把left左边的所有区间、合并后的区间、right右边的所有区间添加到新的数组中
        int[][] ret = new int[len - right + left][];
        int i = 0;
        while (i < left) {
            ret[i] = intervals[i];
            i++;
        }
        ret[i++] = new int[]{begin, end};
        while (i < ret.length) {
            ret[i] = intervals[i - left + right];
            i++;
        }
        return ret;
    }
}


class _57_插入区间2 {
    public int[][] insert(int[][] intervals, int[] newInterval) {
        // 新插入区间的起始
        int begin = newInterval[0];
        // 新插入区间的结束
        int end = newInterval[1];
        // 标记是否已添加新区间
        boolean placed = false;
        // 结果列表
        List<int[]> list = new ArrayList<>();
        // 遍历原始区间
        for (int[] interval : intervals) {
            // 如果原始的起点大于新插入的结束, 在插入区间的右侧且无交集
            // [[1,2],[3,5],[6,7],[8,10],[12,16]] , [4,8]
            if (end < interval[0]) {
                if (!placed) {
                    // 列表添加新区间
                    list.add(new int[]{begin, end});
                    placed = true;
                }
                // 添加原始间隔
                list.add(interval);
            } else if (begin > interval[1]) {
                // 在插入区间的左侧且无交集
                list.add(interval);
            } else {
                // 与插入区间有交集, 计算它们的并集
                begin = Math.min(begin, interval[0]);
                end = Math.max(end, interval[1]);
            }
        }
        if (!placed) {
            list.add(new int[]{begin, end});
        }
        int[][] ret = new int[list.size()][2];
        for (int i = 0; i < list.size(); i++) {
            ret[i] = list.get(i);
        }
        return ret;
    }
}
