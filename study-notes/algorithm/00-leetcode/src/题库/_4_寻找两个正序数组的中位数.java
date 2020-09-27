package 题库;

import java.util.ArrayList;
import java.util.List;

/**
 * 给定两个大小为 m 和 n 的正序（从小到大）数组 nums1 和 nums2。
 *
 * 请你找出这两个正序数组的中位数，并且要求算法的时间复杂度为 O(log(m + n))。
 *
 * 你可以假设 nums1 和 nums2 不会同时为空。
 *
 *  
 *
 * 示例 1:
 *
 * nums1 = [1, 3]
 * nums2 = [2]
 *
 * 则中位数是 2.0
 * 示例 2:
 *
 * nums1 = [1, 2]
 * nums2 = [3, 4]
 *
 * 则中位数是 (2 + 3)/2 = 2.5
 *
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/median-of-two-sorted-arrays
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 */
public class _4_寻找两个正序数组的中位数 {
    public static double findMedianSortedArrays(int[] nums1, int[] nums2) {
        int len1 = nums1.length;
        int len2 = nums2.length;
        int totalLens = len1 + len2;
        int midIdx = totalLens >> 1;
        boolean odd = (totalLens & 1) == 1;

        List<Integer> list = new ArrayList<>();
        int i1 = 0;
        int i2 = 0;
        while (i1 < nums1.length && i2 < nums2.length && list.size() < midIdx + 1) {
            if (nums1[i1] < nums2[i2]) {
                list.add(nums1[i1++]);
            } else {
                list.add(nums2[i2++]);
            }
        }

        if (i1 == len1) {
            while (i2 < len2 && list.size() < midIdx + 1) {
                list.add(nums2[i2++]);
            }
        } else if (i2 == len2) {
            while (i1 < len1 && list.size() < midIdx + 1) {
                list.add(nums1[i1++]);
            }
        }

        if (odd) {
            return (double) list.get(midIdx);
        } else {
            return  (list.get(midIdx) + list.get(midIdx - 1)) * 0.5;
        }
    }

    public static void main(String[] args) {
        System.out.println(findMedianSortedArrays(new int[]{1, 3}, new int[]{4, 5}));
    }
}
