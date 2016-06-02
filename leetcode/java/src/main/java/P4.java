/**
 * Created by taihejin on 16-5-30.
 */
public class P4 {

    private int kth(int A[], int startA, int lengthA, int B[], int startB, int lengthB, int k) {
        if (lengthA > lengthB) {
            return kth(B, startB, lengthB, A, startA, lengthA, k);
        }
        if (lengthA == 0) {
            return B[startB + k - 1];
        }
        if (k == 1) {
            return Math.min(A[startA], B[startB]);
        }
        if (k == 2 && lengthA == 1 && lengthB == 1) {
            return Math.max(A[startA], B[startB]);
        }
        int i = Math.min(k / 2, lengthA);
        int j = Math.min(k / 2, lengthB);
        if (A[startA + i - 1] > B[startB + j - 1]) {
            return kth(A, startA, lengthA, B, startB + j, lengthB - j, k - j);
        } else {
            return kth(A, startA + i, lengthA - i, B, startB, lengthB, k - i);
        }
    }

    private static void test1() {
        int x[] = new int[]{1, 2, 3};
        int y[] = new int[]{4, 5, 6};
        for (int i = 1; i <= x.length + y.length; i++) {
            int median = new P4().kth(x, 0, x.length, y, 0, y.length, i);
            System.out.println(String.format("expect=%d, median=%d, %b", i, median, i == median));
        }
    }

    private static void test2() {
        int x[] = new int[]{1, 2, 6};
        int y[] = new int[]{3, 4, 5};
        for (int i = 1; i <= x.length + y.length; i++) {
            int median = new P4().kth(x, 0, x.length, y, 0, y.length, i);
            System.out.println(String.format("expect=%d, median=%d, %b", i, median, i == median));
        }
    }

    private static void test3() {
        int x[] = new int[]{};
        int y[] = new int[]{1};
        for (int i = 1; i <= x.length + y.length; i++) {
            int median = new P4().kth(x, 0, x.length, y, 0, y.length, i);
            System.out.println(String.format("expect=%d, median=%d, %b", i, median, i == median));
        }
    }

    private static void test4() {
        int x[] = new int[]{};
        int y[] = new int[]{2, 3};
        double value = new P4().median(x, y);
        System.out.println(String.format("expect=%f, median=%f, %b", 2.5, value, 2.5 == value));
    }

    private double median(int nums1[], int nums2[]) {
        double value = 0;
        if (((nums1.length + nums2.length) & 0x01) == 1) {
            value = kth(nums1, 0, nums1.length, nums2, 0, nums2.length, (nums1.length + nums2.length + 1) / 2);
        } else {
            value = (kth(nums1, 0, nums1.length, nums2, 0, nums2.length, (nums1.length + nums2.length) / 2)
                    + kth(nums1, 0, nums1.length, nums2, 0, nums2.length, (nums1.length + nums2.length) / 2 + 1)) / 2.0;
        }
        return value;
    }

    public static void main(String[] args) {
        test1();
        test2();
        test3();
        test4();
    }
}
