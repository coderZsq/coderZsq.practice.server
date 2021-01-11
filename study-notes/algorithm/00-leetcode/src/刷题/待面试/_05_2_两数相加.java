package 刷题.待面试;

import 标签.链表.ListNode;

/**
 * https://leetcode-cn.com/problems/add-two-numbers/
 *
 * @author zhushuangquan
 */
public class _05_2_两数相加 {
    public ListNode addTwoNumbers(ListNode l1, ListNode l2) {
        if (l1 == null) return l2;
        if (l2 == null) return l1;

        ListNode dummyHead = new ListNode(0);
        ListNode cur = dummyHead;
        int curry = 0;
        while (l1 != null || l2 != null) {
            int v1 = 0;
            if (l1 != null) {
                v1 = l1.val;
                l1 = l1.next;
            }
            int v2 = 0;
            if (l2 != null) {
                v2 = l2.val;
                l2 = l2.next;
            }
            int sum = v1 + v2 + curry;
            curry = sum / 10;
            cur = cur.next = new ListNode(sum % 10);
        }
        if (curry == 1) {
            cur.next = new ListNode(curry);
        }
        return dummyHead.next;
    }
}
