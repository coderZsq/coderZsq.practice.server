package 刷题.高频题;

import 标签.链表.ListNode;

/**
 * 反转一个单链表。
 *
 * 示例:
 *
 * 输入: 1->2->3->4->5->NULL
 * 输出: 5->4->3->2->1->NULL
 * 进阶:
 * 你可以迭代或递归地反转链表。你能否用两种方法解决这道题？
 *
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/reverse-linked-list
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 */
public class _206_反转链表 {
    /**
     * 递归实现
     *
     * @param head 头节点
     * @return 反转头节点
     */
    public static ListNode reverseListRecursive(ListNode head) {
        // 4. 递归基, 如果链表没有节点返回NULL, 如果链表只有一个节点, 返回头节点
        if (head == null || head.next == null) return head;
        // 1. 假设除第一个节点之后的节点已经反转
        // head    : 1 -> 2 -> 3 -> 4 -> 5 -> NULL
        // newHead : NULL <- 2 <- 3 <- 4 <- 5
        ListNode newHead = reverseListRecursive(head.next);
        // 2. 原先的头节点指向逆序的非空尾节点, 让指向NULL的非空尾节点的 next 指向 head
        // head == 1; head.next == 2; head.next.next == NULL;
        // head.next.next = 1; 1 <- 2 <- 3 <- 4 <- 5
        head.next.next = head;
        // 3. 并将头节点的 next 指向 NULL
        // NULL <- 1 <- 2 <- 3 <- 4 <- 5
        head.next = null;
        return newHead;
    }

    /**
     * 迭代实现
     *
     * @param head 头节点
     * @return 反转头节点
     */
    public static ListNode reverseListIterate(ListNode head) {
        if (head == null || head.next == null) return head;
        // 1. 定义一个新的头节点引用
        // NULL -> NULL
        ListNode newHead = null;
        // 3. 对链表进行遍历
        // head 当前遍历的节点 1 -> 2 -> 3 -> 4 -> 5 -> NULL
        while (head != null) {
            // 4. 保存当前遍历节点的指向节点 tmp == 2;
            ListNode tmp = head.next;
            // 5. 将head指向newHead节点
            head.next = newHead;
            // 6. 将newHead引用指向head
            newHead = head;
            // 7. 遍历节点 head 从 1 到 2...
            head = tmp;
        }
        // 2. 返回新定义的头节点引用
        return newHead;
    }
}
