import org.junit.Assert;
import org.junit.Test;

/**
 * Created by taihejin on 16-6-9.
 */
public class P35 {

    public int searchInsert(int[] nums, int target) {
        int low = 0, high = nums.length - 1;
        while (low + 1 < high) {
            int mid = low + (high - low) / 2;
            if (target <= nums[mid]) {
                high = mid;
            } else {
                low = mid;
            }
        }
        int index;
        if (target <= nums[low]) {
            index = low;
        } else if (target <= nums[high]) {
            index = high;
        } else {
            index =  high + 1;
        }
        return index;
    }

    @Test
    public void test1() {
        int[] nums = {1,3,5,6};
        Assert.assertEquals(2, searchInsert(nums, 5));
    }

    @Test
    public void test2() {
        int[] nums = {1,3,5,6};
        Assert.assertEquals(1, searchInsert(nums, 2));
    }

    @Test
    public void test3() {
        int[] nums = {1,3,5,6};
        Assert.assertEquals(1, searchInsert(nums, 2));
    }

    @Test
    public void test4() {
        int[] nums = {1,3,5,6};
        Assert.assertEquals(4, searchInsert(nums, 7));
    }

    @Test
    public void test5() {
        int[] nums = {1,3,5,6};
        Assert.assertEquals(0, searchInsert(nums, 0));
    }

    @Test
    public void test6() {
        int[] nums = {1,3,5,7};
        Assert.assertEquals(3, searchInsert(nums, 6));
    }
}
