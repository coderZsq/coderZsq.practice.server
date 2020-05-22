package 刷题.高频题;

import 标签.链表.ListNode;

/**
 * 请判断一个链表是否为回文链表。
 *
 * 示例 1:
 *
 * 输入: 1->2
 * 输出: false
 * 示例 2:
 *
 * 输入: 1->2->2->1
 * 输出: true
 * 进阶：
 * 你能否用 O(n) 时间复杂度和 O(1) 空间复杂度解决此题？
 *
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/palindrome-linked-list
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 */
public class _234_回文链表 {
    public boolean isPalindrome(ListNode head) {
        // 1. 如果节点为空或只有一个节点, 判定为回文链表
        if (head == null || head.next == null) return true;
        // 2. 如果有两个节点, 判断是否相等
        if (head.next.next == null) return head.val == head.next.val;
        // 3. 使用快慢指针获取中心节点, 中心节点判定为右边头节点的前驱节点
        ListNode mid = middleNode(head);
        // 4. 从右边头节点翻转链表
        ListNode rHead = reverseList(mid.next);
        // 5. 双指针头尾遍历
        while (rHead != null) {
            // 6. 遍历如果判定不同则不为回文链表
            if (head.val != rHead.val) return false;
            head = head.next;
            rHead = rHead.next;
        }
        return true;
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

    private ListNode middleNode(ListNode head) {
        ListNode slow = head;
        ListNode fast = head;
        while (fast.next != null && fast.next.next != null) {
            slow = slow.next;
            fast = fast.next.next;
        }
        return slow;
    }
}
