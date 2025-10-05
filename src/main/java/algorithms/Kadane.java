package algorithms;

import metrics.PerformanceTracker;
import java.util.Objects;

public class Kadane {
    public static class Result {
        public final long maxSum;
        public final int start;
        public final int end;
        public Result(long s, int st, int e) { maxSum = s; start = st; end = e; }
        @Override public String toString() { return String.format("Result(sum=%d,start=%d,end=%d)", maxSum, start, end); }
    }

    public static Result maxSubarray(int[] a, PerformanceTracker t) {
        Objects.requireNonNull(a);
        if (t == null) t = new PerformanceTracker();
        if (a.length == 0) return new Result(0, -1, -1);

        long maxSoFar = a[0], maxEndingHere = a[0];
        int start = 0, end = 0, s = 0;
        t.incArrayAccesses(1);
        for (int i = 1; i < a.length; i++) {
            t.incArrayAccesses(1);
            if (maxEndingHere + a[i] < a[i]) {
                t.incComparisons(1);
                maxEndingHere = a[i];
                s = i;
            } else {
                t.incComparisons(1);
                maxEndingHere += a[i];
            }
            if (maxEndingHere > maxSoFar) {
                t.incComparisons(1);
                maxSoFar = maxEndingHere;
                start = s; end = i;
            } else t.incComparisons(1);
        }
        return new Result(maxSoFar, start, end);
    }
}
