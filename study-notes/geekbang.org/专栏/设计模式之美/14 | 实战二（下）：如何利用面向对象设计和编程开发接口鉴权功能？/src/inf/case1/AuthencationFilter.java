package inf.case1;

// 接口实现类：鉴权过滤器
public class AuthencationFilter implements Filter {
    @Override
    public void doFilter(RpcRequest req) throws RpcException {
        //...鉴权逻辑..
    }
}