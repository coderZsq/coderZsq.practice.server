package 刷题.待面试;

/**
 * https://leetcode-cn.com/problems/container-with-most-water/
 *
 * @author zhushuangquan
 */
public class _19_11_盛最多水的容器 {
    public int maxArea(int[] height) {
        if (height == null || height.length == 0) return 0;
        int l = 0;
        int r = height.length - 1;
        int max = 0;
        while (l < r) {
            int len = r - l;
            if (height[l] < height[r]) {
                int minH = height[l];
                max = Math.max(max, minH * len);
                while (l < r && height[l] <= minH) l++;
            } else {
                int minH = height[r];
                max = Math.max(max, minH * len);
                while (l < r && height[r] <= minH) r--;
            }
        }
        return max;
    }
}