package algorithms;

import metrics.PerformanceTracker;
import java.util.Objects;

public class SelectionSort {
    // two-way selection: find min and max in single pass
    public static void sort(int[] a, PerformanceTracker t) {
        Objects.requireNonNull(a);
        if (t == null) t = new PerformanceTracker();
        int n = a.length;
        if (n <= 1) return;
        for (int left = 0, right = n - 1; left < right; left++, right--) {
            int minIdx = left, maxIdx = right;
            if (a[minIdx] > a[maxIdx]) { t.incComparisons(1); swap(a, minIdx, maxIdx, t); }
            for (int i = left + 1; i <= right - 1; i++) {
                t.incArrayAccesses(1);
                if (a[i] < a[minIdx]) { t.incComparisons(1); minIdx = i; }
                else { t.incComparisons(1); }
                t.incArrayAccesses(1);
                if (a[i] > a[maxIdx]) { t.incComparisons(1); maxIdx = i; }
                else { t.incComparisons(1); }
            }
            if (minIdx != left) swap(a, left, minIdx, t);
            if (maxIdx == left) maxIdx = minIdx;
            if (maxIdx != right) swap(a, maxIdx, right, t);
        }
    }

    private static void swap(int[] a, int i, int j, PerformanceTracker t) {
        t.incSwaps(1); t.incArrayAccesses(2);
        int tmp = a[i]; a[i] = a[j]; a[j] = tmp;
    }
}
