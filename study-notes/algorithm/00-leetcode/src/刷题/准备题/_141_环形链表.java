package 刷题.准备题;

import 标签.链表.ListNode;

/**
 * https://leetcode-cn.com/problems/linked-list-cycle/
 *
 * @author zhushuangquan
 */
public class _141_环形链表 {
    public boolean hasCycle(ListNode head) {
        if (head == null || head.next == null) return false;
        ListNode slow = head;
        ListNode fast = head;
        while (fast != null && fast.next != null) {
            slow = slow.next;
            fast = fast.next.next;
            if (slow == fast) return true;
        }
        return false;
    }
}
