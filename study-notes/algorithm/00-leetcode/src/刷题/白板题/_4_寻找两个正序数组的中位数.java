package 刷题.白板题;

import java.util.ArrayList;
import java.util.List;

/**
 * https://leetcode-cn.com/problems/median-of-two-sorted-arrays
 *
 * @author zhushuangquan
 */
public class _4_寻找两个正序数组的中位数 {
    public double findMedianSortedArrays(int[] nums1, int[] nums2) {
        List<Integer> list = new ArrayList<>();
        int len = nums1.length + nums2.length;
        int mid = len >> 1;
        int i = 0;
        int j = 0;
        while (i < nums1.length && j < nums2.length) {
            if (nums1[i] < nums2[j]) {
                list.add(nums1[i]);
                i++;
            } else {
                list.add(nums2[j]);
                j++;
            }
        }
        if (i == nums1.length) {
            for (int i1 = j; i1 < nums2.length; i1++) {
                list.add(nums2[i1]);
            }
        } else {
            for (int i1 = i; i1 < nums1.length; i1++) {
                list.add(nums1[i1]);
            }
        }
        return (len & 1) == 1 ? list.get(mid) : ((list.get(mid) + list.get(mid - 1))) * 0.5;
    }
}
