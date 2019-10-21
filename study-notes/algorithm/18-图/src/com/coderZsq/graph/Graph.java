package com.coderZsq.graph;

import java.util.List;
import java.util.Set;

public interface Graph<V, E> {
    int edgesSize();
    int verticesSize();

    void addVertex(V v);
    void addEdge(V from, V to);
    void addEdge(V from, V to, E weight);

    void removeVertex(V v);
    void removeEdge(V from, V to);

    void bfs(V begin, VertexVisitor<V> visitor);
    void dfs(V begin, VertexVisitor<V> visitor);

    Set<EdgeInfo<V, E>> mst();

    List<V> topologicalSort();

    interface VertexVisitor<V> {
        boolean visit(V v);
    }

    class EdgeInfo<V, E> {
        V from;
        V to;
        E weight;
        public EdgeInfo(V from, V to, E weight) {
            this.from = from;
            this.to = to;
            this.weight = weight;
        }
    }
}
