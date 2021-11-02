package design_principles.cnt.feign;

public class Feign {

    private Logger logger;
    private FormEncoder formEncoder;
    private JacksonDecoder jacksonDecoder;
    private ResponseErrorDecoder responseErrorDecoder;
    private RequestHeadersInterceptor requestHeadersInterceptor;

    public static Feign builder() {
        return new Feign();
    }

    public Feign logger(Logger logger) {
        this.logger = logger;
        return this;
    }

    public Feign encoder(FormEncoder formEncoder) {
        this.formEncoder = formEncoder;
        return this;
    }

    public Feign decoder(JacksonDecoder jacksonDecoder) {
        this.jacksonDecoder = jacksonDecoder;
        return this;
    }

    public Feign errorDecoder(ResponseErrorDecoder responseErrorDecoder) {
        this.responseErrorDecoder = responseErrorDecoder;
        return this;
    }

    public Feign requestInterceptor(RequestHeadersInterceptor requestHeadersInterceptor) {
        this.requestHeadersInterceptor = requestHeadersInterceptor;
        return this;
    }

    public Feign build() {
        return this;
    }
}
