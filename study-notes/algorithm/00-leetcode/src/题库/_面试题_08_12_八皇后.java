package 题库;

import java.util.ArrayList;
import java.util.List;

/**
 * 设计一种算法，打印 N 皇后在 N × N 棋盘上的各种摆法，其中每个皇后都不同行、不同列，也不在对角线上。这里的“对角线”指的是所有的对角线，不只是平分整个棋盘的那两条对角线。
 *
 * 注意：本题相对原题做了扩展
 *
 * 示例:
 *
 *  输入：4
 *  输出：[[".Q..","...Q","Q...","..Q."],["..Q.","Q...","...Q",".Q.."]]
 *  解释: 4 皇后问题存在如下两个不同的解法。
 * [
 *  [".Q..",  // 解法 1
 *   "...Q",
 *   "Q...",
 *   "..Q."],
 *
 *  ["..Q.",  // 解法 2
 *   "Q...",
 *   "...Q",
 *   ".Q.."]
 * ]
 *
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/eight-queens-lcci
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 */

public class _面试题_08_12_八皇后 {
    List<List<String>> res = new ArrayList<>();

    int[] queens;

    boolean[] cols;
    boolean[] leftTop;
    boolean[] rightTop;

    public List<List<String>> solveNQueens(int n) {
        if (n < 1) return res;
        queens = new int[n];
        cols = new boolean[n];
        leftTop = new boolean[(n << 1) - 1];
        rightTop = new boolean[leftTop.length];
        place(0);
        return res;
    }

    void place(int row) {
        if (row == cols.length) {
            show();
            return;
        }
        for (int col = 0; col < cols.length; col++) {
            if (cols[col]) continue;
            int ltIndex = row - col + cols.length - 1;
            if (leftTop[ltIndex]) continue;
            int rtIndex = row + col;
            if (rightTop[rtIndex]) continue;

            queens[row] = col;
            cols[col] = true;
            leftTop[ltIndex] = true;
            rightTop[rtIndex] = true;
            place(row + 1);
            cols[col] = false;
            leftTop[ltIndex] = false;
            rightTop[rtIndex] = false;
        }
    }

    void show() {
        List<String> list = new ArrayList<>();
        for (int row = 0; row < cols.length; row++) {
            StringBuilder sb = new StringBuilder();
            for (int col = 0; col < cols.length; col++) {
                if (queens[row] == col) {
                    sb.append("Q");
                } else {
                    sb.append(".");
                }
            }
            list.add(sb.toString());
        }
        res.add(list);
    }
}

class _面试题_08_12_八皇后2 {
    List<List<String>> res = new ArrayList<>();

    int[] cols;

    public List<List<String>> solveNQueens(int n) {
        if (n < 1) return res;
        cols = new int[n];
        place(0);
        return res;
    }

    void place(int row) {
        if (row == cols.length) {
            show();
            return;
        }
        for (int col = 0; col < cols.length; col++) {
            if (isValid(row, col)) {
                cols[row] = col;
                place(row + 1);
            }
        }
    }

    boolean isValid(int row, int col) {
        for (int i = 0; i < row; i++) {
            if (cols[i] == col) return false;
            if (row - i == Math.abs(col - cols[i])) return false;
        }
        return true;
    }

    void show() {
        List<String> list = new ArrayList<>();
        for (int row = 0; row < cols.length; row++) {
            StringBuilder sb = new StringBuilder();
            for (int col = 0; col < cols.length; col++) {
                if (cols[row] == col) {
                    sb.append("Q");
                } else {
                    sb.append(".");
                }
            }
            list.add(sb.toString());
        }
        res.add(list);
    }
}
