package reconfiguration.cnt.case3;

public class RequestStat {
    private double maxResponseTime;
    private double minResponseTime;
    private double avgResponseTime;
    private double p999ResponseTime;
    private double p99ResponseTime;
    private long count;
    private long tps;

    public void setMaxResponseTime(double maxResponseTime) {
        this.maxResponseTime = maxResponseTime;
    }

    public void setMinResponseTime(double minResponseTime) {
        this.minResponseTime = minResponseTime;
    }

    public void setAvgResponseTime(double avgResponseTime) {
        this.avgResponseTime = avgResponseTime;
    }

    public void setP999ResponseTime(double p999ResponseTime) {
        this.p999ResponseTime = p999ResponseTime;
    }

    public void setP99ResponseTime(double p99ResponseTime) {
        this.p99ResponseTime = p99ResponseTime;
    }

    public void setCount(long count) {
        this.count = count;
    }

    public void setTps(long tps) {
        this.tps = tps;
    }

    //...省略getter/setter方法...
}