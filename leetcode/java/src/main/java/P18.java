import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Created by taihejin on 16-6-5.
 */
public class P18 {

    public List<List<Integer>> fourSum(int[] nums, int target) {
        List<List<Integer>> result = new ArrayList<List<Integer>>();
        if (nums == null || nums.length < 4) return result;
        Arrays.sort(nums);
        int size = nums.length;
        for (int i = 0; i <= size - 4; i++) {
            if (i > 0 && nums[i - 1] == nums[i]) continue;
            for (int j = i + 1; j <= size - 3; j++) {
                if (j > i + 1 && nums[j - 1] == nums[j]) continue;
                int low = j + 1;
                int high = size - 1;
                while (low < high) {
                    int sum = nums[i] + nums[j] + nums[low] + nums[high];
                    if (sum == target) {
                        result.add(Arrays.asList(nums[i], nums[j], nums[low], nums[high]));
                        while (low < high && nums[low] == nums[low + 1]) low++;
                        while (low < high && nums[high] == nums[high - 1]) high--;
                        low++;
                        high--;
                    } else if (sum < target) {
                        low++;
                    } else {
                        high--;
                    }
                }
            }
        }
        return result;
    }

    public static void doTest(int[] nums, int target) {
        long start = System.currentTimeMillis();
        List<List<Integer>> result = new P18().fourSum(nums, target);
        long interval = System.currentTimeMillis() - start;
        System.out.println(String.format("target=%d, length=%d, interval=%d\nresult=%s", target, result.size(), interval, result.toString()));
    }

    public static void main(String[] args) {
        //int[] nums = new int[]{-1,-12,14,-6,4,-11,2,-7,13,-15,-1,11,-15,-15,13,-9,-11,-10,-7,-13,7,9,-13,-3,10,1,-5,12,11,-9,2,-4,-6,-7,5,5,-2,14,13,-12,14,-3,14,14,-11,5,12,-6,10,-9,-4,-6,-15,-9,-1,2,-1,9,-9,-5,-15,8,-2,-6,9,10,7,14,9,5,-13,9,-12,8,8,-8,-2,-2,1,-15,-4,5,-13,-4,3,1,5,-13,-13,11,-11,10,5,3,11,-9,-2,3,-10,-7,-6,14,9,-11,7,2,-4,13,7,5,13,-12,-13,7,-9,5,-7,11,-1,-12,8,3,13,-10,13,1,-4};
        int[] nums = {1, 0, -1, 0, -2, 2};
        for (int i = 0; i < 10; i++) {
            doTest(nums, i);
        }
    }
}
