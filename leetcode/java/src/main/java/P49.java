/**
 * Created by taihejin on 16-6-11.
 */
import org.junit.Assert;
import org.junit.Test;

import java.util.*;

public class P49 {

    public String process(String x) {
        char[] internal = x.toCharArray();
        Arrays.sort(internal);
        return new String(internal);
    }

    public List<List<String>> groupAnagrams(String[] strs) {
        Map<String, List<String>> dict = new HashMap<String, List<String>>();
        for (String str : strs) {
            String origin = str;
            str = process(str);
            if (dict.containsKey(str)) {
                dict.get(str).add(origin);
            } else {
                List<String> list = new ArrayList<String>();
                list.add(origin);
                dict.put(str, list);
            }
        }
        List<List<String>> ret = new ArrayList<List<String>>();
        ret.addAll(dict.values());
        return ret;
    }

    @Test
    public void test1() {
        Assert.assertEquals("aet", process("eat"));
        Assert.assertEquals("aet", process("tae"));
        Assert.assertEquals("aet", process("tea"));
        Assert.assertEquals("aet", process("aet"));
        Assert.assertEquals("aet", process("ate"));
        Assert.assertEquals("aet", process("eta"));
        Assert.assertEquals("", process(""));
        Assert.assertEquals("1", process("1"));
        Assert.assertEquals("123", process("321"));
        Assert.assertEquals("66789", process("98766"));
    }

    @Test
    public void test2() {
        String[] strs = {"eat", "tea", "tan", "ate", "nat", "bat"};
        List<List<String>> ret = groupAnagrams(strs);
        System.out.println(Arrays.deepToString(ret.toArray()));
        Assert.assertEquals(3, ret.size());
    }
}
