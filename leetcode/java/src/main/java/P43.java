import org.junit.Assert;
import org.junit.Test;

import java.util.Arrays;

/**
 * Created by taihejin on 16-6-10.
 */
public class P43 {

    private int[] process(String num1) {
        if (num1 == null || num1.length() == 0) {
            return new int[]{0};
        }
        int size = num1.length();
        int[] arr = new int[size];
        for (int i = 0; i < size; i++) {
            arr[size - 1 - i] = num1.charAt(i) - '0';
        }
        return arr;
    }

    public String multiply(String num1, String num2) {
        int[] x = process(num1);
        int[] y = process(num2);
        int[] r = new int[x.length + y.length];
        Arrays.fill(r, 0);
        for (int i = 0; i < x.length; i++) {
            for (int j = 0; j < y.length; j++) {
                r[i + j] += x[i] * y[j];
            }
        }
        int carry = 0;
        for (int i = 0; i < r.length; i++) {
            int val = r[i] + carry;
            r[i] = val % 10;
            carry = val / 10;
        }
        StringBuilder builder = new StringBuilder();
        boolean trim = true;
        for (int i = r.length - 1; i >= 0; i--) {
            if (trim && r[i] == 0) {
                continue;
            }
            builder.append((char)('0' + r[i]));
            trim = false;
        }
        if (builder.length() == 0) {
            builder.append('0');
        }
        return builder.toString();
    }

    @Test
    public void test1() {
        System.out.println(Arrays.toString(process("")));
        System.out.println(Arrays.toString(process("0")));
        System.out.println(Arrays.toString(process("00")));
        System.out.println(Arrays.toString(process("1")));
        System.out.println(Arrays.toString(process("126")));
    }

    @Test
    public void test2() {
        Assert.assertEquals("0", multiply("0", "0"));
        Assert.assertEquals("0", multiply("0", "12"));
        Assert.assertEquals("0", multiply("0", "0013"));
        Assert.assertEquals("0", multiply("0", "0011003"));
        Assert.assertEquals("0", multiply("0000", "0000"));
        Assert.assertEquals("1", multiply("1", "1"));
        Assert.assertEquals("4", multiply("1", "4"));
        Assert.assertEquals("81", multiply("9", "9"));
        Assert.assertEquals("121", multiply("11", "11"));
        Assert.assertEquals("1386", multiply("126", "11"));
        Assert.assertEquals("1386", multiply("11", "126"));
        Assert.assertEquals("1386", multiply("11", "000126"));
        Assert.assertEquals("138600", multiply("11", "0012600"));
    }
}
