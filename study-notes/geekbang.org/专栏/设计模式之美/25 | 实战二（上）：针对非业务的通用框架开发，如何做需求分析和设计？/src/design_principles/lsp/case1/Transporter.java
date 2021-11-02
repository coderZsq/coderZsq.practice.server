package design_principles.lsp.case1;

import sun.net.www.http.HttpClient;

public class Transporter {
    private HttpClient httpClient;

    public Transporter(HttpClient httpClient) {
        this.httpClient = httpClient;
    }

    public Response sendRequest(Request request) {
        // ...use httpClient to send request
        return null;
    }
}