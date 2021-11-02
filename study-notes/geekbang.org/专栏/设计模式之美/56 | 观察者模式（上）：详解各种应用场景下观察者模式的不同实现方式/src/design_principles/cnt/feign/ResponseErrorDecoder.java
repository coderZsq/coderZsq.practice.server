package design_principles.cnt.feign;

import design_principles.lsp.case1.Response;

public class ResponseErrorDecoder implements ErrorDecoder {
    @Override
    public Exception decode(String methodKey, Response response) {
        //...
        return null;
    }
}
