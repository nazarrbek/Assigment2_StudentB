package algorithms;

import metrics.PerformanceTracker;
import java.util.Objects;

public class HeapSort {
    public static void sort(int[] a, PerformanceTracker t) {
        Objects.requireNonNull(a);
        if (t == null) t = new PerformanceTracker();
        int n = a.length;

        for (int i = parent(n - 1); i >= 0; i--) {
            siftDown(a, i, n, t);
        }

        for (int end = n - 1; end > 0; end--) {
            swap(a, 0, end, t);
            siftDown(a, 0, end, t);
        }
    }

    private static int parent(int i) { return (i - 1) / 2; }
    private static int left(int i) { return 2 * i + 1; }
    private static int right(int i) { return 2 * i + 2; }

    private static void siftDown(int[] a, int i, int size, PerformanceTracker t) {
        while (true) {
            int l = left(i), r = right(i);
            int largest = i;
            if (l < size) {
                t.incArrayAccesses(1); t.incComparisons(1);
                if (a[l] > a[largest]) largest = l;
            }
            if (r < size) {
                t.incArrayAccesses(1); t.incComparisons(1);
                if (a[r] > a[largest]) largest = r;
            }
            if (largest == i) break;
            swap(a, i, largest, t);
            i = largest;
        }
    }

    private static void swap(int[] a, int i, int j, PerformanceTracker t) {
        t.incSwaps(1); t.incArrayAccesses(2);
        int tmp = a[i]; a[i] = a[j]; a[j] = tmp;
    }
}
