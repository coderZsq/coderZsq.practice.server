package 题库;

import java.util.*;

/**
 * 设计一个支持在平均 时间复杂度 O(1) 下， 执行以下操作的数据结构。
 *
 * 注意: 允许出现重复元素。
 *
 * insert(val)：向集合中插入元素 val。
 * remove(val)：当 val 存在时，从集合中移除一个 val。
 * getRandom：从现有集合中随机获取一个元素。每个元素被返回的概率应该与其在集合中的数量呈线性相关。
 * 示例:
 *
 * // 初始化一个空的集合。
 * RandomizedCollection collection = new RandomizedCollection();
 *
 * // 向集合中插入 1 。返回 true 表示集合不包含 1 。
 * collection.insert(1);
 *
 * // 向集合中插入另一个 1 。返回 false 表示集合包含 1 。集合现在包含 [1,1] 。
 * collection.insert(1);
 *
 * // 向集合中插入 2 ，返回 true 。集合现在包含 [1,1,2] 。
 * collection.insert(2);
 *
 * // getRandom 应当有 2/3 的概率返回 1 ，1/3 的概率返回 2 。
 * collection.getRandom();
 *
 * // 从集合中删除 1 ，返回 true 。集合现在包含 [1,2] 。
 * collection.remove(1);
 *
 * // getRandom 应有相同概率返回 1 和 2 。
 * collection.getRandom();
 *
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/insert-delete-getrandom-o1-duplicates-allowed
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 */
public class _381_O_1_时间插入_删除和获取随机元素_允许重复 {
    class RandomizedCollection {
        Map<Integer, Set<Integer>> map;
        List<Integer> list;

        public RandomizedCollection() {
            map = new HashMap<>();
            list = new ArrayList<>();
        }

        public boolean insert(int val) {
            list.add(val);
            // 用于存放数组索引的set
            Set<Integer> set = map.getOrDefault(val, new HashSet<>());
            // 存储数据在数组中的下标
            set.add(list.size() - 1);
            // key 为插入的数字, 值为插入数字在数组内的索引集合
            map.put(val, set);
            // 如果集合的size等于1则判定为第一次加入
            return set.size() == 1;
        }

        public boolean remove(int val) {
            if (!map.containsKey(val)) return false;

            // 索引集合的迭代器
            Iterator<Integer> it = map.get(val).iterator();
            // 取set中存储的数组下标
            int idx = it.next();
            // 数组的最后一个索引
            int lastIdx = list.size() - 1;
            // 数组的最后一位的值
            int lastVal = list.get(lastIdx);

            // 将需要删除的值用数组的最后一位值替换, 删除时 O(n) -> O(1)
            list.set(idx, lastVal);
            // 删除数组最后一位值
            list.remove(lastIdx);

            // 在set中删除本次迭代索引
            map.get(val).remove(idx);
            // 在最后一个set中删除最后一位的索引
            map.get(lastVal).remove(lastIdx);
            // 如果删除的不是最后一位
            if (idx < lastIdx) {
                // 新增set替换值的索引
                map.get(lastVal).add(idx);
            }

            // 如果set的值空了, 则删除key
            if (map.get(val).isEmpty()) map.remove(val);
            return true;
        }

        public int getRandom() {
            // 取随机值
            return list.get((int) (Math.random() * list.size()));
        }
    }
}
