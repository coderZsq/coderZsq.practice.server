package com.coderZsq;

public class BloomFilter<T> {

    /**
     * 二进制向量的长度(一共有多少个二进制位)
     */
    private int bitSize;

    /**
     * 二进制向量
     */
    private long[] bits;

    /**
     * 哈希函数的个数
     */
    private int hashSize;

    /**
     * @param n 数据规模
     * @param p 误判率, 取值范围(0, 1)
     */
    public BloomFilter(int n, double p) {
        if (n <= 0 || p <= 0 || p >= 1) {
            throw new IllegalArgumentException("wrong  n or p");
        }

        double ln2 = Math.log(2);
        // 求出二进制向量的长度
        bitSize = (int) (-(n * Math.log(p)) / (ln2 * ln2));
        // 求出哈希函数的个数
        hashSize = (int) (bitSize * ln2 / n);
        // bits数组的长度
        bits = new long[(bitSize + Long.SIZE - 1) / Long.SIZE];
        // 每一页显示100条数据, pageSize
        // 一共999999条数据, n
        // 请问多少页 pageCount = (n + pageSize - 1) / pageSize
    }

    /**
     * 添加元素
     * @param value
     */
    public void put(T value) {
        nullCheck(value);

        // 利用value生成2个整数
        int hash1 = value.hashCode();
        int hash2 = hash1 >>> 16;

        for (int i = 0; i < hashSize; i++) {
            int combinedHash = hash1 + (i * hash2);
            if (combinedHash < 0) {
                combinedHash = ~combinedHash;
            }
            // 生成一个二进制位的索引
            int index = combinedHash % bitSize;
            // 设置index位置的二进制位为1
            set(index);

            //   101010101010010101
            // | 000000000000000100   1 << index
            //   101010101010010101
        }
    }

    /**
     * 判断一个元素是否村早
     * @param value
     * @return
     */
    public boolean contains(T value) {
        nullCheck(value);
        // 利用value生成2个整数
        int hash1 = value.hashCode();
        int hash2 = hash1 >>> 16;

        for (int i = 0; i < hashSize; i++) {
            int combinedHash = hash1 + (i * hash2);
            if (combinedHash < 0) {
                combinedHash = ~combinedHash;
            }
            // 生成一个二进制位的索引
            int index = combinedHash % bitSize;
            // 查询index位置的二进制位为0
            if (!get(index)) return false;
        }
        return true;
    }

    /**
     * 设置index位置的二进制位为1
     */
    private void set(int index) {

    }

    /**
     * 查看index位置的二进制位的值
     * @return true代表1, false代表0
     */
    private boolean get(int index) {
        return false;
    }

    private void nullCheck(T value) {
        if (value == null) {
            throw new IllegalArgumentException("Value must not be null.");
        }
    }
}
