package 刷题.白板题;

import 标签.链表.ListNode;

/**
 * https://leetcode-cn.com/problems/palindrome-linked-list/
 *
 * @author zhushuangquan
 */
public class _234_回文链表 {
    public boolean isPalindrome(ListNode head) {
        if (head == null) return true;
        ListNode mid = middleNode(head);
        ListNode rHead = reverseNode(mid);
        while (rHead != null) {
            if (head.val != rHead.val) return false;
            head = head.next;
            rHead = rHead.next;
        }
        return true;
    }

    private ListNode reverseNode(ListNode head) {
        ListNode newHead = null;
        while (head != null) {
            ListNode tmp = head.next;
            head.next = newHead;
            newHead = head;
            head = tmp;
        }
        return newHead;
    }

    private ListNode middleNode(ListNode head) {
        ListNode slow = head;
        ListNode fast = head;
        while (fast != null && fast.next != null) {
            slow = slow.next;
            fast = fast.next.next;
        }
        return slow;
    }
}