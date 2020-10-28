package 标签.哈希表;

import java.util.*;

/**
 * 给你一个整数数组 arr，请你帮忙统计数组中每个数的出现次数。
 *
 * 如果每个数的出现次数都是独一无二的，就返回 true；否则返回 false。
 *
 *  
 *
 * 示例 1：
 *
 * 输入：arr = [1,2,2,1,1,3]
 * 输出：true
 * 解释：在该数组中，1 出现了 3 次，2 出现了 2 次，3 只出现了 1 次。没有两个数的出现次数相同。
 * 示例 2：
 *
 * 输入：arr = [1,2]
 * 输出：false
 * 示例 3：
 *
 * 输入：arr = [-3,0,1,-3,1,1,1,-3,10,0]
 * 输出：true
 *  
 *
 * 提示：
 *
 * 1 <= arr.length <= 1000
 * -1000 <= arr[i] <= 1000
 *
 *
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/unique-number-of-occurrences
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 */
public class _1207_独一无二的出现次数 {
    public boolean uniqueOccurrences(int[] arr) {
        // 进行排序
        Arrays.sort(arr);
        Set<Integer> set = new HashSet<>();

        // 默认出现1
        int count = 1;
        // 遍历有序数组
        for (int i = 1; i < arr.length; i++) {
            // 如果前后不相等 意味着出现新的数
            if (arr[i - 1] != arr[i]) {
                // 将新数之前的数的个数加入set, 如果不能加入 判定为false
                if (!set.add(count)) {
                    return false;
                }
                // 可以加入重置count
                count = 1;
            } else {
                // 相同数字进行累加
                count++;
            }
        }
        // 最后一个数字进行判断
        if (!set.add(count)) {
            return false;
        }
        return true;
    }
}

class _1207_独一无二的出现次数2 {
    public boolean uniqueOccurrences(int[] arr) {
        // key 为出现的数, value为出现的次数
        Map<Integer, Integer> map = new HashMap<>();
        for (int x : arr) {
            map.put(x, map.getOrDefault(x, 0) + 1);
        }
        // 出现的次数集合 set用于去重
        Set<Integer> set = new HashSet<>();
        for (Map.Entry<Integer, Integer> x: map.entrySet()) {
            set.add(x.getValue());
        }
        return map.size() == set.size();
    }
}
