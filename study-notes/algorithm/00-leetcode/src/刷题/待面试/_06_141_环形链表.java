package 刷题.待面试;

import 标签.链表.ListNode;

/**
 * https://leetcode-cn.com/problems/linked-list-cycle/
 *
 * @author zhushuangquan
 */
public class _06_141_环形链表 {
    public boolean hasCycle(ListNode head) {
        if (head == null) return false;
        ListNode fast = head;
        ListNode slow = head;
        while (fast != null && fast.next != null) {
            fast = fast.next.next;
            slow = slow.next;
            if (slow == fast) return true;
        }
        return false;
    }
}
