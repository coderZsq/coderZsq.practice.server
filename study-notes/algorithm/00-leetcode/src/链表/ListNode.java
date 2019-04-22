package 链表;

public class ListNode {
    int val;
    ListNode next;
    ListNode(int x) {
        val = x;
    }

    @Override
    public String toString() {
        return String.valueOf(this.val);
    }

    static public void dump(ListNode node) {
        while (node != null) {
            System.out.print(node + " ");
            node = node.next;
        }
    }
}