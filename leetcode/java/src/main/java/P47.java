/**
 * Created by taihejin on 16-6-11.
 */
import org.junit.Assert;
import org.junit.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class P47 {

    private void swap(int[] nums, int low, int high) {
        int temp = nums[low];
        nums[low] = nums[high];
        nums[high] = temp;
    }

    private void reverse(int[] nums, int low, int high) {
        while (low < high) {
            swap(nums, low, high);
            low++;
            high--;
        }
    }

    private List<Integer> asList(int[] nums) {
        List<Integer> ret = new ArrayList<Integer>();
        for (int value : nums) {
            ret.add(value);
        }
        return ret;
    }

    private boolean nextPermute(int[] nums) {
        boolean valid = false;
        int low, high;
        for (low = nums.length - 1; low > 0; low--) {
            if (nums[low - 1] < nums[low]) {
                valid = true;
                break;
            }
        }
        if (valid) {
            for (high = nums.length - 1; high > 0; high--) {
                if (nums[high] > nums[low - 1]) {
                    swap(nums, low - 1, high);
                    reverse(nums, low, nums.length - 1);
                    return true;
                }
            }
        }
        return false;
    }

    public List<List<Integer>> permuteUnique(int[] nums) {
        List<List<Integer>> list = new ArrayList<List<Integer>>();
        Arrays.sort(nums);
        list.add(asList(nums));
        while (nextPermute(nums)) {
            list.add(asList(nums));
        }
        return list;
    }

    @Test
    public void doTest1() {
        int[] nums = {2, 1, 3};
        nextPermute(nums);
        Assert.assertEquals(3, nums[1]);
        Assert.assertEquals(1, nums[2]);
    }

    @Test
    public void doTest2() {
        int[] nums = {2, 3, 1};
        while (nextPermute(nums)) {
            System.out.println(Arrays.toString(nums));
        }
    }

    @Test
    public void test1() {
        int[] nums = {3, 2, 1};
        List<List<Integer>> list = permuteUnique(nums);
        System.out.println(Arrays.deepToString(list.toArray()));
        Assert.assertEquals(6, list.size());
    }

    @Test
    public void test2() {
        int[] nums = {3};
        List<List<Integer>> list = permuteUnique(nums);
        System.out.println(Arrays.deepToString(list.toArray()));
        Assert.assertEquals(1, list.size());
    }

    @Test
    public void test3() {
        int[] nums = {1, 2};
        List<List<Integer>> list = permuteUnique(nums);
        System.out.println(Arrays.deepToString(list.toArray()));
        Assert.assertEquals(2, list.size());
    }

    @Test
    public void test4() {
        int[] nums = {1, 2, 3, 4};
        List<List<Integer>> list = permuteUnique(nums);
        System.out.println(Arrays.deepToString(list.toArray()));
        Assert.assertEquals(24, list.size());
    }

    @Test
    public void test5() {
        int[] nums = {1, 1};
        List<List<Integer>> list = permuteUnique(nums);
        System.out.println(Arrays.deepToString(list.toArray()));
        Assert.assertEquals(1, list.size());
    }

    @Test
    public void test6() {
        int[] nums = {1, 1, 2};
        List<List<Integer>> list = permuteUnique(nums);
        System.out.println(Arrays.deepToString(list.toArray()));
        Assert.assertEquals(3, list.size());
    }
}
