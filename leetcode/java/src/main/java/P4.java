/**
 * Created by taihejin on 16-5-30.
 */
public class P4 {

    private static int kth(int a[], int s1, int len1, int b[], int s2, int len2, int k) {
        if (len1 > len2) return kth(b, s2, len2, a, s1, len1, k);
        if (len1 == 0) return b[k - 1];
        if (k == 1) return Math.min(a[0], b[0]);
        if (k == 2 && len1 == 1 && len2 == 1) return Math.max(a[0], b[0]);
        int i = Math.min(k / 2, len1);
        int j = Math.min(k / 2, len2);
        if (a[i - 1] > b[j - 1]) return kth(a, s1, len1 - i, b, s2 + j, len2 - j, k - j);
        else return kth(a, s1 + i, len1 - i, b, s2, len2 - j, k - i);
    }

    public static void main(String[] args) {
        int x[] = new int[]{1,3};
        int y[] = new int[]{2};
        for (int i = 1; i <= x.length + y.length; i++) {
            int value = kth(x, 0, x.length, y, 0, y.length, i);
            System.out.println(String.format("value=%d", value));
        }
    }
}
