import java.util.Arrays;
import java.util.List;

/**
 * Created by taihejin on 16-6-5.
 */
public class P16 {

    public int threeSumClosest(int[] nums, int target) {
        int value = 0;
        if (nums == null || nums.length <= 2) {
            for (int v : nums) value += nums[v];
            return value;
        }
        Arrays.sort(nums);
        int size = nums.length;
        int closest = 0;
        int minDelta = Integer.MAX_VALUE;
        for (int i = 0; i < size - 2; i++) {
            if (i > 0 && nums[i] == nums[i - 1]) continue;
            int low = i + 1;
            int high = size - 1;
            while (low < high) {
                int sum = nums[i] + nums[low] + nums[high];
                int delta =  sum - target;
                if (delta == 0) return target;
                if (delta < 0) low++;
                else high--;
                if (Math.abs(delta) < minDelta) {
                    minDelta = Math.abs(delta);
                    closest = sum;
                }
            }
        }
        return closest;
    }

    public static void doTest(int[] nums, int target) {
        long start = System.currentTimeMillis();
        int closest = new P16().threeSumClosest(nums, target);
        long interval = System.currentTimeMillis() - start;
        System.out.println(String.format("closet=%d, target=%d, delta=%d, interval=%d", closest, target, Math.abs(closest - target), interval));
    }

    public static void main(String[] args) {
        int[] nums = new int[]{-1,-12,14,-6,4,-11,2,-7,13,-15,-1,11,-15,-15,13,-9,-11,-10,-7,-13,7,9,-13,-3,10,1,-5,12,11,-9,2,-4,-6,-7,5,5,-2,14,13,-12,14,-3,14,14,-11,5,12,-6,10,-9,-4,-6,-15,-9,-1,2,-1,9,-9,-5,-15,8,-2,-6,9,10,7,14,9,5,-13,9,-12,8,8,-8,-2,-2,1,-15,-4,5,-13,-4,3,1,5,-13,-13,11,-11,10,5,3,11,-9,-2,3,-10,-7,-6,14,9,-11,7,2,-4,13,7,5,13,-12,-13,7,-9,5,-7,11,-1,-12,8,3,13,-10,13,1,-4};
        for (int i = 0; i < 100; i++) {
            doTest(nums, i);
        }
    }
}
