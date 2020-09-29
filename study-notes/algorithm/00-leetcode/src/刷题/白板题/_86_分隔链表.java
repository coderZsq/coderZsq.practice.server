package 刷题.白板题;

import 标签.链表.ListNode;

/**
 * https://leetcode-cn.com/problems/partition-list/
 *
 * @author zhushuangquan
 */
public class _86_分隔链表 {
    public ListNode partition(ListNode head, int x) {
        if (head == null) return null;
        ListNode lHead = new ListNode(0);
        ListNode lCur = lHead;
        ListNode rHead = new ListNode(0);
        ListNode rCur = rHead;
        while (head != null) {
            if (head.val < x) {
                lCur = lCur.next = head;
            } else {
                rCur = rCur.next = head;
            }
            head = head.next;
        }
        lCur.next = rHead.next;
        rCur.next = null;
        return lHead.next;
    }
}
