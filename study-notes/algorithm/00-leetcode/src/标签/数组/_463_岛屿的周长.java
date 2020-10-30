package 标签.数组;

/**
 * 给定一个包含 0 和 1 的二维网格地图，其中 1 表示陆地 0 表示水域。
 *
 * 网格中的格子水平和垂直方向相连（对角线方向不相连）。整个网格被水完全包围，但其中恰好有一个岛屿（或者说，一个或多个表示陆地的格子相连组成的岛屿）。
 *
 * 岛屿中没有“湖”（“湖” 指水域在岛屿内部且不和岛屿周围的水相连）。格子是边长为 1 的正方形。网格为长方形，且宽度和高度均不超过 100 。计算这个岛屿的周长。
 *
 *  
 *
 * 示例 :
 *
 * 输入:
 * [[0,1,0,0],
 *  [1,1,1,0],
 *  [0,1,0,0],
 *  [1,1,0,0]]
 *
 * 输出: 16
 *
 * 解释: 它的周长是下面图片中的 16 个黄色的边：
 *
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/island-perimeter
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 */
public class _463_岛屿的周长 {
    public int islandPerimeter(int[][] grid) {
        // 重点关注前面遍历过得方格，如果之前有相邻方格，就-2;
        int result = 0;
        for (int y = 0; y < grid.length; y++) {
            for (int x = 0; x < grid[0].length; x++) {
                // 遍历所有的格子 如果是陆地则计算边
                if (grid[y][x] == 1) {
                    // 统一先加上四条边
                    result += 4;
                    // 如果格子不靠边并且格子上边是陆地则减两条边
                    if (y > 0 && grid[y - 1][x] == 1) result -= 2;
                    // 如果格子不靠边并且格子右边是陆地则减两条边
                    if (x > 0 && grid[y][x - 1] == 1) result -= 2;
                }
            }
        }
        return result;
    }
}

class _463_岛屿的周长2 {
    public int islandPerimeter(int[][] grid) {
        int result = 0;
        for (int i = 0; i < grid.length; i++) {
            for (int j = 0; j < grid[0].length; j++) {
                // 遍历所有的格子 如果是陆地则计算边
                if (grid[i][j] == 1) {
                    result += countSides(grid, j, i);
                }
            }
        }
        return result;
    }

    private int countSides(int[][] grid, int x, int y) {
        // 定义边数
        int sides = 0;
        // 如果格子左边靠边或左边是水域则加一条边
        if (x == 0 || grid[y][x - 1] == 0) sides++;
        // 如果格子上边靠边或上边是水域则加一条边
        if (y == 0 || grid[y - 1][x] == 0) sides++;
        // 如果格子右边靠边或右边是水域则加一条边
        if (x == grid[0].length - 1 || grid[y][x + 1] == 0) sides++;
        // 如果格子下边靠边或下边是水域则加一条边
        if (y == grid.length - 1 || grid[y + 1][x] == 0) sides++;
        return sides;
    }
}
