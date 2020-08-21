package 题库;

import java.util.Arrays;

/**
 * 给定两个数组，编写一个函数来计算它们的交集。
 *
 * 示例 1:
 *
 * 输入: nums1 = [1,2,2,1], nums2 = [2,2]
 * 输出: [2,2]
 * 示例 2:
 *
 * 输入: nums1 = [4,9,5], nums2 = [9,4,9,8,4]
 * 输出: [4,9]
 * 说明：
 *
 * 输出结果中每个元素出现的次数，应与元素在两个数组中出现的次数一致。
 * 我们可以不考虑输出结果的顺序。
 * 进阶:
 *
 * 如果给定的数组已经排好序呢？你将如何优化你的算法？
 * 如果 nums1 的大小比 nums2 小很多，哪种方法更优？
 * 如果 nums2 的元素存储在磁盘上，磁盘内存是有限的，并且你不能一次加载所有的元素到内存中，你该怎么办？
 *
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/intersection-of-two-arrays-ii/
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 */
public class _350_两个数组的交集II {
    public static int[] intersect(int[] nums1, int[] nums2) {
        // 1. 先将两个数组进行排序
        Arrays.sort(nums1);
        Arrays.sort(nums2);
        // 2. 定义三指针
        int i1 = 0, i2 = 0, cur = 0;
        // 3. 同时遍历两个数组
        while (i1 < nums1.length && i2 < nums2.length) {
            // 4. 如果不相等且小的跳过
            if (nums1[i1] < nums2[i2]) {
                i1++;
            } else if (nums1[i1] > nums2[i2]) {
                i2++;
            } else {
                // 5. 相等则为交集赋值
                nums1[cur++] = nums1[i1];
                i1++;
                i2++;
            }
        }
        // 5. 截断拷贝数组
        return Arrays.copyOfRange(nums1, 0, cur);
    }
}
