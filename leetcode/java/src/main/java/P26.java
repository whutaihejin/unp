import org.junit.Assert;
import org.junit.Test;

import java.util.Arrays;

/**
 * Created by taihejin on 16-6-6.
 */
public class P26 {

    public int removeElement(int[] nums, int val) {
        if (nums == null || nums.length == 0) return 0;
        Arrays.sort(nums);
        int size = nums.length;
        for (int i = 0; i < size; i++) {
            if (nums[i] != val) {
                continue;
            }
            int k = i;
            for (; k < size - 1 && nums[k] == nums[k + 1]; k++);
            int counter = 0;
            for (int p = k + 1; p < size; p++) {
                nums[i + counter++] = nums[p];
            }
            size -= (k - i + 1);
            break;
        }
        System.out.println(Arrays.toString(nums));
        return size;
    }

    @Test
    public void removeTest() {
        int[] nums = {1, 2, 3, 3, 4, 4, 4, 5};
        System.out.println(removeElement(nums, 3));
    }

    @Test
    public void removeTest1() {
        int[] nums = {1, 2, 3, 3, 4, 4, 4, 5};
        System.out.println(removeElement(nums, 4));
    }

    @Test
    public void removeTest2() {
        int[] nums = {1, 2, 3, 3, 4, 4, 4, 5};
        System.out.println(removeElement(nums, 5));
    }

    @Test
    public void removeTest3() {
        int[] nums = {1, 2, 3, 3, 4, 4, 4, 5};
        System.out.println(removeElement(nums, 1));
    }

    public int removeDuplicates(int[] nums) {
        if (nums == null || nums.length == 0) {
            return 0;
        }
        int size = nums.length;
        for (int i = 0; i < size; i++) {
            int k = i;
            for (; k < size - 1 && nums[k] == nums[k + 1]; k++);
            int counter = 0;
            for (int p = k; p < size; p++) {
                nums[i + counter++] = nums[p];
            }
            size -= k - i;
        }
        return size;
    }

    @Test
    public void doTest() {
        int[] nums = {1, 1, 1, 1};
        int len = removeDuplicates(nums);
        System.out.println(String.format("len=%d, array=%s", len, Arrays.toString(nums)));
        Assert.assertEquals(len, 1);
    }

    @Test
    public void doTest1() {
        int[] nums = {1, 2, 3};
        int len = removeDuplicates(nums);
        System.out.println(String.format("len=%d, array=%s", len, Arrays.toString(nums)));
        Assert.assertEquals(3, len);
    }

    @Test
    public void doTest2() {
        int[] nums = {1, 2, 2, 3};
        int len = removeDuplicates(nums);
        System.out.println(String.format("len=%d, array=%s", len, Arrays.toString(nums)));
        Assert.assertEquals(3, len);
    }

    @Test
    public void doTest3() {
        int[] nums = {1, 2, 2, 2, 3};
        int len = removeDuplicates(nums);
        System.out.println(String.format("len=%d, array=%s", len, Arrays.toString(nums)));
        Assert.assertEquals(3, len);
    }

    @Test
    public void doTest4() {
        int[] nums = {1, 2, 2, 2, 3, 3, 3, 3, 3};
        int len = removeDuplicates(nums);
        System.out.println(String.format("len=%d, array=%s", len, Arrays.toString(nums)));
        Assert.assertEquals(3, len);
    }

    @Test
    public void doTest5() {
        int[] nums = {1, 1, 1, 1, 2, 2, 2, 3, 3, 3, 3, 3};
        int len = removeDuplicates(nums);
        System.out.println(String.format("len=%d, array=%s", len, Arrays.toString(nums)));
        Assert.assertEquals(3, len);
    }
}
