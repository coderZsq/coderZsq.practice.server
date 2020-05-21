package 刷题.高频题;

/**
 * 编写一个算法来判断一个数 n 是不是快乐数。
 * <p>
 * 「快乐数」定义为：对于一个正整数，每一次将该数替换为它每个位置上的数字的平方和，然后重复这个过程直到这个数变为 1，也可能是 无限循环 但始终变不到 1。如果 可以变为  1，那么这个数就是快乐数。
 * <p>
 * 如果 n 是快乐数就返回 True ；不是，则返回 False 。
 * <p>
 * <p>
 * <p>
 * 示例：
 * <p>
 * 输入：19
 * 输出：true
 * 解释：
 * 12 + 92 = 82
 * 82 + 22 = 68
 * 62 + 82 = 100
 * 12 + 02 + 02 = 1
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/happy-number/
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 */
public class _202_快乐数 {
    // 1. 定义一个类似链表的next函数
    private int next(int n) {
        // 2. 定义返回结果
        int res = 0;
        // 3. 循环退位结束条件
        while (n > 0) {
            // 4. 取出最后一位
            int x = n % 10;
            // 5. 累加退位平方
            res += x * x;
            // 6. 退位
            n /= 10;
        }
        // 7. 返回结果
        return res;
    }

    public boolean isHappy(int n) {
        // 8. 使用快慢指针判断是否有环
        int slow = n;
        int fast = next(n);
        // 9. 退出条件 快指针 == 1 为快乐数, 或者指针相遇有环退出, 不为快乐数
        while (fast != 1 && fast != slow) {
            // 10. 快慢指针赛跑
            slow = next(slow);
            fast = next(next(fast));
        }
        // 11. 判断是否是快乐数
        return fast == 1;
    }
}
