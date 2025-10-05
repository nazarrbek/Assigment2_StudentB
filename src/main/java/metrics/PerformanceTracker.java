package metrics;

import java.io.FileWriter;
import java.io.IOException;
import java.util.concurrent.atomic.AtomicLong;

public class PerformanceTracker {
    public AtomicLong comparisons = new AtomicLong();
    public AtomicLong swaps = new AtomicLong();
    public AtomicLong arrayAccesses = new AtomicLong();
    private long startNs;
    private long endNs;

    public void start() { startNs = System.nanoTime(); }
    public void stop() { endNs = System.nanoTime(); }
    public long timeNs() { return endNs - startNs; }

    public void incComparisons(long v) { comparisons.addAndGet(v); }
    public void incSwaps(long v) { swaps.addAndGet(v); }
    public void incArrayAccesses(long v) { arrayAccesses.addAndGet(v); }

    public String toCsvLine(String algorithm, String variant, int n, String distribution, int trial) {
        return String.join(",",
            algorithm,
            variant,
            String.valueOf(n),
            distribution,
            String.valueOf(trial),
            String.valueOf(timeNs()),
            String.valueOf(comparisons.get()),
            String.valueOf(swaps.get()),
            String.valueOf(arrayAccesses.get())
        );
    }

    public static void writeCsvHeader(String path) throws IOException {
        try (FileWriter w = new FileWriter(path, false)) {
            w.write("algorithm,variant,n,distribution,trial,timeNs,comparisons,swaps,arrayAccesses\n");
        }
    }

    public void appendCsv(String path, String line) throws IOException {
        try (FileWriter w = new FileWriter(path, true)) {
            w.write(line + "\n");
        }
    }
}
