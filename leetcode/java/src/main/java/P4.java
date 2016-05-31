/**
 * Created by taihejin on 16-5-30.
 */
public class P4 {

    public static int find(int x[], int low, int high, int y[], int n) {
        if (low > high) return -1;
        int k =  (low + high) / 2;
        if (k == 0 && x[k] >= y[n - 1]) {
            return k;
        } else if (k == n - 1 && x[k] <= y[0]) {
            return k;
        } else if (k < n - 1 && x[k] >= y[n - k - 1] && x[k] <= y[n - k]) {
            return k;
        } else if (x[k] > y[n - k]) {
            return find(x, low, k - 1, y, n);
        } else {
            return find(x, k + 1, high, y, n);
        }
    }

    public static double median(int nums1[], int nums2[]) {
        int length = nums1.length;
        double median = 0;
        int index = find(nums1, 0 , length - 1, nums2, length);
        if (index == -1) {
            index = find(nums2, 0, length - 1, nums1, length);
            median = nums2[index];
        } else {
            median = nums1[index];
        }
        System.out.println(String.format("index=%d, median=%f", index, median));
        return median;
//        int length = nums1.length + nums2.length;
//        int z[] = new int[length];
//        int i = 0, j = 0, k = 0;
//        for (; i < nums1.length && j < nums2.length;) {
//            if (nums1[i] < nums2[j]) {
//                z[k++] = nums1[i++];
//            } else {
//                z[k++] = nums2[j++];
//            }
//        }
//        for (; i < nums1.length;) {
//            z[k++] = nums1[i++];
//        }
//        for (; j < nums2.length;) {
//            z[k++] = nums2[j++];
//        }
//        if ((length & 0x01) == 0) {
//            int index = length / 2;
//            return (z[index] + z[index - 1]) / 2.0;
//        } else {
//            return z[length / 2];
//        }
    }

    public static void main(String[] args) {
        int x[] = new int[]{1, 2};
        int y[] = new int[]{3, 4};
        median(x, y);
        median(y, x);
    }
}
