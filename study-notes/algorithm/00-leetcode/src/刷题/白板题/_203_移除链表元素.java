package 刷题.白板题;

import 标签.链表.ListNode;

/**
 * https://leetcode-cn.com/problems/remove-linked-list-elements/
 *
 * @author zhushuangquan
 */
public class _203_移除链表元素 {
    public ListNode removeElements(ListNode head, int val) {
        ListNode dummyHead = new ListNode(0);
        ListNode cur = dummyHead;
        while (head != null) {
            if (head.val != val) {
                cur = cur.next = head;
            }
            head = head.next;
        }
        cur.next = null;
        return dummyHead.next;
    }
}