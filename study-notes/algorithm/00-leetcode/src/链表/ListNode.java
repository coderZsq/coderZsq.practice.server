package 链表;

public class ListNode {
    int val;
    ListNode next;

    ListNode(int x) {
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