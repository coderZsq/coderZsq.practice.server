package 刷题.待面试;

import 标签.链表.ListNode;

/**
 * https://leetcode-cn.com/problems/merge-k-sorted-lists/
 *
 * @author zhushuangquan
 */
public class _18_23_合并K个排序链表 {
    public ListNode mergeKLists(ListNode[] lists) {
        if (lists == null || lists.length == 0) return null;
        return merge(lists, 0, lists.length);
    }

    private ListNode merge(ListNode[] lists, int l, int r) {
        if (r - l < 2) return lists[l];
        int mid = ((r - l) >> 1) + l;
        return mergeTwoLists(merge(lists, l, mid), merge(lists, mid, r));
    }

    private ListNode mergeTwoLists(ListNode l1, ListNode l2) {
        if (l1 == null) return l2;
        if (l2 == null) return l1;

        ListNode dummyHead = new ListNode(0);
        ListNode cur = dummyHead;
        while (l1 != null && l2 != null) {
            if (l1.val < l2.val) {
                cur = cur.next = l1;
                l1 = l1.next;
            } else {
                cur = cur.next = l2;
                l2 = l2.next;
            }
        }
        if (l1 == null) {
            cur.next = l2;
        } else {
            cur.next = l1;
        }
        return dummyHead.next;
    }
}
