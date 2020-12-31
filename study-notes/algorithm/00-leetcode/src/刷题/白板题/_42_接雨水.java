package 刷题.白板题;

/**
 * https://leetcode-cn.com/problems/trapping-rain-water/
 *
 * @author zhushuangquan
 */
public class _42_接雨水 {
    public int trap(int[] height) {
        if (height == null || height.length == 0) return 0;
        int l = 1;
        int r = height.length - 2;
        int[] rMaxes = new int[height.length];
        for (int i = r; i >= l; i--) {
            rMaxes[i] = Math.max(rMaxes[i + 1], height[i + 1]);
        }
        int lMax = 0;
        int max = 0;
        for (int i = l; i < height.length; i++) {
            lMax = Math.max(lMax, height[i - 1]);
            int minH = Math.min(lMax, rMaxes[i]);
            if (minH > height[i]) {
                max += minH - height[i];
            }
        }
        return max;
    }
}