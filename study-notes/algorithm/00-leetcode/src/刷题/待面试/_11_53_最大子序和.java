package 刷题.待面试;

/**
 * https://leetcode-cn.com/problems/maximum-subarray/
 *
 * @author zhushuangquan
 */
public class _11_53_最大子序和 {
    public int maxSubArray(int[] nums) {
        if (nums == null || nums.length == 0) return 0;
        int dp = nums[0];
        int max = dp;
        for (int i = 1; i < nums.length; i++) {
            if (dp < 0) {
                dp = nums[i];
            } else {
                dp += nums[i];
            }
            max = Math.max(max, dp);
        }
        return max;
    }
}