package com.coderZsq;

import com.coderZsq.tools.Asserts;
import com.coderZsq.union.UnionFind;
import com.coderZsq.union.UnionFind_QF;
import com.coderZsq.union.UnionFind_QU;
import com.coderZsq.union.UnionFind_QU_S;

public class Main {

    public static void main(String[] args) {
        test(new UnionFind_QF(12));
        test(new UnionFind_QU(12));
        test(new UnionFind_QU_S(12));
    }

    static void test(UnionFind uf) {
        uf.union(0, 1);
        uf.union(0, 3);
        uf.union(0, 4);
        uf.union(2, 3);
        uf.union(2, 5);

        uf.union(6, 7);

        uf.union(9, 10);
        uf.union(9, 10);
        uf.union(9, 11);

        Asserts.test(!uf.isSame(2, 7));

        uf.union(4, 6);

        Asserts.test(uf.isSame(2, 7));
    }

    static void testTime() {
        int count = 100000;
        UnionFind uf = new UnionFind_QF(100000);
    }
}
