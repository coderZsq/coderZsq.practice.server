package 刷题.准备题;

import 标签.链表.ListNode;

/**
 * https://leetcode-cn.com/problems/middle-of-the-linked-list/
 *
 * @author zhushuangquan
 */
public class _876_链表的中间结点 {
    public ListNode middleNode(ListNode head) {
        if (head == null) return null;
        ListNode slow = head;
        ListNode fast = head;
        while (fast != null && fast.next != null) {
            slow = slow.next;
            fast = fast.next.next;
        }
        return slow;
    }
}
