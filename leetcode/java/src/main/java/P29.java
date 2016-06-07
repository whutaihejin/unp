import org.junit.Assert;
import org.junit.Test;

/**
 * Created by taihejin on 16-6-7.
 */
public class P29 {

    // If it is overflow, return MAX_INT.
    public int divide(int dividend, int divisor) {
        if (divisor == 0) return Integer.MAX_VALUE;
        long x = Math.abs((long)dividend);
        long y = Math.abs((long)divisor);
        long ret = 0;
        while (x >= y) {
            int counter = 0;
            while (x >= (y << counter)) {
                counter++;
            }
            ret += (1L << (counter - 1));
            x -= (y << (counter - 1));
        }
        if ((dividend > 0 && divisor > 0) || (dividend < 0 && divisor < 0)) {
            return ret > Integer.MAX_VALUE ? Integer.MAX_VALUE : (int)ret;
        } else {
            return ret - 1 > Integer.MAX_VALUE ? Integer.MIN_VALUE : -(int)ret;
        }
    }

    @Test
    public void test1() {
        int value = divide(1, 0);
        System.out.println(value);
        Assert.assertEquals(Integer.MAX_VALUE, value);
    }

    @Test
    public void test2() {
        int value = divide(Integer.MIN_VALUE, -1);
        System.out.println(value);
        Assert.assertEquals(Integer.MAX_VALUE, value);
    }

    @Test
    public void test3() {
        int value = divide(Integer.MIN_VALUE, 1);
        System.out.println(value);
        Assert.assertEquals(Integer.MIN_VALUE, value);
    }

    @Test
    public void test4() {
        int value = divide(Integer.MAX_VALUE, -1);
        System.out.println(value);
        Assert.assertEquals(-Integer.MAX_VALUE, value);
    }

    @Test
    public void test5() {
        int value = divide(4, 2);
        System.out.println(value);
        Assert.assertEquals(2, value);
    }

    @Test
    public void test6() {
        int value = divide(5, 2);
        System.out.println(value);
        Assert.assertEquals(2, value);
    }

    @Test
    public void test7() {
        int value = divide(5, -2);
        System.out.println(value);
        Assert.assertEquals(-2, value);
    }

    @Test
    public void test8() {
        int value = divide(-5, 2);
        System.out.println(value);
        Assert.assertEquals(-2, value);
    }

    @Test
    public void test9() {
        int value = divide(-5, -2);
        System.out.println(value);
        Assert.assertEquals(2, value);
    }

}
