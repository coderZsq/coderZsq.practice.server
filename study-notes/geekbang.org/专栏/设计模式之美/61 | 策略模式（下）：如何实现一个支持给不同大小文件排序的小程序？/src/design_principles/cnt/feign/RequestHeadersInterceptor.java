package design_principles.cnt.feign;

public class RequestHeadersInterceptor implements RequestInterceptor {
    @Override
    public void apply(RequestTemplate template) {
        template.header("appId", "...");
        template.header("version", "...");
        template.header("timestamp", "...");
        template.header("token", "...");
        template.header("idempotent-token", "...");
        template.header("sequence-id", "...");
    }
}