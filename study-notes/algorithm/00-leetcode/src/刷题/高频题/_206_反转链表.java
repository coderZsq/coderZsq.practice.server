package 刷题.高频题;

import org.junit.Assert;
import org.junit.Test;
import 标签.链表.ListNode;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

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
    public static ListNode reverseList(ListNode head) {
        if (head == null || head.next == null) return head;
        // 1. head : 1->2->3->4->5->NULL
        ListNode newHead = reverseList(head.next);
        // 2. newHead : NULL->5->4->3->2
        head.next.next = head;
        head.next = null;
        return newHead;
    }

    @Test
    public void test() {
        List<Integer> list = Arrays.asList(1, 2, 3, 4, 5);
        ListNode input = createListNodeFromList(list);
        String outString = serializationFromListNode(reverseList(input));
        Collections.reverse(list);
        String targetString = serializationFromListNode(createListNodeFromList(list));
        Assert.assertEquals(targetString, outString);
    }

    private ListNode createListNodeFromList(List<Integer> list) {
        // 2. 创建虚拟头节点
        ListNode dummyHead = new ListNode(-1);
        // 3. 头节点引用
        ListNode head = dummyHead;
        // 1. 循环遍历出单链表的值
        for (Integer value : list) {
            // 4. 串起链表
            head.next = new ListNode(value);
            head = head.next;
        }
        // 5. 由于改变了引用指向, 重定向头节点引用
        return dummyHead.next;
    }

    private String serializationFromListNode(ListNode head) {
        // 6. 判断遍历到的节点是否为空
        StringBuilder stringBuilder = new StringBuilder();
        while (head != null) {
            // 7. 打印遍历到节点的值
            stringBuilder.append(head.val).append("->");
            // 8. 变更引用
            head = head.next;
        }
        stringBuilder.append("NULL");
        return stringBuilder.toString();
    }
}
