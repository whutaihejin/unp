import org.junit.Assert;
import org.junit.Test;

/**
 * Created by taihejin on 16-6-11.
 */
public class P50 {

    private double doPow(double x, long n) {
        if (n == 0) {
            return 1;
        }
        if (n == 1) {
            return x;
        }
        double value = doPow(x, n / 2);
        value *= value;
        return (n & 0x01) == 1 ? value * x : value;
    }

    public double myPow(double x, int n) {
        long y = (long) n;
        if (y == 0 || x == 1) {
            return 1;
        }
        if (x == 0) {
            return 0;
        }
        return y < 0 ? 1.0 / doPow(x, -y) : doPow(x, y);
    }

    @Test
    public void test1() {
        Assert.assertEquals(1, myPow(0, 0), 0.000001);
        Assert.assertEquals(0, myPow(0, 1), 0.000001);
        Assert.assertEquals(0, myPow(0, 2), 0.000001);
        Assert.assertEquals(0, myPow(0, 3), 0.000001);
        Assert.assertEquals(1, myPow(1, 3), 0.000001);
        Assert.assertEquals(1, myPow(1, 0), 0.000001);
        Assert.assertEquals(1, myPow(1, -1), 0.000001);
        Assert.assertEquals(1, myPow(1, -3), 0.000001);
        Assert.assertEquals(1, myPow(1, 5), 0.000001);
        Assert.assertEquals(1, myPow(1, 5), 0.000001);
        Assert.assertEquals(1, myPow(1, 0), 0.000001);
        Assert.assertEquals(1, myPow(-1, 0), 0.000001);
        Assert.assertEquals(8, myPow(2, 3), 0.000001);
        Assert.assertEquals(0.125, myPow(2, -3), 0.000001);
    }
}
