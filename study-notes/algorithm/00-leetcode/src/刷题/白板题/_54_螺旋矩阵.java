package 刷题.白板题;

import java.util.ArrayList;
import java.util.List;

/**
 * https://leetcode-cn.com/problems/spiral-matrix/
 *
 * @author zhushuangquan
 */
public class _54_螺旋矩阵 {
    public List<Integer> spiralOrder(int[][] matrix) {
        if (matrix == null) return null;
        List<Integer> res = new ArrayList<>();
        if (matrix.length == 0) return res;
        int top = 0;
        int bottom = matrix.length - 1;
        int left = 0;
        int right = matrix[0].length - 1;
        while (left <= right && top <= bottom) {
            for (int i = left; i <= right; i++) {
                res.add(matrix[top][i]);
            }
            top++;
            for (int i = top; i <= bottom; i++) {
                res.add(matrix[i][right]);
            }
            right--;
            if (left > right || top > bottom) break;
            for (int i = right; i >= left; i--) {
                res.add(matrix[bottom][i]);
            }
            bottom--;
            for (int i = bottom; i >= top; i--) {
                res.add(matrix[i][left]);
            }
            left++;
        }
        return res;
    }
}
