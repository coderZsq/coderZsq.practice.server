package 标签.数组;

import java.util.ArrayList;
import java.util.List;

/**
 * 给定一个非负整数 numRows，生成杨辉三角的前 numRows 行。
 *
 *
 *
 * 在杨辉三角中，每个数是它左上方和右上方的数的和。
 *
 * 示例:
 *
 * 输入: 5
 * 输出:
 * [
 *      [1],
 *     [1,1],
 *    [1,2,1],
 *   [1,3,3,1],
 *  [1,4,6,4,1]
 * ]
 *
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/pascals-triangle
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 */
public class _118_杨辉三角 {
    public List<List<Integer>> generate(int numRows) {
        // 1. 定义出二维数组的结构
        List<List<Integer>> tri = new ArrayList<>();
        // 2. 如果数值小于等于0 返回空数组
        if (numRows <= 0) {
            return tri;
        }
        // 3. 添加杨辉三角第一行的数组
        tri.add(new ArrayList<>());
        // 4. 杨辉三角第一行的数组必定是1
        tri.get(0).add(1);
        // 5. 从第二行开始遍历
        for (int i = 1; i < numRows; i++) {
            // 6. 定义当前行
            List<Integer> row = new ArrayList<>();
            // 7. 获得上一行
            List<Integer> prevRow = tri.get(i - 1);
            // 8. 杨辉三角当前行的第一位永远是1
            row.add(1);
            // 9. 遍历当前行从第二到倒数第二
            for (int j = 1; j < i; j++) {
                // 10. 杨辉三角当前值等于上一行的值相加
                row.add(prevRow.get(j - 1) + prevRow.get(j));
            }
            // 11. 杨辉三角当前行的最后一位永远是1
            row.add(1);
            // 12. 将当前行加入二维数组
            tri.add(row);
        }
        return tri;
    }
}
