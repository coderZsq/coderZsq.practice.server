package 刷题.待面试;

import java.util.ArrayList;
import java.util.List;

/**
 * https://leetcode-cn.com/problems/median-of-two-sorted-arrays
 *
 * @author zhushuangquan
 */
public class _07_4_寻找两个正序数组的中位数 {
    public double findMedianSortedArrays(int[] nums1, int[] nums2) {
        List<Integer> list = new ArrayList<>();
        int mid = (nums1.length + nums2.length) >> 1;
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
            for (int k = j; k < nums2.length; k++) {
                list.add(nums2[k]);
            }
        } else {
            for (int k = i; k < nums1.length; k++) {
                list.add(nums1[k]);
            }
        }
        return ((nums1.length + nums2.length) & 1) == 1 ? list.get(mid) : (list.get(mid + 1) + list.get(mid)) * 0.5;
    }
}
