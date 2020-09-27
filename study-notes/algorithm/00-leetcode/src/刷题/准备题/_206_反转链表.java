package 刷题.准备题;

import 标签.链表.ListNode;

/**
 * https://leetcode-cn.com/problems/reverse-linked-list/
 *
 * @author zhushuangquan
 */
class _206_反转链表 {
    public ListNode reverseList(ListNode head) {
        if (head == null) return null;
        ListNode newHead = null;
        while (head != null) {
            ListNode tmp = head.next;
            head.next = newHead;
            newHead = head;
            head = tmp;
        }
        return newHead;
    }
}
