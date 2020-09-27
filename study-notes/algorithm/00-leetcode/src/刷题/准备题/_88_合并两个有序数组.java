package 刷题.准备题;

/**
 * https://leetcode-cn.com/problems/merge-sorted-array/
 *
 * @author zhushuangquan
 */
public class _88_合并两个有序数组 {
    public void merge(int[] nums1, int m, int[] nums2, int n) {
        int i1 = m - 1;
        int i2 = n - 1;
        int i3 = nums1.length - 1;
        while (i2 >= 0) {
            if (i1 >= 0 && nums2[i2] < nums1[i1]) {
                nums1[i3--] = nums1[i1--];
            } else {
                nums1[i3--] = nums2[i2--];
            }
        }
    }
}
