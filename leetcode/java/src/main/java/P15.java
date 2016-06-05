/**
 * Created by taihejin on 16-6-4.
 */

import java.util.*;

public class P15 {

    public List<List<Integer>> threeSum(int[] nums) {
        List<List<Integer>> result = new ArrayList<List<Integer>>();
        if (nums == null || nums.length <= 2) return result;
        Arrays.sort(nums);
        int size = nums.length;
        for (int i = 0; i < size - 2; i++) {
            if (i > 0 && nums[i] == nums[i - 1]) continue;
            int low = i + 1;
            int high = size - 1;
            while (low < high) {
                int sum = nums[i] + nums[low] + nums[high];
                if (sum == 0) {
                    result.add(Arrays.asList(nums[i], nums[low], nums[high]));
                    while (low + 1 < high && nums[low + 1] == nums[low]) low++;
                    while (high - 1 > low && nums[high - 1] == nums[high]) high--;
                    low++;
                    high--;
                } else if (sum < 0) {
                    low++;
                } else {
                    high--;
                }
            }
        }
        return result;
    }

    public static void main(String[] args) {
        int[] nums = new int[]{-1,-12,14,-6,4,-11,2,-7,13,-15,-1,11,-15,-15,13,-9,-11,-10,-7,-13,7,9,-13,-3,10,1,-5,12,11,-9,2,-4,-6,-7,5,5,-2,14,13,-12,14,-3,14,14,-11,5,12,-6,10,-9,-4,-6,-15,-9,-1,2,-1,9,-9,-5,-15,8,-2,-6,9,10,7,14,9,5,-13,9,-12,8,8,-8,-2,-2,1,-15,-4,5,-13,-4,3,1,5,-13,-13,11,-11,10,5,3,11,-9,-2,3,-10,-7,-6,14,9,-11,7,2,-4,13,7,5,13,-12,-13,7,-9,5,-7,11,-1,-12,8,3,13,-10,13,1,-4};
        long start , interval;

        start = System.currentTimeMillis();
        List<List<Integer>> r = new P15().threeSum(nums);
        interval = System.currentTimeMillis() - start;
        System.out.println(r.toString() + " \n" + r.size() +  " \n" + interval);


    }
}
