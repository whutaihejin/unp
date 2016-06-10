import org.junit.Test;

import java.util.*;

/**
 * Created by taihejin on 16-6-10.
 */
public class P40 {

    private void dfs(int[] candidates, int start, LinkedList<Integer> stack, List<List<Integer>> result, int target) {
        if (target == 0) {
            List<Integer> right = new ArrayList<Integer>(stack);
            Collections.reverse(right);
            result.add(right);
            return;
        }
        if (start >= candidates.length || target < candidates[start]) {
            return;
        }
        for (int i = start; i < candidates.length; i++) {
            if (i > start && candidates[i] == candidates[i - 1]) {
                continue;
            }
            stack.push(candidates[i]);
            dfs(candidates, i + 1, stack, result, target - candidates[i]);
            stack.pop();
        }
    }

    public List<List<Integer>> combinationSum2(int[] candidates, int target) {
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
    public void test0() {
        int[] candidates = {1};
        List<List<Integer>> result = combinationSum2(candidates, 1);
        System.out.println(Arrays.toString(result.toArray()));
    }

    @Test
    public void test1() {
        int[] candidates = {10, 1, 2, 7, 6, 1, 5};
        List<List<Integer>> result = combinationSum2(candidates, 8);
        System.out.println(Arrays.toString(result.toArray()));
    }
}
