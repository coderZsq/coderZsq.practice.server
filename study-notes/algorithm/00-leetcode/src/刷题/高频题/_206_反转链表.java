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

    @Test
    public void test() {
        List<Integer> list = Arrays.asList(1, 2, 3, 4, 5);
        String outStringR = serializationFromListNode(reverseListRecursive(createListNodeFromList(list)));
        String outStringI = serializationFromListNode(reverseListIterate(createListNodeFromList(list)));

        Collections.reverse(list);
        String targetString = serializationFromListNode(createListNodeFromList(list));

        Assert.assertEquals(targetString, outStringR);
        Assert.assertEquals(targetString, outStringI);
    }

    /**
     * 根据数组创建链表
     *
     * @param list 数组
     * @return 链表的头节点
     */
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

    /**
     * 根据链表头节点序列化成字符串
     *
     * @param head 链表头节点
     * @return 链表字符串
     */
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
