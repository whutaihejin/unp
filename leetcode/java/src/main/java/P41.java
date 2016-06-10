import org.junit.Assert;
import org.junit.Test;

import java.util.HashSet;
import java.util.Set;

/**
 * Created by taihejin on 16-6-10.
 */
public class P41 {
    /*int answer = 1;
        if (nums == null || nums.length == 0) {
            return answer;
        }
        int size = nums.length;
        for (int i = 0; i < size; i++) {
            int value = nums[i];
            if (value < 0 || value >= size) {
                continue;
            }
            nums[i] = nums[value];
            nums[value] = value;
        }
        answer = -1;
        for (int i = 1; i < size; i++) {
            if (nums[i] != i) {
                answer = i;
                break;
            }
        }
        return answer != -1 ? answer : nums[size - 1] + 1;*/

    public int firstMissingPositive(int[] nums) {
        int answer = 1;
        if (nums == null || nums.length == 0) {
            return answer;
        }
        Set<Integer> set = new HashSet<Integer>();
        int size = nums.length;
        for (int i = 0; i < nums.length; i++) {
            int value = nums[i];
            if (value >= 0 && value <= size) {
                set.add(value);
            }
        }
        for (int i = 1;  i <= size + 1; i++) {
            if (!set.contains(i)) {
                return i;
            }
        }
        return 1;
    }

    @Test
    public void test1() {
        int[] nums = {1, 2, 0};
        Assert.assertEquals(3, firstMissingPositive(nums));
    }

    @Test
    public void test2() {
        int[] nums = {3, 4, -1, 1};
        Assert.assertEquals(2, firstMissingPositive(nums));
    }

    @Test
    public void test3() {
        int[] nums = {2, 3, 1};
        Assert.assertEquals(4, firstMissingPositive(nums));
    }

    @Test
    public void test4() {
        int[] nums = {1, 2};
        Assert.assertEquals(3, firstMissingPositive(nums));
    }

}
