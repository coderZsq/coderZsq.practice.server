package object_oriented.inf.case1;

// 接口实现类：限流过滤器
public class RateLimitFilter implements Filter {
    @Override
    public void doFilter(RpcRequest req) throws RpcException {
        //...限流逻辑...
    }
}
