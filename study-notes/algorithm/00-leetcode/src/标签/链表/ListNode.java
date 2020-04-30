package 标签.链表;

import java.util.List;

public class ListNode {
    public int val;
    public ListNode next;

    public ListNode(int x) {
        val = x;
    }

    static public void dump(ListNode node) {
        while (node != null) {
            System.out.print(node + " ");
            node = node.next;
        }
    }

    @Override
    public String toString() {
        return val + " -> " + next;
    }

    /**
     * 根据数组创建链表
     *
     * @param list 数组
     * @return 链表的头节点
     */
    static public ListNode createListNodeFromList(List<Integer> list) {
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
    static public String serializationFromListNode(ListNode head) {
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