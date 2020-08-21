package 题库;

import 标签.链表.ListNode;

/**
 * https://leetcode-cn.com/problems/palindrome-linked-list-lcci/
 *
 * @author zhushuangquan
 */
public class _面试题_02_06_回文链表 {
    public boolean isPalindrome(ListNode head) {
        if (head == null || head.next == null) return true;
        if (head.next.next == null) return head.val == head.next.val;
        // 找到中间节点
        ListNode mid = middleNode(head);
        // 翻转右半部分 (中间节点的右边部分)
        ListNode rHead = reverseList(mid.next);
        ListNode lHead = head;
        // 从lHead, rHead触发, 判断是否为回文链表
        while (rHead != null) {
            if (lHead.val != rHead.val) return false;
            rHead = rHead.next;
            lHead = lHead.next;
        }
        return true;
    }

    /**
     * 找到中间节点 (右半部分链表头节点的前一个节点)
     * 比如 1>2>3>2>1中的3是中间节点
     * 比如 1>2>2>1中左边第一个2是中间节点
     *
     * @param head
     * @return
     */
    private ListNode middleNode(ListNode head) {
        ListNode fast = head;
        ListNode slow = head;
        while (fast.next != null && fast.next.next != null) {
            slow = slow.next;
            fast = fast.next.next;
        }
        return slow;
    }

    /**
     * 翻转链表
     *
     * @param head 原链表的头节点
     *             比如原链表: 1>2>3>4>null, 翻转之后是: 4>3>2>1>null
     * @return 翻转之后链表的头结点(返回4)
     */
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
