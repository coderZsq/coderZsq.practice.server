package 刷题.待面试.小红书;

import java.util.ArrayList;
import java.util.List;

public class SmartCollection {
    private List<Integer> list = new ArrayList<>();

    public static void main(String[] args) {
        SmartCollection o = new SmartCollection();
        for (int i = 100; i >= 0; i -= 3) {
            o.add(i);
        }
        System.out.println(o.list);
        System.out.println(o.query(53));
    }

    public void add(int num) {
        if (list.size() == 0) {
            list.add(num);
            return;
        }
        int insertIdx = 0;
        for (int i = list.size() - 1; i >= 0; i--) {
            if (list.get(i) < num) {
                insertIdx = i + 1;
                break;
            }
        }
        list.add(insertIdx, num);
    }

    public int query(int target) {
        int l = 0;
        int r = list.size() - 1;
        int lastVal = 0;
        while (l <= r) {
            int mid = ((r - l) >> 1) + l;
            if (list.get(mid) == target) return list.get(mid);
            else if (list.get(mid) < target) {
                lastVal = list.get(mid);
                l = mid + 1;
            } else {
                r = mid - 1;
            }
        }
        return lastVal;
    }
}
