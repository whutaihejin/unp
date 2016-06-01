/**
 * Created by taihejin on 16-5-30.
 */
public class P4 {

    private static int kth(int a[], int s1, int len1, int b[], int s2, int len2, int k) {
        if (len1 > len2) return kth(b, s2, len2, a, s1, len1, k);
        //
        if (len1 == 0) return b[s2 + k - 1];
        if (k == 1) return Math.min(a[s1], b[s2]);
        if (k == 2 && len1 == 1 && len2 == 1) return Math.max(a[s1], b[s2]);
        int i = Math.min(k / 2, len1);
        int j = Math.min(k / 2, len2);
        if (a[s1 + i - 1] > b[s2 + j - 1]) return kth(a, s1, len1, b, s2 + j, len2 - j, k - j);
        else return kth(a, s1 + i, len1 - i, b, s2, len2, k - i);
    }

    private static void test1() {
        int x[] = new int[]{1, 2, 3};
        int y[] = new int[]{4, 5, 6};
        for (int i = 1; i <= x.length + y.length; i++) {
            int median = kth(x, 0, x.length, y, 0, y.length, i);
            System.out.println(String.format("expect=%d, median=%d, %b", i, median, i == median));
        }
    }

    private static void test2() {
        int x[] = new int[]{1, 2, 6};
        int y[] = new int[]{3, 4, 5};
        for (int i = 1; i <= x.length + y.length; i++) {
            int median = kth(x, 0, x.length, y, 0, y.length, i);
            System.out.println(String.format("expect=%d, median=%d, %b", i, median, i == median));
        }
    }

    public static void main(String[] args) {
        test1();
        test2();
    }
}
