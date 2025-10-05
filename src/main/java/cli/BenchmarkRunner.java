package cli;

import metrics.PerformanceTracker;
import algorithms.SelectionSort;
import algorithms.HeapSort;
import algorithms.Kadane;
import algorithms.MaxHeap;

import java.util.Random;
import java.util.Arrays;

public class BenchmarkRunner {
    public static void main(String[] args) throws Exception {
        if (args.length < 5) {
            System.out.println("Usage: <algorithm> <n> <distribution> <trials> <out.csv>");
            System.out.println("Algorithms: selection, heapsort, kadane, maxheap");
            System.exit(1);
        }
        String algo = args[0];
        int n = Integer.parseInt(args[1]);
        String dist = args[2];
        int trials = Integer.parseInt(args[3]);
        String out = args[4];

        PerformanceTracker.writeCsvHeader(out);

        for (int trial = 1; trial <= trials; trial++) {
            int[] arr = makeArray(n, dist);
            PerformanceTracker t = new PerformanceTracker();
            switch (algo.toLowerCase()) {
                case "selection":
                    t.start();
                    SelectionSort.sort(Arrays.copyOf(arr, arr.length), t);
                    t.stop();
                    t.appendCsv(out, t.toCsvLine("SelectionSort", "two-way", n, dist, trial));
                    break;
                case "heapsort":
                    t.start();
                    HeapSort.sort(Arrays.copyOf(arr, arr.length), t);
                    t.stop();
                    t.appendCsv(out, t.toCsvLine("HeapSort", "floyd", n, dist, trial));
                    break;
                case "kadane":
                    t.start();
                    Kadane.maxSubarray(Arrays.copyOf(arr, arr.length), t);
                    t.stop();
                    t.appendCsv(out, t.toCsvLine("Kadane", "standard", n, dist, trial));
                    break;
                case "maxheap":
                    t.start();
                    MaxHeap h = new MaxHeap(n, t);
                    for (int v : arr) h.insert(v);
                    while (h.size() > 0) h.extractMax();
                    t.stop();
                    t.appendCsv(out, t.toCsvLine("MaxHeap", "array", n, dist, trial));
                    break;
                default:
                    System.out.println("Unknown algorithm");
            }
        }
        System.out.println("Done. Results: " + out);
    }

    private static int[] makeArray(int n, String dist) {
        Random r = new Random(123);
        int[] a = new int[n];
        switch (dist) {
            case "random":
                for (int i = 0; i < n; i++) a[i] = r.nextInt(1_000_000);
                break;
            case "sorted":
                for (int i = 0; i < n; i++) a[i] = i;
                break;
            case "reverse":
                for (int i = 0; i < n; i++) a[i] = n - i;
                break;
            case "nearly":
                for (int i = 0; i < n; i++) a[i] = i;
                for (int k = 0; k < Math.max(1, n/100); k++) {
                    int i = r.nextInt(n), j = r.nextInt(n);
                    int tmp = a[i]; a[i] = a[j]; a[j] = tmp;
                }
                break;
            default:
                for (int i = 0; i < n; i++) a[i] = r.nextInt(1_000_000);
        }
        return a;
    }
}
