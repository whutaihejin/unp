import org.junit.Assert;
import org.junit.Test;

/**
 * Created by taihejin on 16-6-11.
 */
public class P45 {

    public int jump(int[] nums) {
        int size = nums.length;
        int ret = 0;
        int curr = 0;
        int last = 0;
        for (int i = 0; i < size; i++) {
            if (i > last) {
                last = curr;
                ret++;
            }
            curr = Math.max(curr, i + nums[i]);
        }
       return ret;
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
