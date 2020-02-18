package 数组;

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

    public int[] sortedSquares1(int[] A) {
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
                B[i--] = (int) Math.pow(A[l++], 2);
            } else {
                B[i--] = (int) Math.pow(A[r--], 2);
            }
        }
        if (i >= 0) {
            if (l < p) {
                for (int j = l; j < p; j++) {
                    B[i--] = (int) Math.pow(A[j], 2);
                }
            } else {
                for (int j = r; j >= p; j--) {
                    B[i--] = (int) Math.pow(A[j], 2);
                }
            }
        }
        return B;
    }
}
