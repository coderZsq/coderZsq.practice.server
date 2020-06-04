package 刷题.高频题;

/**
 * 给出一个 32 位的有符号整数，你需要将这个整数中每位上的数字进行反转。
 * <p>
 * 示例 1:
 * <p>
 * 输入: 123
 * 输出: 321
 *  示例 2:
 * <p>
 * 输入: -123
 * 输出: -321
 * 示例 3:
 * <p>
 * 输入: 120
 * 输出: 21
 * 注意:
 * <p>
 * 假设我们的环境只能存储得下 32 位的有符号整数，则其数值范围为 [−231,  231 − 1]。请根据这个假设，如果反转后整数溢出那么就返回 0。
 * <p>
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/reverse-integer
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 */
public class _7_整数反转 {
    public int reverse(int x) {
        // 1. 定义需要返回的反转整数
        int res = 0;
        // 3. 当退到最后一位退出
        while (x != 0) {
            // 4. 循环取出最后一位放在进位之后的res
            int prevRes = res;
            res = prevRes * 10 + x % 10;
            // 5. 倒推回去判断是否溢出
            if ((res - x % 10) / 10 != prevRes) return 0;
            // 2. 循环res除以10退位
            x /= 10;
        }
        return res;
    }
}
