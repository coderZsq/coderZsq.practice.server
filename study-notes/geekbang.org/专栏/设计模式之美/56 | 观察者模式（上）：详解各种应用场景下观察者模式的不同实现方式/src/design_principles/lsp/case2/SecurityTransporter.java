package design_principles.lsp.case2;

import design_principles.lsp.case1.Request;
import design_principles.lsp.case1.Response;
import design_principles.lsp.case1.StringUtils;
import design_principles.lsp.case1.Transporter;
import sun.net.www.http.HttpClient;

// 改造后：
public class SecurityTransporter extends Transporter {
    private String appId;
    private String appToken;

    public SecurityTransporter(HttpClient httpClient, String appId, String appToken) {
        super(httpClient);
        this.appId = appId;
        this.appToken = appToken;
    }

    @Override
    public Response sendRequest(Request request) {
        if (StringUtils.isBlank(appId) || StringUtils.isBlank(appToken)) {
            try {
                throw new NoAuthorizationRuntimeException();
            } catch (NoAuthorizationRuntimeException e) {
                e.printStackTrace();
            }
        }
        request.addPayload("app-id", appId);
        request.addPayload("app-token", appToken);
        return super.sendRequest(request);
    }
}