package 刷题.高频题;

/**
 * 给定一个整数数组 nums ，找到一个具有最大和的连续子数组（子数组最少包含一个元素），返回其最大和。
 *
 * 示例:
 *
 * 输入: [-2,1,-3,4,-1,2,1,-5,4],
 * 输出: 6
 * 解释: 连续子数组 [4,-1,2,1] 的和最大，为 6。
 * 进阶:
 *
 * 如果你已经实现复杂度为 O(n) 的解法，尝试使用更为精妙的分治法求解。
 *
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/maximum-subarray
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 */
public class _53_最大子序和 {
    /**
     * O(n) 解法
     *
     * @param nums 整数数组
     * @return 最大和
     */
    public static int maxSubArrayOn(int[] nums) {
        if (nums == null || nums.length == 0) return 0;
        // 1. 定义状态, dp为数组中每个下标为最后一位的最大子序列和
        int dp = nums[0];
        int max = dp;
        for (int i = 1; i < nums.length; i++) {
            // 2. 状态转移方程
            if (dp <= 0) {
                // 3. 如果前一位为最后一位的最大子序列和的数为负数, 则直接覆盖新数
                dp = nums[i];
            } else {
                // 4. 如果前一位为最后一位的最大子序列和的数为正数, 则直接累加新数
                dp += nums[i];
            }
            max = Math.max(dp, max);
        }
        return max;
    }

    /**
     * 分治法
     *
     * @param nums 整数数组
     * @return 最大和
     */
    public static int maxSubArrayDivideAndConquer(int[] nums) {
        if (nums == null || nums.length == 0) return 0;
        return maxSubArrayDivideAndConquer(nums, 0, nums.length);
    }

    /**
     * 1. 定义出分治函数 左闭右开
     */
    private static int maxSubArrayDivideAndConquer(int[] nums, int begin, int end) {
        // 2. 如果区间内只有一个数, 则该数为最大子序和
        if (end - begin < 2) return nums[begin];

        // 3. 定义出 mid 下标
        int mid = (begin + end) >> 1;

        // 5. 从中间向左边进行遍历得出该区间最大子序和
        int leftMax = Integer.MIN_VALUE;
        int leftSum = 0;
        // 6. 由于左闭右开区间, 所以下标从 mid - 1 开始
        for (int i = mid - 1; i >= begin; i--) {
            leftSum += nums[i];
            leftMax = Math.max(leftMax, leftSum);
        }

        // 7. 相反得出从中间向右边的最大子序和
        int rightMax = Integer.MIN_VALUE;
        int rightSum = 0;
        for (int i = mid; i < end; i++) {
            rightSum += nums[i];
            rightMax = Math.max(rightMax, rightSum);
        }

        // 4. 将区间分成三份, 左 中 右, 分治取最大子序和
        return Math.max(leftMax + rightMax, Math.max(maxSubArrayDivideAndConquer(nums, begin, mid), maxSubArrayDivideAndConquer(nums, mid, end)));
    }
}
