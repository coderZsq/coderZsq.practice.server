package 算法;

public class BSearch {
    public static void main(String[] args) {
        int[] a = new int[]{1, 2, 2, 2, 2, 2, 4, 4, 5};
        System.out.println(bsearch1(a, a.length, 2));
        System.out.println(bsearch2(a, a.length, 2));
        System.out.println(bsearch3(a, a.length, 3));
        System.out.println(bsearch4(a, a.length, 3));
    }

    // 变体一: 查找第一个值等于给定值的元素
    public static int bsearch1(int[] a, int n, int value) {
        int low = 0;
        int high = n - 1;
        while (low <= high) {
            int mid = low + ((high - low) >> 1);
            if (a[mid] > value) {
                high = mid - 1;
            } else if (a[mid] < value) {
                low = mid + 1;
            } else {
                if ((mid == 0) || (a[mid - 1] != value)) return mid;
                else high = mid - 1;
            }
        }
        return -1;
    }

    // 变体二: 查找最后一个值等于给定值的元素
    public static int bsearch2(int[] a, int n, int value) {
        int low = 0;
        int high = n - 1;
        while (low <= high) {
            int mid = low + ((high - low) >> 1);
            if (a[mid] > value) {
                high = mid - 1;
            } else if (a[mid] < value) {
                low = mid + 1;
            } else {
                if (a[mid] == n - 1 || a[mid + 1] != value) return mid;
                else low = mid + 1;
            }
        }
        return -1;
    }

    // 变体三: 查找第一个大于等于给定值的元素
    public static int bsearch3(int[] a, int n, int value) {
        int low = 0;
        int high = n - 1;
        while (low <= high) {
            int mid = low + ((high - low) >> 1);
            if (a[mid] >= value) {
                if ((mid == 0) || a[mid - 1] < value) return mid;
                else high = mid - 1;
            } else {
                low = mid + 1;
            }
        }
        return -1;
    }

    // 变体四: 查找最后一个小于等于给定值的元素
    public static int bsearch4(int[] a, int n, int value) {
        int low = 0;
        int high = n - 1;
        while (low <= high) {
            int mid = low + ((high - low) >> 1);
            if (a[mid] > value) {
                high = mid - 1;
            } else {
                if ((mid == n - 1) || (a[mid + 1] > value)) return mid;
                else low = mid + 1;
            }
        }
        return -1;
    }
}
