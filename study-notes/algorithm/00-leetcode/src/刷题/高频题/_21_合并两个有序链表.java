package 刷题.高频题;

import org.junit.Assert;
import org.junit.Test;
import 标签.链表.ListNode;

import java.util.Arrays;
import java.util.List;

/**
 * 将两个升序链表合并为一个新的升序链表并返回。新链表是通过拼接给定的两个链表的所有节点组成的。 
 *
 * 示例：
 *
 * 输入：1->2->4, 1->3->4
 * 输出：1->1->2->3->4->4
 *
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/merge-two-sorted-lists
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 */
public class _21_合并两个有序链表 {
    public ListNode mergeTwoLists(ListNode l1, ListNode l2) {
        if (l1 == null) return l2;
        if (l2 == null) return l1;

        // 1. 创建虚拟头节点
        ListNode dummyHead = new ListNode(-1);
        ListNode cur = dummyHead;

        // 2. 先遍历到一条链表结束为止
        while (l1 != null && l2 != null) {
            if (l1.val < l2.val) {
                // 3. 接上新链表并继续遍历
                cur = cur.next = l1;
                l1 = l1.next;
            } else {
                cur = cur.next = l2;
                l2 = l2.next;
            }
        }

        // 4. 接上没有接上的链表
        if (l1 == null) {
            cur.next = l2;
        } else {
            cur.next = l1;
        }

        return dummyHead.next;
    }

    @Test
    public void test() {
        List<Integer> list1 = Arrays.asList(1, 2, 4);
        List<Integer> list2 = Arrays.asList(1, 3, 4);
        String targetString = ListNode.serializationFromListNode(
                new 标签.链表._21_合并两个有序链表().mergeTwoLists(
                        ListNode.createListNodeFromList(list1),
                        ListNode.createListNodeFromList(list2)
                )
        );
        String actualString = ListNode.serializationFromListNode(
                mergeTwoLists(
                        ListNode.createListNodeFromList(list1),
                        ListNode.createListNodeFromList(list2)
                )
        );
        Assert.assertEquals(targetString, actualString);
    }
}
