package com.coderZsq;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;
import java.util.List;

public class Main {

    public static void main(String[] args) {
        /*
         * 集合(Collections)
         *
         * java.util 包中有个集合框架(Collections Framework)，提供了一大堆常用的数据结构
         * ArrayList、LinkedList、Queue、Stack、HashSet、HashMap 等
         *
         * 数据结构是计算机存储、组织数据的方式
         *
         * 在实际应用中，根据使用场景来选择最合适的数据结构
         * */

        /*
         * 集合框架预览
         *
         * Iterable
         * Collection
         *
         * Queue Deque
         * List LinkedList ArrayList Stack Vector
         *
         * AbstractSequentialList AbstractList AbstractCollection
         *
         * Set HashSet AbstractSet
         * Map HashMap AbstractMap
         *
         * 思考题:为何 Map 如此独立?跟 Collection、Iterable 毫无继承关系?
         * */

        /*
         * List vs Set vs Map
         *
         * List 的特点
         * 可以存储重复的元素
         * 元素之间 equals 可能返回 true
         * 可以通过索引访问元素
         * 有记录元素的添加顺序
         *
         * Set 的特点
         * 不可以存储重复的元素
         * 元素之间 equals 不可能返回 true
         * 不可以通过索引访问元素
         * 不记录元素的添加顺序(LinkedHashSet 除外)
         *
         * Map 的特点
         *
         * 不可以存储重复的 key
         * key 之间 equals 不可能返回 true
         * 不可以通过索引访问 key-value
         * 不记录 key-value 的添加顺序(LinkedHashMap 除外)
         * */

        /*
         * 数组的局限性
         *
         * 数组的局限性
         * 无法动态扩容
         * 操作元素的过程不够面向对象
         * ......
         *
         * java.util.ArrayList 是 Java 中的动态数组
         * 一个可以动态扩容的数组
         * 封装了各种实用的数组操作
         * */
        {
            int[] array = new int[4];
            array[0] = 11;
            array[1] = 22;
            array[2] = 33;
        }

        /*
         * ArrayList的常用方法
         * */
        {
            // public int size()
            // public boolean isEmpty()
            // public boolean contains(Object o)
            // public int indexOf(Object o)
            // public int lastIndexOf(Object o)
            // public E get(int index)
            // public E set(int index, E element)
            // public boolean add(E e)
            // public void add(int index, E element)
            // public E remove(int index)
            // public boolean remove(Object o)
            // public void clear()
        }

        {
            // public Object[] toArray()
            // public <T> T[] toArray(T[] a)
            // public void trimToSize()
            // public void ensureCapacity(int minCapacity)
        }

        {
            // public boolean addAll(Collection<? extends E> c)
            // public boolean addAll(int index, Collection<? extends E> c)
            // public boolean removeAll(Collection<?> c)
            // public boolean retainAll(Collection<?> c)
            // public void forEach(Consumer<? super E> action)
            // public void sort(Comparator<? super E> c)
        }

        /*
         * ArrayList的基本使用
         * */
        {
            ArrayList list = new ArrayList<>();
            list.add(11);
            list.add(false);
            list.add(null); // 可以添加null值
            list.add(3.14);
            list.add(0, "Jack");
            list.add('8');
            // 3
            System.out.println(list.indexOf(null));
            // 6
            System.out.println(list.size());
            // [Jack, 11, false, null, 3.14, 8]
            System.out.println(list);
        }

        /*
         * ArrayList - retainAll
         * */
        {
            List<Integer> list1 = new ArrayList<>();
            list1.add(11);
            list1.add(22);
            list1.add(33);
            list1.add(44);

            List<Integer> list2 = new ArrayList<>();
            list2.add(22);
            list2.add(44);

            // 从 list1 中删掉除 list2 中元素以外的所有元素
            list1.retainAll(list2);

            // [22, 44]
            System.out.println(list1);
        }

        {
            /*
             * ArrayList - toArray
             * */
            List<Integer> list = new ArrayList<>();
            list.add(11);
            list.add(22);
            list.add(33);
            {
                Object[] array1 = list.toArray();
                // [Ljava.lang.Object;@2503dbd3
                System.out.println(array1);
                // [11, 22, 33]
                System.out.println(Arrays.toString(array1));
            }

            {
                Integer[] array2 = list.toArray(new Integer[0]);
                // [Ljava.lang.Integer;@4b67cf4d
                System.out.println(array2);
                // [11, 22, 33]
                System.out.println(Arrays.toString(array2));
            }

            /*
             * ArrayList的遍历
             * */
            {
                int size = list.size();
                for (int i = 0; i < size; i++) {
                    System.out.println(list.get(i));
                }
            }

            {
                Iterator it = list.iterator();
                while (it.hasNext()) {
                    Object obj = it.next();
                    System.out.println(obj);
                }
            }

            {
                for (Object obj : list) {
                    System.out.println(obj);
                }
            }

            {
                list.forEach((obj) -> {
                    System.out.println(obj);
                });
            }
        }

        /*
         * for-each 格式
         *
         * 实现了 Iterable 接口的对象，都可以使用 for-each 格式遍历元素
         * 比如 List、Set 等
         * Iterable 在使用 foreach 格式遍历元素时，本质是使用了 Iterator 对象
         * */
        {
            // for-each 格式
            // for (元素类型 变量名 : 数组\Iterable) {
            //
            // }
        }

        /*
         * 自定义 Iterable、Iterator
         * */
        {
            ClassRoom room = new ClassRoom("Jack", "Rose");
            for (String string : room) {
                System.out.println(string);
            } // Jack Rose
        }

        /*
         * ArrayList 的扩容原理
         *
         * ArrayList 的最小容量是 10
         * 每次扩容时，新容量是旧容量的 1.5 倍
         * */
        {
            ArrayList<Integer> list = new ArrayList<>();
            list.add(11);
            list.add(22);
            list.add(33);
            list.add(44);
            list.add(55);
        }

        {
            /*
             * 遍历的注意点
             *
             * 需求:通过遍历的方式，挨个删除所有的元素
             *
             * 如果希望在遍历元素的同时删除元素
             * 请使用 Iterator 进行遍历
             * 然后使用 Iterator 的 remove 方法删除元素
             *
             * 如果在遍历集合元素的时候，使用了集合自带的方法修改集合的长度(比如 add、remove 等方法)
             * 那么可能会抛出 java.util.ConcurrentModificationException 异常
             * */
            List<Integer> list = new ArrayList<>();
            list.add(11);
            list.add(22);
            list.add(33);
            list.add(44);
            {
                int size = list.size();
                for (int i = 0; i < size; i++) {
                    // java.lang.IndexOutOfBoundsException
                    // list.remove(i);
                }
            }

            {
                for (int i = 0; i < list.size(); i++) {
                    // list.remove(i);
                }
                // [22, 44]
                System.out.println(list);
            }

            {
                // java.util.ConcurrentModificationException
                for (Integer e : list) {
                    // list.remove(e);
                }
            }

            {
                // java.util.ConcurrentModificationException
                list.forEach((e) -> {
                    // list.remove(e);
                });
            }

            {
                Iterator<Integer> it = list.iterator();
                while (it.hasNext()) {
                    it.next();
                    it.remove();
                }
                // 0
                System.out.println(list.size());
            }
        }
    }
}
