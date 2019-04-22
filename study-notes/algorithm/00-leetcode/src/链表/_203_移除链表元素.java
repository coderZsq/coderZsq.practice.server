package 链表;

/**
 * https://leetcode-cn.com/problems/remove-linked-list-elements/
 * @author zhushuangquan
 */
public class _203_移除链表元素 {
    public ListNode removeElements(ListNode head, int val) {
        ListNode virtualHead = new ListNode(-1);
        virtualHead.next = head;
        ListNode current = virtualHead;
        while (current.next != null) {
            if (current.next.val == val) {
                current.next = current.next.next;
            } else {
                current = current.next;
            }
        }
        return virtualHead.next;
    }
}
