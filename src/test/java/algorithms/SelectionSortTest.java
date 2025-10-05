package algorithms;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
import metrics.PerformanceTracker;
import java.util.Arrays;

public class SelectionSortTest {
    @Test
    public void testEmpty() {
        int[] a = {};
        SelectionSort.sort(a, new PerformanceTracker());
        assertArrayEquals(new int[]{}, a);
    }
    @Test
    public void testSingle() {
        int[] a = {5};
        SelectionSort.sort(a, new PerformanceTracker());
        assertArrayEquals(new int[]{5}, a);
    }
    @Test
    public void testRandom() {
        int[] a = {3,1,4,1,5,9,2,6};
        int[] expected = Arrays.copyOf(a, a.length);
        Arrays.sort(expected);
        SelectionSort.sort(a, new PerformanceTracker());
        assertArrayEquals(expected, a);
    }
    @Test
    public void testDuplicates() {
        int[] a = {2,2,2,2};
        SelectionSort.sort(a, new PerformanceTracker());
        assertArrayEquals(new int[]{2,2,2,2}, a);
    }
}
