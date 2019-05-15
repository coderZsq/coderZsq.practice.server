package com.coderZsq.map;

import sun.security.krb5.internal.PAData;

import java.util.Comparator;

public class TreeMap<K, V> implements Map<K, V> {
    private static final boolean RED = false;
    private static final boolean BLACK = true;

    private int size;
    private Node<K, V> root;

    private Comparator<K> comparator;

    public TreeMap() {
        this(null);
    }

    public TreeMap(Comparator<K> comparator) {
        this.comparator = comparator;
    }


    public int size() {
        return size;
    }

    public boolean isEmpty() {
        return size == 0;
    }

    public void clear() {
        root = null;
        size = 0;
    }

    @Override
    public V put(K key, V value) {
        keyNotNullCheck(key);

        // 添加第一个节点
        if (root == null) {
            root = new Node<>(key, value, null);
            size++;

            // 新添加节点之后的处理
            afterPut(root);
            return null;
        }

        // 添加的不是第一个节点
        // 找到父节点
        Node<K, V> parent = root;
        Node<K, V> node = root;
        int cmp = 0;
        while (node != null) {
            cmp = compare(key, node.key);
            parent = node;
            if (cmp > 0) {
                node = node.right;
            } else if (cmp < 0) {
                node = node.left;
            } else { //相等
                node.key = key;
                V oldValue = node.value;
                node.value = value;
                return oldValue;
            }
        }

        // 看看插入到父节点的哪个位置
        Node<K, V> newNode = new Node<>(key, value, parent);
        if (cmp > 0) {
            parent.right = newNode;
        } else {
            parent.left = newNode;
        }
        size++;

        // 新添加节点之后的处理
        afterPut(newNode);
        return null;
    }

    @Override
    public V get(K key) {
        return null;
    }

    @Override
    public V remove(K key) {
        return null;
    }

    @Override
    public boolean containsKey(K key) {
        return false;
    }

    @Override
    public boolean containsValue(V value) {
        return false;
    }

    @Override
    public void traversal(Visitor<K, V> visitor) {

    }

    private void afterPut(Node<K, V> node) {
//        Node<E> parent = node.parent;
//
//        // 添加的是根节点或者上溢到达了根节点
//        if (parent == null) {
//            black(node);
//            return;
//        }
//
//        // 如果父节点是黑色, 直接返回
//        if (isBlack(parent)) return;
//
//        // 叔父节点
//        Node<E> uncle = parent.sibling();
//        // 祖父节点
//        Node<E> grand = red(parent.parent);
//        if (isRed(uncle)) { // 叔父节点是红色[B树节点上溢]
//            black(parent);
//            black(uncle);
//            // 祖父节点当做是新添加的节点
//            afterAdd(grand);
//            return;
//        }
//
//        // 叔父节点不是红色
//        if (parent.isLeftChild()) { // L
//            if (node.isLeftChild()) { // LL
//                black(parent);
//            } else { // LR
//                black(node);
//                rotateLeft(parent);
//            }
//            rotateRight(grand);
//        } else { // R
//            if (node.isLeftChild()) { // RL
//                black(node);
//                rotateRight(parent);
//            } else { // RR
//                black(parent);
//            }
//            rotateLeft(grand);
//        }
    }

    private int compare(K k1, K k2) {
        if (comparator != null) {
            return comparator.compare(k1, k2);
        }
        return ((Comparable<K>)k1).compareTo(k2);
    }

    private void keyNotNullCheck(K key) {
        if (key == null) {
            throw new IllegalArgumentException("key must not be null");
        }
    }

    private static class Node<K, V> {
        K key;
        V value;
        boolean color = RED;
        Node<K, V> left;
        Node<K, V> right;
        Node<K, V> parent;
        public Node(K key, V value, Node<K, V> parent) {
            this.key = key;
            this.value = value;
            this.parent = parent;
        }

        public boolean isLeaf() {
            return left == null && right == null;
        }

        public boolean hasTwoChildren() {
            return left != null && right != null;
        }

        public boolean isLeftChild() {
            return parent != null && this == parent.left;
        }

        public boolean isRightChild() {
            return parent != null && this == parent.right;
        }

        public Node<K, V> sibling() {
            if (isLeftChild()) {
                return parent.right;
            }

            if (isRightChild()) {
                return parent.left;
            }

            return null;
        }
    }
}
