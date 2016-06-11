/**
 * Created by taihejin on 16-6-10.
 */
import org.junit.Assert;
import org.junit.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
public class P46 {

    private void swap(int[] nums, int low, int high) {
        int temp = nums[low];
        nums[low] = nums[high];
        nums[high] = temp;
    }

    private List<Integer> asList(int[] nums) {
        List<Integer> ret = new ArrayList<Integer>();
        for (int value : nums) {
            ret.add(value);
        }
        return ret;
    }

    private void doPermute(int[] nums, int start, List<List<Integer>> result) {
        if (start == nums.length) {
            result.add(asList(nums));
            return;
        }
        for (int i = start; i < nums.length; i++) {
            swap(nums, start, i);
            doPermute(nums, start + 1, result);
            swap(nums, start, i);
        }
    }

    public List<List<Integer>> permute(int[] nums) {
        List<List<Integer>> result = new ArrayList<List<Integer>>();
        doPermute(nums, 0, result);
        return  result;
    }

    @Test
    public void test1() {
        int[] nums = {1, 2, 3};
        List<List<Integer>> result = permute(nums);
        System.out.println(Arrays.deepToString(result.toArray()));
        Assert.assertEquals(6, result.size());
    }

    @Test
    public void test2() {
        int[] nums = {1};
        List<List<Integer>> result = permute(nums);
        System.out.println(Arrays.deepToString(result.toArray()));
        Assert.assertEquals(1, result.size());
    }

    @Test
    public void test3() {
        int[] nums = {1, 2};
        List<List<Integer>> result = permute(nums);
        System.out.println(Arrays.deepToString(result.toArray()));
        Assert.assertEquals(2, result.size());
    }

    @Test
    public void test4() {
        int[] nums = {1, 2, 3, 4};
        List<List<Integer>> result = permute(nums);
        System.out.println(Arrays.deepToString(result.toArray()));
        Assert.assertEquals(24, result.size());
    }

       /*
       [[1, 2, 3, 4],
       [1, 2, 4, 3],
       [1, 3, 2, 4],
       [1, 3, 4, 2],
       [1, 4, 3, 2],
       [1, 4, 2, 3],
       [2, 1, 3, 4],
       [2, 1, 4, 3],
       [2, 3, 1, 4],
       [2, 3, 4, 1],
       [2, 4, 3, 1],
       [2, 4, 1, 3]
       [3, 2, 1, 4],
       [3, 2, 4, 1],
       [3, 1, 2, 4],
       [3, 1, 4, 2],
       [3, 4, 1, 2],
       [3, 4, 2, 1],
       [4, 2, 3, 1],
       [4, 2, 1, 3],
       [4, 3, 2, 1],
       [4, 3, 1, 2],
       [4, 1, 3, 2],
       [4, 1, 2, 3]]*/


}
