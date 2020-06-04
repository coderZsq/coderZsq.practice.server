package 刷题.高频题;

import 标签.链表.ListNode;

/**
 * 给定一个链表，判断链表中是否有环。
 * <p>
 * 为了表示给定链表中的环，我们使用整数 pos 来表示链表尾连接到链表中的位置（索引从 0 开始）。 如果 pos 是 -1，则在该链表中没有环。
 * <p>
 *  
 * <p>
 * 示例 1：
 * <p>
 * 输入：head = [3,2,0,-4], pos = 1
 * 输出：true
 * 解释：链表中有一个环，其尾部连接到第二个节点。
 * <p>
 * <p>
 * 示例 2：
 * <p>
 * 输入：head = [1,2], pos = 0
 * 输出：true
 * 解释：链表中有一个环，其尾部连接到第一个节点。
 * <p>
 * <p>
 * 示例 3：
 * <p>
 * 输入：head = [1], pos = -1
 * 输出：false
 * 解释：链表中没有环。
 * <p>
 * <p>
 *  
 * <p>
 * 进阶：
 * <p>
 * 你能用 O(1)（即，常量）内存解决此问题吗？
 * <p>
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/linked-list-cycle
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 */
public class _141_环形链表 {
    public boolean hasCycle(ListNode head) {
        // 1. 如果没有节点或只有一个节点, 则判定不是环形链表
        if (head == null || head.next == null) return false;
        // 2. 定义慢指针, 慢指针每一次走一格
        ListNode slow = head;
        // 3. 定义快指针, 快指针每一次走两格
        ListNode fast = head.next;
        while (fast.next != null && fast.next.next != null) {
            slow = slow.next;
            fast = fast.next.next;
            // 4. 如果快指针和慢指针相遇, 则判定有环 (注意:需要先走再判定)
            if (fast == slow) return true;
        }
        // 5. 如果遍历完快慢指针没有相遇则判定无环
        return false;
    }
}
