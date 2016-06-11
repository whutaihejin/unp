import org.junit.Assert;
import org.junit.Test;

/**
 * Created by taihejin on 16-6-11.
 */
public class P45 {

    public int jump(int[] nums) {
        int size = nums.length;
        int[] f = new int[size];
        f[0] = 0;
        int[] last = new int[size];
        last[0] = 0;
        for (int p = 1; p < size; p++) {
            int min = Integer.MAX_VALUE;
            for (int k = last[p - 1]; k < p; k++) {
                if (p - k <= nums[k] && f[k] + 1 < min) {
                    min = f[k] + 1;
                    last[p] = k;
                }
            }
            f[p] = min;
        }
        return f[size - 1];
    }

    @Test
    public void test1() {
        int[] nums = {2,3,1,1,4};
        Assert.assertEquals(2, jump(nums));
    }

    @Test
    public void test2() {
        int[] nums = new int[25000];
        for (int i = 0; i < nums.length; i++) {
            nums[i] = 1;
        }
        Assert.assertEquals(nums.length - 1, jump(nums));
    }

    @Test
    public void test3() {
        int size = 25000;
        int[] nums = new int[size];
        for (int i = 0; i < size; i++) {
            nums[i] = size - i;
        }
        Assert.assertEquals(1, jump(nums));
    }

}
