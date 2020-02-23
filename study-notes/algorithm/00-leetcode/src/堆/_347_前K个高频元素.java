package 堆;

import java.util.*;
import java.util.Map.Entry;

@SuppressWarnings("unchecked")
public class _347_前K个高频元素 {

    public static void main(String[] args) {
        int[] nums = {1, 1, 1, 2, 2, 3};
        new _347_前K个高频元素().topKFrequent(nums, 2);
    }

    /**
     * 快速排序
     */
    public List<Integer> topKFrequent(int[] nums, int k) {
        // 利用Map存储每个整数出现的次数
        Map<Integer, Integer> counts = new HashMap<>();
        for (int num : nums) {
            counts.put(num, counts.getOrDefault(num, 0) + 1);
        }

        Entry<Integer, Integer>[] entries = new Entry[counts.size()];
        counts.entrySet().toArray(entries);
        int begin = 0;
        int end = entries.length;
        int pivotIndex = 0;
        int resultIndex = k - 1;
        while ((pivotIndex = pivotIndex(entries, begin, end)) != resultIndex) {
            if (pivotIndex > resultIndex) {
                end = pivotIndex;
            } else {
                begin = pivotIndex + 1;
            }
        }

        List<Integer> result = new LinkedList<>();
        for (int i = 0; i < k; i++) {
            result.add(entries[i].getKey());
        }
        return result;
    }

    private int pivotIndex(Entry<Integer, Integer>[] entries, int begin, int end) {
        int newBegin = begin + (int) (Math.random() * (end - begin));
        Entry<Integer, Integer> tmp = entries[begin];
        entries[begin] = entries[newBegin];
        entries[newBegin] = tmp;
        Entry<Integer, Integer> pivot = entries[begin];
        end--;
        while (begin < end) {
            while (begin < end) {
                if (pivot.getValue() > entries[end].getValue()) {
                    end--;
                } else {
                    entries[begin++] = entries[end];
                    break;
                }
            }
            while (begin < end) {
                if (pivot.getValue() < entries[begin].getValue()) {
                    begin++;
                } else {
                    entries[end--] = entries[begin];
                    break;
                }
            }
        }
        entries[begin] = pivot;
        return begin;
    }

    /**
     * 桶排序
     */
    public List<Integer> topKFrequent4(int[] nums, int k) {
        Map<Integer, Integer> counts = new HashMap<>();
        for (int num : nums) { // O(n)
            counts.put(num, counts.getOrDefault(num, 0) + 1);
        }

        List<Integer>[] buckets = new List[nums.length + 1];
        int maxNo = 0;
        for (Entry<Integer, Integer> entry : counts.entrySet()) {  // O(m)
            int no = entry.getValue();
            List<Integer> bucket = buckets[no];
            if (bucket == null) {
                bucket = new LinkedList<>();
                buckets[no] = bucket;
                maxNo = Math.max(maxNo, no);
            }
            bucket.add(entry.getKey());
        }

        List<Integer> result = new LinkedList<>();
        for (int i = maxNo; i > 0 && result.size() < k; i--) { // O(n)
            if (buckets[i] == null) continue;
            result.addAll(buckets[i]);
        }
        return result;
    }

    /**
     * 优先级队列
     */
    public List<Integer> topKFrequent3(int[] nums, int k) {
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
}
