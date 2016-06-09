import org.junit.Assert;
import org.junit.Test;

import java.util.Arrays;

/**
 * Created by taihejin on 16-6-8.
 */
public class P31 {

    public void reverse(int[] nums, int low, int high) {
        while (low < high) {
            int temp = nums[low];
            nums[low] = nums[high];
            nums[high] = temp;
            low++;
            high--;
        }
    }

    public void nextPermutation(int[] nums) {
        if (nums == null || nums.length <= 1) {
            return;
        }
        int size = nums.length;
        for (int i = size - 1; i > 0; i--) {
            if (nums[i - 1] < nums[i]) {
                int index = size - 1;
                while (nums[index] <= nums[i - 1]) {
                    index--;
                }
                int temp = nums[index];
                nums[index] = nums[i - 1];
                nums[i - 1] = temp;
                reverse(nums, i, size - 1);
                return;
            }
        }
        reverse(nums, 0, size - 1);
    }

    @Test
    public void doTest() {
        int[] nums = {1, 2, 3};
        System.out.println(Arrays.toString(nums));
        nextPermutation(nums);
        System.out.println(Arrays.toString(nums));
        Assert.assertEquals(nums[1], 3);
        Assert.assertEquals(nums[2], 2);
    }

    @Test
    public void doTest1() {
        int[] nums = {3, 2, 1};
        System.out.println(Arrays.toString(nums));
        nextPermutation(nums);
        System.out.println(Arrays.toString(nums));
        Assert.assertEquals(nums[1], 2);
        Assert.assertEquals(nums[2], 3);
    }

    @Test
    public void doTest2() {
        int[] nums = {1, 1, 5};
        System.out.println(Arrays.toString(nums));
        nextPermutation(nums);
        System.out.println(Arrays.toString(nums));
        Assert.assertEquals(nums[1], 5);
        Assert.assertEquals(nums[2], 1);
    }

    @Test
    public void doTest3() {
        int[] nums = {6, 7, 5, 4, 3, 2, 1};
        System.out.println(Arrays.toString(nums));
        nextPermutation(nums);
        System.out.println(Arrays.toString(nums));
        Assert.assertEquals(nums[0], 7);
        Assert.assertEquals(nums[1], 1);
    }

    @Test
    public void doTest4() {
        int[] nums = {9, 8, 7, 6, 5, 5, 5, 5, 3, 2, 1};
        System.out.println(Arrays.toString(nums));
        nextPermutation(nums);
        System.out.println(Arrays.toString(nums));
        Assert.assertEquals(nums[0], 1);
        Assert.assertEquals(nums[1], 2);
    }

    @Test
    public void doTest5() {
        int[] nums = {1, 3, 2};
        System.out.println(Arrays.toString(nums));
        nextPermutation(nums);
        System.out.println(Arrays.toString(nums));
    }

    @Test
    public void doTest6() {
        int[] nums = {3, 7, 5, 4, 3, 2, 1};
        System.out.println(Arrays.toString(nums));
        nextPermutation(nums);
        System.out.println(Arrays.toString(nums));
    }

    @Test
    public void doTest7() {
        int[] nums = {5, 6, 9, 8, 7};
        System.out.println(Arrays.toString(nums));
        nextPermutation(nums);
        System.out.println(Arrays.toString(nums));
    }
}
