package design_principles.ocp.case3;

public class ApiStatInfo {//省略constructor/getter/setter方法
    private String api;
    private long requestCount;
    private long errorCount;
    private long durationOfSeconds;

    public String getApi() {
        return api;
    }

    public long getRequestCount() {
        return requestCount;
    }

    public long getErrorCount() {
        return errorCount;
    }

    public long getDurationOfSeconds() {
        return durationOfSeconds;
    }
}