package 刷题.准备题;

import 标签.链表.ListNode;

/**
 * https://leetcode-cn.com/problems/delete-node-in-a-linked-list/
 *
 * @author zhushuangquan
 */
public class _237_删除链表中的节点 {
    public void deleteNode(ListNode node) {
        node.val = node.next.val;
        node.next = node.next.next;
    }
}
