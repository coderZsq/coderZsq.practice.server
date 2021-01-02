package 刷题.待面试;

/**
 * https://leetcode-cn.com/problems/search-in-rotated-sorted-array/
 *
 * @author zhushuangquan
 */
public class _16_33_搜索旋转排序数组 {
    public int search(int[] nums, int target) {
        if (nums == null || nums.length == 0) return 0;
        int l = 0;
        int r = nums.length - 1;
        while (l <= r) {
            int mid = ((r - l) >> 1) + l;
            if (nums[mid] == target) return mid;
            if (nums[l] <= nums[mid]) {
                if (nums[l] <= target && target < nums[mid]) {
                    r = mid - 1;
                } else {
                    l = mid + 1;
                }
            } else {
                if (nums[mid] < target && target <= nums[r]) {
                    l = mid + 1;
                } else {
                    r = mid - 1;
                }
            }
        }
        return -1;
    }
}
