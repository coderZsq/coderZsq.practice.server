package design_principles.cnt.feign;

public class Demo {
    public static void main(String[] args) {
        Feign feign = Feign.builder()
                .logger(new CustomizedLogger())
                .encoder(new FormEncoder(new JacksonEncoder()))
                .decoder(new JacksonDecoder())
                .errorDecoder(new ResponseErrorDecoder())
                .requestInterceptor(new RequestHeadersInterceptor()).build();
    }
}
