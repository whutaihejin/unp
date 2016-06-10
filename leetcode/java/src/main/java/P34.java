import org.junit.Assert;
import org.junit.Test;

/**
 * Created by taihejin on 16-6-9.
 */
public class P34 {

    public int right(int[] nums, int target) {
        int low = 0, high = nums.length - 1;
        while (low + 1 < high) {
            int mid = low + (high - low) / 2;
            if (target >= nums[mid]) {
                low = mid;
            } else {
                high = mid;
            }
        }
        if (nums[high] == target) {
            return high;
        } else if (nums[low] == target) {
            return low;
        } else {
            return -1;
        }
    }

    public int left(int[] nums, int target) {
        int low = 0, high = nums.length - 1;
        while (low + 1 < high) {
            int mid = low + (high - low) / 2;
            if (target <= nums[mid]) {
                high = mid;
            } else {
                low = mid;
            }
        }
        if (nums[low] == target) {
            return low;
        } else if (nums[high] == target) {
            return high;
        } else {
            return -1;
        }
    }

    public int[] searchRange(int[] nums, int target) {
        int[] ret = new int[] {-1, -1};
        if (nums == null || nums.length == 0) {
            return ret;
        }
        int left = left(nums, target);
        if (left != -1) {
            ret[0] = left;
            ret[1] = right(nums, target);
        }
        return ret;
    }

    @Test
    public void test1() {
        int[] nums = {1, 2, 5, 8, 8, 8, 8, 9};
        int right = right(nums, 8);
        Assert.assertEquals(6, right);
    }

    @Test
    public void test2() {
        int[] nums = {1, 2, 5, 8, 8, 8, 8, 9, 9, 9, 9};
        int right = right(nums, 9);
        Assert.assertEquals(nums.length - 1, right);
    }

    @Test
    public void test3() {
        int[] nums = {1, 1, 1, 1, 2, 5, 8, 8, 8, 8, 9, 9, 9, 9};
        int right = right(nums, 1);
        Assert.assertEquals(3, right);
    }

    @Test
    public void test4() {
        int[] nums = {1, 1, 1, 1, 2, 5, 8, 8, 8, 8, 9, 9, 9, 9};
        int left = left(nums, 1);
        Assert.assertEquals(0, left);
    }

    @Test
    public void test5() {
        int[] nums = {1, 1, 1, 1, 2, 5, 8, 8, 8, 8, 9, 9, 9, 9};
        int left = left(nums, 9);
        Assert.assertEquals(nums.length - 4, left);
    }

    @Test
    public void test6() {
        int[] nums = {5, 7, 7, 8, 8, 10};
        int[] ret = searchRange(nums, 8);
        Assert.assertEquals(3, ret[0]);
        Assert.assertEquals(4, ret[1]);
    }

    @Test
    public void test7() {
        int[] nums = {5, 7, 7, 8, 8, 10};
        int[] ret = searchRange(nums, 7);
        Assert.assertEquals(1, ret[0]);
        Assert.assertEquals(2, ret[1]);
    }

    @Test
    public void test8() {
        int[] nums = {5, 7, 7, 8, 8, 10};
        int[] ret = searchRange(nums, 5);
        Assert.assertEquals(0, ret[0]);
        Assert.assertEquals(0, ret[1]);
    }

    @Test
    public void test9() {
        int[] nums = {5, 7, 7, 8, 8, 10};
        int[] ret = searchRange(nums, 10);
        Assert.assertEquals(nums.length - 1, ret[0]);
        Assert.assertEquals(nums.length - 1, ret[1]);
    }

    @Test
    public void test10() {
        int[] nums = {5, 7, 7, 8, 8, 10};
        int[] ret = searchRange(nums, 99);
        Assert.assertEquals(-1, ret[0]);
        Assert.assertEquals(-1, ret[1]);
    }

}
