package design_pattern.create.singleton.case10;

import java.util.concurrent.atomic.AtomicLong;

public enum IdGenerator {
    INSTANCE;
    private AtomicLong id = new AtomicLong(0);

    public long getId() {
        return id.incrementAndGet();
    }
}