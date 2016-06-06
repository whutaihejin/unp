import org.junit.Assert;
import org.junit.Test;

import java.util.Arrays;

/**
 * Created by taihejin on 16-6-6.
 */
public class Heap {

    public void maxHeapify1(int[] heap, int i, int size) {
        while (i < size) {
            int largest = i;
            int left = (i << 1) + 1;
            int right = left + 1;
            if (left < size && heap[left] > heap[largest]) {
                largest = left;
            }
            if (right < size && heap[right] > heap[largest]) {
                largest = right;
            }
            if (largest != i) {
                int temp = heap[i];
                heap[i] = heap[largest];
                heap[largest] = temp;
                i = largest;
            } else {
                break;
            }
        }
    }

    public void maxHeapify2(int[] heap, int i, int size) {
        int largest = i;
        int left = (i << 1) + 1;
        int right = left + 1;
        if (left < size && heap[left] > heap[largest]) {
            largest = left;
        }
        if (right < size && heap[right] > heap[largest]) {
            largest = right;
        }
        if (largest != i) {
            int temp = heap[i];
            heap[i] = heap[largest];
            heap[largest] = temp;
            maxHeapify2(heap, largest, size);
        }
    }

    public void buildMaxHeap(int heap[]) {
        int size = heap.length;
        for (int i = size / 2; i >= 0; i--) {
            maxHeapify1(heap, i, size);
        }
    }

    public void heapSort(int heap[]) {
        buildMaxHeap(heap);
        int size = heap.length;
        for (int i = size - 1; i > 0; i--) {
            int temp = heap[0];
            heap[0] = heap[i];
            heap[i] = temp;
            maxHeapify1(heap, 0, i);
        }
    }

    @Test
    public void testHeapify() {
        int[] heap = {16, 4, 10, 14, 7, 9, 3, 2, 8, 1};
        maxHeapify1(heap, 1, heap.length);
        Assert.assertEquals(heap[1], 14);
        Assert.assertEquals(heap[8], 4);
    }

    @Test
    public void testHeapify1() {
        int[] heap = {};
        maxHeapify1(heap, 0, heap.length);
    }

    @Test
    public void testHeapify2() {
        int[] heap = {1};
        maxHeapify1(heap, 0, heap.length);
        Assert.assertEquals(heap[0], 1);
    }

    @Test
    public void testHeapify3() {
        int[] heap = {1, 2};
        maxHeapify1(heap, 0, heap.length);
        Assert.assertEquals(heap[0], 2);
        Assert.assertEquals(heap[1], 1);
    }

    @Test
    public void testHeapify4() {
        int[] heap = {1, 2, 3};
        maxHeapify1(heap, 0, heap.length);
        Assert.assertEquals(heap[0], 3);
    }

    @Test
    public void testBuildMaxHeap() {
        int[] heap = {1, 2, 3};
        buildMaxHeap(heap);
        Assert.assertEquals(heap[0], 3);
    }

    @Test
    public void testBuildMaxHeap1() {
        int[] heap = {1, 2, 3, 4};
        buildMaxHeap(heap);
        Assert.assertEquals(heap[0], 4);
    }

    @Test
    public void heapSortTest() {
        int[] arr = {4, 3, 2, 1};
        heapSort(arr);
        Assert.assertEquals(arr[0], 1);
        Assert.assertEquals(arr[1], 2);
        Assert.assertEquals(arr[2], 3);
        Assert.assertEquals(arr[3], 4);
        System.out.println(Arrays.toString(arr));
    }

}
