package 标签.链表;

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
}