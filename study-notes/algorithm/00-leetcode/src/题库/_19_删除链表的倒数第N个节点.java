package 题库;

import 标签.链表.ListNode;

/**
 * https://leetcode-cn.com/problems/remove-nth-node-from-end-of-list/
 *
 * @author zhushuangquan
 */
public class _19_删除链表的倒数第N个节点 {
    public ListNode removeNthFromEnd(ListNode head, int n) {
        if (head == null) return null;
        ListNode dummyHead = new ListNode(0);
        dummyHead.next = head;

        ListNode fast = dummyHead;
        ListNode slow = dummyHead;

        while (n > 0) {
            fast = fast.next;
            n--;
        }
        
        while (fast != null) {
            fast = fast.next;
            slow = slow.next;
        }
        slow.next = slow.next.next;
        return dummyHead.next;
    }
}
