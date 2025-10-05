package algorithms;

import metrics.PerformanceTracker;
import java.util.Arrays;
import java.util.NoSuchElementException;

public class MaxHeap {
    private int[] heap;
    private int size;
    private PerformanceTracker t;

    public MaxHeap(int capacity, PerformanceTracker tracker) {
        heap = new int[Math.max(1, capacity)];
        size = 0;
        this.t = tracker == null ? new PerformanceTracker() : tracker;
    }

    public void insert(int val) {
        ensureCapacity(size + 1);
        heap[size] = val;
        t.incArrayAccesses(1);
        siftUp(size++);
    }

    public int extractMax() {
        if (size == 0) throw new NoSuchElementException();
        int max = heap[0];
        t.incArrayAccesses(1);
        heap[0] = heap[--size];
        t.incArrayAccesses(1);
        siftDown(0);
        return max;
    }

    public void increaseKey(int index, int newVal) {
        if (index < 0 || index >= size) throw new IndexOutOfBoundsException();
        if (newVal < heap[index]) throw new IllegalArgumentException("new key smaller");
        heap[index] = newVal; t.incArrayAccesses(1);
        siftUp(index);
    }

    private void siftUp(int i) {
        while (i > 0) {
            int p = (i - 1) / 2;
            t.incArrayAccesses(2); t.incComparisons(1);
            if (heap[i] <= heap[p]) break;
            swap(i, p); i = p;
        }
    }

    private void siftDown(int i) {
        while (true) {
            int l = 2 * i + 1, r = 2 * i + 2, largest = i;
            if (l < size) { t.incArrayAccesses(1); t.incComparisons(1); if (heap[l] > heap[largest]) largest = l; }
            if (r < size) { t.incArrayAccesses(1); t.incComparisons(1); if (heap[r] > heap[largest]) largest = r; }
            if (largest == i) break;
            swap(i, largest); i = largest;
        }
    }

    private void swap(int i, int j) { t.incSwaps(1); t.incArrayAccesses(2); int tmp = heap[i]; heap[i] = heap[j]; heap[j] = tmp; }

    private void ensureCapacity(int min) {
        if (min > heap.length) {
            heap = Arrays.copyOf(heap, heap.length * 2);
        }
    }

    public int size() { return size; }
}
