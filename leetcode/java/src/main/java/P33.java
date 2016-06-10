import org.junit.Assert;
import org.junit.Test;

/**
 * Created by taihejin on 16-6-9.
 */
public class P33 {

    public int search(int[] nums, int target) {
        int index = -1;
        if (nums == null || nums.length == 0) {
            return index;
        }
        int low = 0, high = nums.length - 1;
        while (low <= high) {
            int mid = low + (high - low) / 2;
            if (nums[mid] == target) {
                return mid;
            } else {
                if (nums[low] <= nums[mid]) {
                    if (target > nums[mid] || target < nums[low]) {
                        low = mid + 1;
                    } else {
                        high = mid - 1;
                    }
                } else if (nums[mid] <= nums[high]) {
                    if (target < nums[mid] || target > nums[high]) {
                        high = mid - 1;
                    } else {
                        low = mid + 1;
                    }
                } else {
                    if (target > nums[mid]) {
                        high = mid - 1;
                    } else {
                        low = mid + 1;
                    }
                }
            }
        }
        return low > high ? -1 : low;
    }

    @Test
    public void test1() {
        int[] nums = {1};
        Assert.assertEquals(0, search(nums, 1));
        Assert.assertEquals(-1, search(nums, 22));
    }

    @Test
    public void test2() {
        int[] nums = {1, 2};
        Assert.assertEquals(0, search(nums, 1));
        Assert.assertEquals(1, search(nums, 2));
        Assert.assertEquals(-1, search(nums, 22));
    }

    @Test
    public void test3() {
        int[] nums = {2, 1};
        Assert.assertEquals(1, search(nums, 1));
        Assert.assertEquals(0, search(nums, 2));
        Assert.assertEquals(-1, search(nums, 22));
    }

    @Test
    public void test4() {
        int[] nums = {1, 2, 3, 4, 5, 6, 7, 8};
        Assert.assertEquals(0, search(nums, 1));
        Assert.assertEquals(-1, search(nums, 9));
        Assert.assertEquals(7, search(nums, 8));
        Assert.assertEquals(5, search(nums, 6));
    }

    @Test
    public void test5() {
        int[] nums = {4, 5, 6, 7, 8, 1, 2, 3};
        Assert.assertEquals(0, search(nums, 4));
        Assert.assertEquals(1, search(nums, 5));
        Assert.assertEquals(2, search(nums, 6));
        Assert.assertEquals(3, search(nums, 7));
        Assert.assertEquals(4, search(nums, 8));
        Assert.assertEquals(5, search(nums, 1));
        Assert.assertEquals(6, search(nums, 2));
        Assert.assertEquals(7, search(nums, 3));
        Assert.assertEquals(-1, search(nums, 99));
    }

    @Test
    public void test6() {
        int[] nums = {8, 7, 6, 5, 4, 3, 2, 1};
        Assert.assertEquals(0, search(nums, 8));
        Assert.assertEquals(1, search(nums, 7));
        Assert.assertEquals(2, search(nums, 6));
        Assert.assertEquals(3, search(nums, 5));
        Assert.assertEquals(4, search(nums, 4));
        Assert.assertEquals(5, search(nums, 3));
        Assert.assertEquals(6, search(nums, 2));
        Assert.assertEquals(7, search(nums, 1));
        Assert.assertEquals(-1, search(nums, 99));
    }
}
