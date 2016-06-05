/**
 * Created by taihejin on 16-6-4.
 */

import java.util.*;

public class P15 {

    public List<List<Integer>> threeSum(int[] nums) {
        List<List<Integer>> result = new ArrayList<List<Integer>>();
        if (nums.length <= 2) return result;
        Arrays.sort(nums);
        System.out.println(Arrays.toString(nums));
        int size = nums.length;
        for (int k = 0; k < size; k++) {
            int goal = -nums[k];
            while (k + 1 < size && nums[k] == nums[k + 1]) {
                k++;
            }
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
                    result.add(list);
                    while (i < size && nums[i] == nums[i + 1]) i++;
                    while (j > i && nums[j] == nums[j - 1]) j--;
                    i++;
                    j--;
                } else {
                    j--;
                }
            }
        }
        return result;
    }
public List<List<Integer>> threeSum1(int[] num) {
    List<List<Integer>> res = new ArrayList<List<Integer>>();
    if(num==null || num.length<=2)
        return res;
    Arrays.sort(num);
    System.out.println(Arrays.toString(num));
    for(int i=num.length-1;i>=2;i--)
    {
        if(i<num.length-1 && num[i]==num[i+1])
            continue;
        ArrayList<ArrayList<Integer>> curRes = twoSum(num,i-1,-num[i]);
        for(int j=0;j<curRes.size();j++)
        {
            curRes.get(j).add(num[i]);
        }
        res.addAll(curRes);
    }
    return res;
}
    private ArrayList<ArrayList<Integer>> twoSum(int[] num, int end,int target)
    {
        ArrayList<ArrayList<Integer>> res = new ArrayList<ArrayList<Integer>>();
        if(num==null || num.length<=1)
            return res;
        int l = 0;
        int r = end;
        while(l<r)
        {
            if(num[l]+num[r]==target)
            {
                ArrayList<Integer> item = new ArrayList<Integer>();
                item.add(num[l]);
                item.add(num[r]);
                res.add(item);
                l++;
                r--;
                while(l<r&&num[l]==num[l-1])
                    l++;
                while(l<r&&num[r]==num[r+1])
                    r--;
            }
            else if(num[l]+num[r]>target)
            {
                r--;
            }
            else
            {
                l++;
            }
        }
        return res;
    }

    public static void main(String[] args) {
        //int[] nums = new int[]{-1, 0, 1, 2, -1, -4};
        long s = System.currentTimeMillis();
        int[] nums = new int[]{-1,-12,14,-6,4,-11,2,-7,13,-15,-1,11,-15,-15,13,-9,-11,-10,-7,-13,7,9,-13,-3,10,1,-5,12,11,-9,2,-4,-6,-7,5,5,-2,14,13,-12,14,-3,14,14,-11,5,12,-6,10,-9,-4,-6,-15,-9,-1,2,-1,9,-9,-5,-15,8,-2,-6,9,10,7,14,9,5,-13,9,-12,8,8,-8,-2,-2,1,-15,-4,5,-13,-4,3,1,5,-13,-13,11,-11,10,5,3,11,-9,-2,3,-10,-7,-6,14,9,-11,7,2,-4,13,7,5,13,-12,-13,7,-9,5,-7,11,-1,-12,8,3,13,-10,13,1,-4};
        List<List<Integer>> r = new P15().threeSum(nums);
        System.out.println(r.toString() +  " \n" + (System.currentTimeMillis() - s));
        s = System.currentTimeMillis();
        List<List<Integer>> rr = new P15().threeSum1(nums);
        System.out.println(rr.toString() +  " \n" + (System.currentTimeMillis() - s));
    }
}
