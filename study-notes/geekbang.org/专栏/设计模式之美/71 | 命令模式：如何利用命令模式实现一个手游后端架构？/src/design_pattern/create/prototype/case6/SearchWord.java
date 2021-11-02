package design_pattern.create.prototype.case6;

public class SearchWord {
    private long lastUpdateTime;
    private String keyword;
    private int count;

    public SearchWord(long lastUpdateTime, String keyword, int count) {
        this.lastUpdateTime = lastUpdateTime;
        this.keyword = keyword;
        this.count = count;
    }

    public int getCount() {
        return count;
    }

    public void setCount(int count) {
        this.count = count;
    }

    public long getLastUpdateTime() {
        return lastUpdateTime;
    }

    public void setLastUpdateTime(long lastUpdateTime) {
        this.lastUpdateTime = lastUpdateTime;
    }

    public String getKeyword() {
        return keyword;
    }

    public void setKeyword(String keyword) {
        this.keyword = keyword;
    }
}
