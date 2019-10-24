package 堆;

import java.util.*;
import java.util.Map.Entry;

@SuppressWarnings("unchecked")
public class _347_前K个高频元素 {

    /**
     * 优先级队列
     */
    public List<Integer> topKFrequent(int[] nums, int k) {
        Map<Integer, Integer> counts = new HashMap<>();
        for (int num : nums) { // O(n)
            counts.put(num, counts.getOrDefault(num, 0) + 1);
        }

        // PriorityQueue 最小堆
        PriorityQueue<Integer> queue = new PriorityQueue<>(
                (Integer i1, Integer i2) -> {
                    return counts.get(i1) - counts.get(i2);
                });
        for (Entry<Integer, Integer> entry : counts.entrySet()) { // O(mlogk)
            if (queue.size() < k) {
                queue.offer(entry.getKey());
            } else if (entry.getValue() > counts.get(queue.peek())) {
                queue.poll();
                queue.offer(entry.getKey());
            }
        }

        List<Integer> result = new LinkedList<>();
        while (!queue.isEmpty()) { // O(klogk)
            result.add(0, queue.poll());
        }
        return result;
    }

    public List<Integer> topKFrequent2(int[] nums, int k) {
        Map<Integer, Integer> counts = new HashMap<>();
        for (int num : nums) { // O(n)
            counts.put(num, counts.getOrDefault(num, 0) + 1);
        }

        // PriorityQueue 最小堆
        PriorityQueue<Entry<Integer, Integer>> queue = new PriorityQueue<>(
                (Entry<Integer, Integer> e1, Entry<Integer, Integer> e2) -> {
            return e1.getValue() - e2.getValue();
        });
        for (Entry<Integer, Integer> entry : counts.entrySet()) { // O(mlogk)
            if (queue.size() < k) {
                queue.offer(entry);
            } else if (entry.getValue() > queue.peek().getValue()) {
                queue.poll();
                queue.offer(entry);
            }
        }

        List<Integer> result = new LinkedList<>();
        while (!queue.isEmpty()) { // O(klogk)
            Entry<Integer, Integer> entry = queue.poll();
            result.add(0, entry.getKey());
        }
        return result;
    }

    /**
     * 对entries全排序
     */
    public List<Integer> topKFrequent1(int[] nums, int k) {
        // 利用Map存储每个整数出现的次数
        Map<Integer, Integer> counts = new HashMap<>();
        // nums = [1, 1, 1, 2, 2, 3]
        for (int num : nums) {
//            Integer count = counts.get(num);
//            count = count == null ? 0 : count;
            counts.put(num, counts.getOrDefault(num, 0) + 1);
        }

        // 假设规模为 n 的数组中, 不同元素的个数是 m
        Entry<Integer, Integer>[] entries = new Entry[counts.size()];
        counts.entrySet().toArray(entries); // O(m)
        Arrays.sort(entries, (Entry<Integer, Integer> e1, Entry<Integer, Integer> e2) -> {
            return e2.getValue() - e1.getValue();
        }); // O(mlogm)

        List<Integer> result = new ArrayList<>();
        for (int i = 0; i < k; i++) { // O(k)
            result.add(entries[i].getKey());
        }

        return result;
    }

    public static void main(String[] args) {
        int[] nums = {1, 1, 1, 2, 2, 3};
        new _347_前K个高频元素().topKFrequent(nums, 2);
    }
}
