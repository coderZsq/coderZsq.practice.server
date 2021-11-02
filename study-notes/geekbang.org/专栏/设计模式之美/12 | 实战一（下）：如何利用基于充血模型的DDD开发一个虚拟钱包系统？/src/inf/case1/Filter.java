package inf.case1;

// 接口
public interface Filter {
    void doFilter(RpcRequest req) throws RpcException;
}