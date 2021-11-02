package design_principles.cnt.feign;

import design_principles.lsp.case1.Response;

public interface ErrorDecoder {
    Exception decode(String methodKey, Response response);
}
