package SortSearchTimeTesting;

import java.time.Duration;

public class TestResult{
    private long duration;

    public TestResult(long duration) {
        this.duration = duration;
    }

    public long getDurationInNanos() {
        return duration;
    }
}
