/**
 * Created by taihejin on 16-5-30.
 */
public class P4 {

    private static int kth(int a[], int lx, int hx, int b[], int ly, int hy, int k) {
        if (hx - lx + 1 > hy - ly + 1) return kth(b, ly, hy, a, lx, hx, k);
        if (hx - lx + 1 == 0) return b[k - 1];
        if (k == 1) return Math.min(a[0], b[0]);
        if (k == 2 && hx - lx == 0 && hy - ly == 0) return Math.max(a[0], b[0]);
        int i = Math.min(k / 2, hx - lx + 1);
        int j = Math.min(k / 2, hy - ly + 1);
        if (a[i - 1] > b[j - 1]) {
            return kth(a, lx, i - 1, b, j + 1, hy, k - j);
        } else {
            return kth(a, i + 1, hx, b, ly, j - 1, k - i);
        }
    }

    public static void main(String[] args) {
        int x[] = new int[]{1,3};
        int y[] = new int[]{2};
        for (int i = 1; i <= x.length + y.length; i++) {
            int value = kth(x, 1, x.length, y, 1, y.length, i);
            System.out.println(String.format("value=%d", value));
        }
    }
}
