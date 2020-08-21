package 题库;

import 标签.链表.ListNode;

import java.util.ArrayList;
import java.util.List;
import java.util.PriorityQueue;

/**
 * https://leetcode-cn.com/problems/merge-k-sorted-lists/
 *
 * @author zhushuangquan
 */
public class _23_合并K个排序链表 {

    // 虚拟头节点 (dummy head)
    private ListNode head = new ListNode(0);

    /**
     * 思路5 - 分治策略, O(nlogk)
     */
    public ListNode mergeKLists(ListNode[] lists) {
        if (lists == null || lists.length == 0) return null;

        int step = 1;
        while (step < lists.length) {
            int nextStep = step << 1;
            for (int i = 0; i + step < lists.length; i += nextStep) {
                lists[i] = mergeTwoLists(lists[i], lists[i + step]);
            }
            step = nextStep;
        }

        return lists[0];
    }

    public ListNode mergeTwoLists(ListNode l1, ListNode l2) {
        if (l1 == null) return l2;
        if (l2 == null) return l1;

        head.next = null;
        ListNode cur = head;
        while (l1 != null && l2 != null) {
            if (l1.val <= l2.val) {
                cur = cur.next = l1;
                l1 = l1.next;
            } else {
                cur = cur.next = l2;
                l2 = l2.next;
            }
        }

        if (l1 == null) {
            cur.next = l2;
        } else if (l2 == null) {
            cur.next = l1;
        }
        return head.next;
    }
}

class _23_合并K个排序链表2 {
    /**
     * 思路4 - 优先级队列 (小顶堆), O(nlogk), 空间复杂度: O(k)
     */
    public ListNode mergeKLists(ListNode[] lists) {
        if (lists == null || lists.length == 0) return null;

        ListNode head = new ListNode(0);
        ListNode cur = head;

        // 将所有链表的头结点添加到小顶堆 (优先级队列) 中
        PriorityQueue<ListNode> queue = new PriorityQueue<>((ListNode node1, ListNode node2) -> {
            return node1.val - node2.val;
        });
        for (ListNode list : lists) {
            if (list == null) continue;
            queue.offer(list);
        } // klogk

        // 不断删除堆顶元素, 并且把堆顶元素的next添加到堆中
        while (!queue.isEmpty()) {
            // 删除堆顶元素
            ListNode node = queue.poll();
            cur = cur.next = node;
            if (node.next != null) {
                queue.offer(node.next);
            }
        } // nlogk

        return head.next;
    }
}

class _23_合并K个排序链表3 {
    // 虚拟头节点 (dummy head)
    private ListNode head = new ListNode(0);

    /**
     * 思路3 - 逐一两两合并, O(kn)
     */
    public ListNode mergeKLists(ListNode[] lists) {
        if (lists == null || lists.length == 0) return null;

        // k条链表, 总共n个节点, 每一条链表平均 n/k 个节点

        for (int i = 1; i < lists.length; i++) { // k - 1次的复杂度累加起来
            lists[0] = mergeTwoLists(lists[0], lists[i]);
        }

        return lists[0];
    }

    public ListNode mergeTwoLists(ListNode l1, ListNode l2) {
        if (l1 == null) return l2;
        if (l2 == null) return l1;

        head.next = null;
        ListNode cur = head;
        while (l1 != null && l2 != null) {
            if (l1.val <= l2.val) {
                cur = cur.next = l1;
                l1 = l1.next;
            } else {
                cur = cur.next = l2;
                l2 = l2.next;
            }
        }

        if (l1 == null) {
            cur.next = l2;
        } else if (l2 == null) {
            cur.next = l1;
        }
        return head.next;
    }
}

class _23_合并K个排序链表4 {
    // 10条链表
    // 每条链表有1W个节点
    // k == 10
    // n == 10W

    /**
     * 思路2 - 逐一比较, O(kn)
     */
    public ListNode mergeKLists(ListNode[] lists) {
        if (lists == null || lists.length == 0) return null;
        ListNode head = new ListNode(0);
        ListNode cur = head;

        while (true) {
            int minIndex = -1;
            for (int i = 0; i < lists.length; i++) {
                if (lists[i] == null) continue;

                if (minIndex == -1 || lists[i].val < lists[minIndex].val) {
                    minIndex = i;
                }
            } // O(k)
            // 所有链表节点已经串起来了
            if (minIndex == -1) break;

            cur = cur.next = lists[minIndex];
            lists[minIndex] = lists[minIndex].next;
        } // O(kn)

        return head.next;
    }
}

class _23_合并K个排序链表5 {
    /**
     * 思路1 - 最笨的解法, O(nlogn), 空间复杂度: O(n)
     */
    public ListNode mergeKLists(ListNode[] lists) {
        if (lists == null || lists.length == 0) return null;

        // 将所有节点添加到数组中
        List<ListNode> nodes = new ArrayList<>();
        for (ListNode list : lists) {
            while (list != null) {
                nodes.add(list);
                list = list.next;
            }
        } // O(n)

        // 对数组进行排序 (基于比较的排序, 时间复杂度目前最好是O(nlogn), n是所有节点的数量)
        nodes.sort((ListNode node1, ListNode node2) -> {
            return node1.val - node2.val;
        });

        // 将排好序的节点串起来
        ListNode head = new ListNode(0);
        ListNode cur = head;
        for (ListNode node : nodes) {
            cur = cur.next = node;
        } // O(n)
        return head.next;
    }
}
