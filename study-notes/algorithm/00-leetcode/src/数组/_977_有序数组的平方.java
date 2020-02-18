package 数组;

/**
 * https://leetcode-cn.com/problems/squares-of-a-sorted-array/
 *
 * @author zhushuangquan
 */
public class _977_有序数组的平方 {
    public static int[] sortedSquares(int[] A) {
        if (A.length == 0) return new int[]{};
        int p = 0;
        for (int i = 0; i < A.length; i++) {
            if (A[i] >= 0) {
                p = i;
                break;
            }
        }
        int[] newA = new int[A.length];
        int l = 0;
        int r = A.length - 1;
        int i = newA.length - 1;
        while (r >= p && l < p) {
            if (Math.abs(A[l]) > A[r]) {
                newA[i--] = (int) Math.pow(A[l++], 2);
            } else {
                newA[i--] = (int) Math.pow(A[r--], 2);
            }
        }
        if (i >= 0) {
            if (l < p) {
                for (int j = l; j < p; j++) {
                    newA[i--] = (int) Math.pow(A[j], 2);
                }
            } else {
                for (int j = r; j >= p; j--) {
                    newA[i--] = (int) Math.pow(A[j], 2);
                }
            }
        }
        return newA;
    }
}
