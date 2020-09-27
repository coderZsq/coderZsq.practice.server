package 刷题.准备题;

import 标签.链表.ListNode;

/**
 * https://leetcode-cn.com/problems/intersection-of-two-linked-lists/
 *
 * @author zhushuangquan
 */
public class _160_相交链表 {
    public ListNode getIntersectionNode(ListNode headA, ListNode headB) {
        if (headA == null || headB == null) return null;
        ListNode curA = headA, curB = headB;
        while (curA != curB) {
            curA = curA != null ? curA.next : headB;
            curB = curB != null ? curB.next : headA;
        }
        return curA;
    }
}
