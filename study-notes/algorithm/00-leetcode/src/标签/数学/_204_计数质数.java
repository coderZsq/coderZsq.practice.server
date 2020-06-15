package 标签.数学;

/**
 * 统计所有小于非负整数 n 的质数的数量。
 *
 * 示例:
 *
 * 输入: 10
 * 输出: 4
 * 解释: 小于 10 的质数一共有 4 个, 它们是 2, 3, 5, 7 。
 *
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/count-primes/
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 */
public class _204_计数质数 {
    public int countPrimes(int n) {
        // 1. 定义结果变量计数
        int result = 0;
        // 2. 使用桶来筛选质数 (默认为false, 但语义上理解为默认是质数)
        boolean[] bucket = new boolean[n];
        // 3. 如果 n > 2 说明肯定包含 2 这个质数
        if (n > 2) result++;
        // 4. 从 3 开始遍历奇数, 质数排除 2 外均为奇数
        for (int i = 3; i < n; i += 2) {
            // 5. 判断是否为质数
            if (!bucket[i]) {
                // 6. 是质数, 则结果计数累加 1
                result++;
                // 7. 在奇数中判断是否可以被整除(反向相乘)
                for (int j = 3; i * j < n; j += 2) {
                    // 8. 过滤不是质数, i * j < n 的判断条件为不越界的条件, 设置为true, 语义为不是质数
                    bucket[i * j] = true;
                }
            }
        }
        // 9. 返回计数结果
        return result;
    }
}
