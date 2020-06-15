package 刷题.高频题;

/**
 * 编写一个算法来判断一个数 n 是不是快乐数。
 *
 * 「快乐数」定义为：对于一个正整数，每一次将该数替换为它每个位置上的数字的平方和，然后重复这个过程直到这个数变为 1，也可能是 无限循环 但始终变不到 1。如果 可以变为  1，那么这个数就是快乐数。
 *
 * 如果 n 是快乐数就返回 True ；不是，则返回 False 。
 *
 *
 *
 * 示例：
 *
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
        // 9. 进行循环
        while (true) {
            // 10. 快慢指针
            fast = next(next(fast));
            slow = next(slow);
            // 11. 如果 fast == 1 判定为快乐数 (必须先判断, 因为有 fast = slow = 1)
            if (fast == 1) return true;
            // 12. 快慢指针相遇为有环, 判定不是快乐数
            if (fast == slow) return false;
        }
    }
}
