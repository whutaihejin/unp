import org.junit.Assert;
import org.junit.Test;

/**
 * Created by taihejin on 16-6-9.
 */
public class P38 {

    public String countAndSay(int n) {
        String seq = "1";
        StringBuilder builder = new StringBuilder();
        for (int i = 1; i < n; i++) {
            int size = seq.length();
            for (int k = 0; k < size; k++) {
                char ch = seq.charAt(k);
                int count = 1;
                while (k + 1 < size && seq.charAt(k + 1) == ch) {
                    count++;
                    k++;
                }
                builder.append(String.valueOf(count) + ch);
            }
            seq = builder.toString();
            builder.delete(0, builder.length());
        }
        return seq;
    }

    @Test
    public void test1() {
        String ret = countAndSay(1);
        Assert.assertEquals("1", ret);
    }

    @Test
    public void test2() {
        String ret = countAndSay(2);
        Assert.assertEquals("11", ret);
    }

    @Test
    public void test3() {
        String ret = countAndSay(3);
        Assert.assertEquals("21", ret);
    }

    @Test
    public void test4() {
        String ret = countAndSay(4);
        Assert.assertEquals("1211", ret);
    }

    @Test
    public void test5() {
        String ret = countAndSay(5);
        Assert.assertEquals("111221", ret);
    }

    @Test
    public void test6() {
        String ret = countAndSay(6);
        Assert.assertEquals("312211", ret);
    }
 }
