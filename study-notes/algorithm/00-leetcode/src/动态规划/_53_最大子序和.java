package 动态规划;

/**
 * https://leetcode-cn.com/problems/maximum-subarray/
 *
 * @author zhushuangquan
 */
public class _53_最大子序和 {
    static int maxSubArray1(int[] nums, int begin, int end) {
        if (end - begin < 2) return nums[begin];
        int mid = (begin + end) >> 1;
        int leftMax = Integer.MIN_VALUE;
        int leftSum = 0;
        for (int i = mid - 1; i >= begin; i--) {
            leftSum += nums[i];
            leftMax = Math.max(leftMax, leftSum);
        }

        int rightMax = Integer.MIN_VALUE;
        int rightSum = 0;
        for (int i = mid; i < end; i++) {
            rightSum += nums[i];
            rightMax = Math.max(rightMax, rightSum);
        }
        return Math.max(leftMax + rightMax,
                Math.max(
                        maxSubArray1(nums, begin, mid),
                        maxSubArray1(nums, mid, end))
        );
    }

    public int maxSubArray(int[] nums) {
        if (nums == null || nums.length == 0) return 0;
        int dp = nums[0];
        int max = dp;
        for (int i = 1; i < nums.length; i++) {
            if (dp <= 0) {
                dp = nums[i];
            } else {
                dp = dp + nums[i];
            }
            max = Math.max(dp, max);
        }
        return max;
    }

    public int maxSubArray1(int[] nums) {
        if (nums == null || nums.length == 0) return 0;
        return maxSubArray1(nums, 0, nums.length);
    }
}
