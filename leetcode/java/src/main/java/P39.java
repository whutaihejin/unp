/**
 * Created by taihejin on 16-6-10.
 */
import org.junit.Assert;
import org.junit.Test;

import java.util.*;

public class P39 {

    private void dfs(int[] candidates, int start, LinkedList<Integer> stack, List<List<Integer>> result, int target) {
        if (target == 0) {
            List<Integer> right = new ArrayList<Integer>(stack);
            Collections.reverse(right);
            result.add(right);
        }
        if (target < candidates[start] || target <= 0) {
            return;
        }
        for (int i = start; i < candidates.length; i++) {
            stack.push(candidates[i]);
            dfs(candidates, i, stack, result, target - candidates[i]);
            stack.pop();
        }
    }

    public List<List<Integer>> combinationSum(int[] candidates, int target) {
        List<List<Integer>> result = new ArrayList<List<Integer>>();
        if (candidates == null || candidates.length == 0) {
            return result;
        }
        Arrays.sort(candidates);
        LinkedList<Integer> stack = new LinkedList<Integer>();
        dfs(candidates, 0, stack, result, target);
        return result;
    }

    @Test
    public void test1() {
        int[] candidates = {2, 3, 6, 7};
        List<List<Integer>> result = combinationSum(candidates, 7);
        Assert.assertEquals(2, result.size());
        System.out.println(Arrays.toString(result.toArray()));
    }

    @Test
    public void test2() {
        int[] candidates = {2, 3, 6, 7};
        List<List<Integer>> result = combinationSum(candidates, 6);
        Assert.assertEquals(3, result.size());
        System.out.println(Arrays.toString(result.toArray()));
    }

    @Test
    public void test3() {
        int[] candidates = {2, 3, 6, 7};
        List<List<Integer>> result = combinationSum(candidates, 5);
        Assert.assertEquals(1, result.size());
        System.out.println(Arrays.toString(result.toArray()));
    }

    @Test
    public void test4() {
        int[] candidates = {1, 2, 3, 6, 7};
        List<List<Integer>> result = combinationSum(candidates, 5);
        System.out.println(Arrays.toString(result.toArray()));
    }

    @Test
    public void test5() {
        int[] candidates = {8, 7, 4, 3};
        List<List<Integer>> result = combinationSum(candidates, 11);
        System.out.println(Arrays.toString(result.toArray()));
    }


}
