package 标签.数组;

/**
 * 给定一个整数数组 A，如果它是有效的山脉数组就返回 true，否则返回 false。
 *
 * 让我们回顾一下，如果 A 满足下述条件，那么它是一个山脉数组：
 *
 * A.length >= 3
 * 在 0 < i < A.length - 1 条件下，存在 i 使得：
 * A[0] < A[1] < ... A[i-1] < A[i]
 * A[i] > A[i+1] > ... > A[A.length - 1]
 *  
 *
 *
 *
 *  
 *
 * 示例 1：
 *
 * 输入：[2,1]
 * 输出：false
 * 示例 2：
 *
 * 输入：[3,5,5]
 * 输出：false
 * 示例 3：
 *
 * 输入：[0,3,2,1]
 * 输出：true
 *  
 *
 * 提示：
 *
 * 0 <= A.length <= 10000
 * 0 <= A[i] <= 10000 
 *
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/valid-mountain-array
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 */
public class _941_有效的山脉数组 {
    public boolean validMountainArray(int[] A) {
        int len = A.length;
        int left = 0;
        int right = len - 1;
        // 从左往右扫描, 登上山顶
        while (left + 1 < len && A[left] < A[left + 1]) left++;
        // 从右往左扫描, 登上山顶
        while (right > 0 && A[right - 1] > A[right]) right--;
        // 判断是否是同一个山顶, 左边和右边至少有登山动作
        return left > 0 && right < len - 1 &&  left == right;
    }
}
