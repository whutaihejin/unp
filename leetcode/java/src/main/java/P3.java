import java.util.HashSet;
import java.util.Set;

/**
 * Created by taihejin on 16-5-29.
 */
public class P3 {

    private int[] flag = new int[256];

    private void reset() {
        for (int i = 0; i < flag.length; i++) {
            flag[i] = 0;
        }
    }

    public int lengthOfLongestSubstring(String s) {
        if (s.length() <= 1) return s.length();
        reset();
        int i = 0, j = 0, maxLength = 1;
        for (i = 0; i < s.length(); i++) {
            for (j = i; j < s.length(); j++) {
                int index = (int)s.charAt(j);
                if (flag[index] == 0) {
                    flag[index] = 1;
                } else {
                    maxLength = j - i > maxLength ? j - i : maxLength;
                    reset();
                    break;
                }
            }
            if (j == s.length()) {
                break;
            }
        }
        return j - i > maxLength ? j - i : maxLength;
    }

    private void doTest(String value) {
        System.out.println(String.format("length=%d, max substring=%d", value.length(), lengthOfLongestSubstring(value)));
    }

    private void test() {
        doTest("dvdf");
        doTest("abcabcbb");
        doTest("bbbbb");
        doTest("pwwkew");
        doTest("xbawtvebluuagttbeqbihnlucpmg");
    }

    public static void main(String[] args) {
        new P3().test();
    }

}
