package 题库;

/**
 * https://leetcode-cn.com/problems/squares-of-a-sorted-array/
 *
 * @author zhushuangquan
 */
public class _977_有序数组的平方 {
    public int[] sortedSquares(int[] A) {
        int l = 0, r = A.length - 1;
        int cur = r;
        int[] B = new int[A.length];
        while (l <= r) {
            int lv = A[l] * A[l];
            int rv = A[r] * A[r];
            if (lv > rv) {
                B[cur--] = lv;
                l++;
            } else {
                B[cur--] = rv;
                r--;
            }
        }
        return B;
    }

}

class _977_有序数组的平方2 {
    public int[] sortedSquares(int[] A) {
        if (A.length == 0) return new int[]{};
        int p = 0;
        for (int i = 0; i < A.length; i++) {
            if (A[i] >= 0) {
                p = i;
                break;
            }
        }
        int[] B = new int[A.length];
        int l = 0;
        int r = A.length - 1;
        int i = B.length - 1;
        while (r >= p && l < p) {
            if (Math.abs(A[l]) > A[r]) {
                B[i--] = A[l] * A[l];
                l++;
            } else {
                B[i--] = A[r] * A[r];
                r--;
            }
        }
        if (i >= 0) {
            if (l < p) {
                for (int j = l; j < p; j++) {
                    B[i--] = A[j] * A[j];
                }
            } else {
                for (int j = r; j >= p; j--) {
                    B[i--] = A[j] * A[j];
                }
            }
        }
        return B;
    }
}
