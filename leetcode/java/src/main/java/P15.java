/**
 * Created by taihejin on 16-6-4.
 */

import java.util.*;

public class P15 {

    public List<List<Integer>> threeSum(int[] nums) {
        Arrays.sort(nums);
        List<List<Integer>> result = new ArrayList<List<Integer>>();
        Set<Integer> goalSet = new HashSet<Integer>();
        int size = nums.length;
        for (int k = 0; k < size; k++) {
            int goal = -nums[k];
            if (goalSet.contains(goal)) continue;
            else goalSet.add(goal);
            for (int i = 0, j = size - 1; i < j;) {
                if (i == k) {
                    i++;
                    continue;
                }
                if (j == k) {
                    j--;
                    continue;
                }
                int sum = nums[i] + nums[j];
                if (sum < goal) {
                    i++;
                } else if (sum == goal){
                    List<Integer> list = new ArrayList<Integer>();
                    list.add(nums[k]);
                    list.add(nums[i]);
                    list.add(nums[j]);
                    Collections.sort(list);
                    boolean repeated = false;
                    for (List<Integer> e : result) {
                        if (e.equals(list)) {
                            repeated = true;
                            break;
                        }
                    }
                    if (!repeated) {
                        result.add(list);
                    }
                    i++;
                    j--;
                } else {
                    j--;
                }
            }
        }
        return result;
    }

    public static void main(String[] args) {
        //int[] nums = new int[]{-1, 0, 1, 2, -1, -4};
        int[] nums = new int[]{-1,-12,14,-6,4,-11,2,-7,13,-15,-1,11,-15,-15,13,-9,-11,-10,-7,-13,7,9,-13,-3,10,1,-5,12,11,-9,2,-4,-6,-7,5,5,-2,14,13,-12,14,-3,14,14,-11,5,12,-6,10,-9,-4,-6,-15,-9,-1,2,-1,9,-9,-5,-15,8,-2,-6,9,10,7,14,9,5,-13,9,-12,8,8,-8,-2,-2,1,-15,-4,5,-13,-4,3,1,5,-13,-13,11,-11,10,5,3,11,-9,-2,3,-10,-7,-6,14,9,-11,7,2,-4,13,7,5,13,-12,-13,7,-9,5,-7,11,-1,-12,8,3,13,-10,13,1,-4};
        List<List<Integer>> r = new P15().threeSum(nums);
        System.out.println(r.toString());
    }
}
