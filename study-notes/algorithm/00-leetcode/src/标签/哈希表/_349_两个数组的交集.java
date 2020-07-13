package 标签.哈希表;

import java.util.Arrays;
import java.util.HashSet;

/**
 * 给定两个数组，编写一个函数来计算它们的交集。
 *
 *  
 *
 * 示例 1：
 *
 * 输入：nums1 = [1,2,2,1], nums2 = [2,2]
 * 输出：[2]
 * 示例 2：
 *
 * 输入：nums1 = [4,9,5], nums2 = [9,4,9,8,4]
 * 输出：[9,4]
 *  
 *
 * 说明：
 *
 * 输出结果中的每个元素一定是唯一的。
 * 我们可以不考虑输出结果的顺序。
 *
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/intersection-of-two-arrays
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 */
public class _349_两个数组的交集 {
    public int[] intersection(HashSet<Integer> set1, HashSet<Integer> set2) {
        int[] output = new int[set1.size()];
        int idx = 0;
        for (Integer v : set1) {
            if (set2.contains(v)) {
                output[idx++] = v;
            }
        }
        return Arrays.copyOf(output, idx);
    }

    public int[] intersection(int[] nums1, int[] nums2) {
        HashSet<Integer> set1 = new HashSet<>();
        for (Integer v : nums1) {
            set1.add(v);
        }
        HashSet<Integer> set2 = new HashSet<>();
        for (Integer v : nums2) {
            set2.add(v);
        }
        if (set1.size() < set2.size()) {
            return intersection(set1, set2);
        } else {
            return intersection(set2, set1);
        }
    }

}
