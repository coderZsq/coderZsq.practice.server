package object_oriented.inf.case1;

import java.util.ArrayList;
import java.util.List;

// 过滤器使用Demo
public class Application {
    // filters.add(new AuthencationFilter());
    // filters.add(new RateLimitFilter());
    private List<Filter> filters = new ArrayList<>();

    public void handleRpcRequest(RpcRequest req) {
        try {
            for (Filter filter : filters) {
                filter.doFilter(req);
            }
        } catch(RpcException e) {
            // ...处理过滤结果...
        }
        // ...省略其他处理逻辑...
    }
}