package design_principles.cnt.feign;

public interface RequestInterceptor {
    void apply(RequestTemplate template);
}
