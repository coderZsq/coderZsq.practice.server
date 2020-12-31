package 标签.链表;

/**
 * https://leetcode-cn.com/problems/reverse-nodes-in-k-group/
 *
 * @author zhushuangquan
 */
public class _25_K个一组翻转链表 {
    public ListNode reverseKGroup(ListNode head, int k) {
        if (head == null) return null;
        ListNode a = head, b = head;
        for (int i = 0; i < k; i++) {
            if (b == null) return head;
            b = b.next;
        }

        ListNode newHead = reverseList(a, b);
        a.next = reverseKGroup(b, k);
        return newHead;
    }

    private ListNode reverseList(ListNode head, ListNode eof) {
        ListNode newHead = null;
        while (head != eof) {
            ListNode tmp = head.next;
            head.next = newHead;
            newHead = head;
            head = tmp;
        }
        return newHead;
    }
}
