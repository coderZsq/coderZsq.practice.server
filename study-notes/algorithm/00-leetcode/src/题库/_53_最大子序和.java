package 题库;

/**
 * https://leetcode-cn.com/problems/maximum-subarray/
 *
 * @author zhushuangquan
 */
public class _53_最大子序和 {
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
}

class _53_最大子序和2 {
    public int maxSubArray(int[] nums) {
        if (nums == null || nums.length == 0) return 0;
        int[] dp = new int[nums.length];
        dp[0] = nums[0];
        int max = nums[0];
        for (int i = 1; i < nums.length; i++) {
            if (dp[i - 1] <= 0) {
                dp[i] = nums[i];
            } else {
                dp[i] = dp[i - 1] + nums[i];
            }
            max = Math.max(dp[i], max);
        }
        return max;
    }
}

class _53_最大子序和3 {
    static int maxSubArray(int[] nums, int begin, int end) {
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
                        maxSubArray(nums, begin, mid),
                        maxSubArray(nums, mid, end))
        );
    }

    public int maxSubArray(int[] nums) {
        if (nums == null || nums.length == 0) return 0;
        return maxSubArray(nums, 0, nums.length);
    }
}

class _53_最大子序和4 {
    public int maxSubArray(int[] nums) {
        if (nums == null || nums.length == 0) return 0;
        int max = Integer.MIN_VALUE;
        for (int begin = 0; begin < nums.length; begin++) {
            int sum = 0;
            for (int end = begin; end < nums.length; end++) {
                // sum是[begin, end]的和
                sum += nums[end];
                max = Math.max(max, sum);
            }
        }
        return max;
    }
}

class _53_最大子序和5 {
    public int maxSubArray(int[] nums) {
        if (nums == null || nums.length == 0) return 0;
        int max = Integer.MIN_VALUE;
        for (int begin = 0; begin < nums.length; begin++) {
            for (int end = begin; end < nums.length; end++) {
                // sum是[begin, end]的和
                int sum = 0;
                for (int i = begin; i <= end; i++) {
                    sum += nums[i];
                }
                max = Math.max(max, sum);
            }
        }
        return max;
    }
}