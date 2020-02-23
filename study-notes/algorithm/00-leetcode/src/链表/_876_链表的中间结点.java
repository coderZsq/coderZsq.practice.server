package 链表;

/**
 * https://leetcode-cn.com/problems/middle-of-the-linked-list/
 *
 * @author zhushuangquan
 */
public class _876_链表的中间结点 {
    public ListNode middleNode(ListNode head) {
        ListNode slow = head, fast = head;
        while (fast != null && fast.next != null) {
            slow = slow.next;
            fast = fast.next.next;
        }
        return slow;
    }
}
