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
        ListNode rHead = reverseList(mid);
        ListNode lHead = head;
        while (rHead != null) {
            if (lHead.val != rHead.val) {
                return false;
            }
            lHead = lHead.next;
            rHead = rHead.next;
        }
        return true;
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

    private ListNode reverseList(ListNode head) {
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