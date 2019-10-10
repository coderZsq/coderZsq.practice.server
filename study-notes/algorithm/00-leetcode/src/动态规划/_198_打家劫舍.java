package 动态规划;

/**
 * https://leetcode-cn.com/problems/house-robber/submissions/
 * @author zhushuangquan
 */
public class _198_打家劫舍 {
    // 针对每一个房屋, 有2种选择: 偷, 不偷
    // 0, 1, 2, 3, 4, 5
    // 如果偷第0号房子
    // sum1 = nums[0] + rob([2, 3, 4, 5])
    // 如果不偷第0号房子
    // sum2 = rob([1, 2, 3, 4, 5])
    // max(sum1, sum2)
    public int rob(int[] nums) {
        if (nums == null) return 0;
        int first = 0, second = 0;
        for (int i = 0; i < nums.length; i++) {
            int tmp = second;
            second = Math.max(nums[i] + first, second);
            first = tmp;
        }
        return second;
    }

    public int rob1(int[] nums) {
        if (nums == null || nums.length == 0) return 0;
        return rob1(nums, 0);
    }

    /**
     * 从第begin号房子开始偷出来的最大金额(从前往后偷)
     */
    private int rob1(int[] nums, int begin) {
        if (begin == nums.length - 1) return nums[begin];
        if (begin == nums.length - 2) return Math.max(nums[begin], nums[begin + 1]);
        int robCur = nums[begin] + rob1(nums, begin + 2);
        int robNext = rob1(nums, begin + 1);
        return Math.max(robCur, robNext);
    }

    // 假设当数据规模为n时, rob函数消耗的时间 T(n) = T(n - 2) + T(n - 1) + O(1)
    // 因此T(n) = O(2^n)

    public int rob2(int[] nums) {
        if (nums == null || nums.length == 0) return 0;
        return rob2(nums, nums.length - 1);
    }

    /**
     * 从第begin号房子开始偷出来的最大金额(从后往前偷)
     */
    private int rob2(int[] nums, int begin) {
        if (begin == 0) return nums[0];
        if (begin == 1) return Math.max(nums[0], nums[1]);
        int robCur = nums[begin] + rob2(nums, begin - 2); // T(n - 2)
        int robNext = rob2(nums, begin - 1); // T(n - 1)
        return Math.max(robCur, robNext);
    }

    public int rob3(int[] nums) {
        if (nums == null || nums.length == 0) return 0;
        if (nums.length == 1) return nums[0];

        int[] array = new int[nums.length];
        array[0] = nums[0];
        array[1] = Math.max(nums[0], nums[1]);
        for (int i = 2; i < array.length; i++) {
            array[i] = Math.max(nums[i] + array[i - 2], array[i - 1]);
        }
        return array[array.length - 1];
//        array[3] 是从第三号房子开始往前偷出来的最大金额
//        array[i] 是从第i号房子开始往前偷出来的最大金额
    }

    public int rob4(int[] nums) {
        if (nums == null || nums.length == 0) return 0;
        if (nums.length == 1) return nums[0];

        int first = nums[0];
        int second = Math.max(nums[0], nums[1]);
        for (int i = 2; i < nums.length; i++) {
            int tmp = second;
            second = Math.max(nums[i] + first, second);
            first = tmp;
        }
        return second;
    }
}
