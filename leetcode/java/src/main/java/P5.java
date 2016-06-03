/**
 * Created by taihejin on 16-6-2.
 */
public class P5 {

    private String preProcess(String s) {
        if (s == null || s.length() < 1) {
            return "^$";
        }
        StringBuilder builder = new StringBuilder();
        builder.append('^');
        for (int i = 0; i < s.length(); i++) {
            builder.append('#').append(s.charAt(i));
        }
        builder.append('#').append('$');
        return builder.toString();
    }

    private String longestPalindrome1(String s) {
        String t = preProcess(s);
        int n = t.length();
        int[] p = new int[n];
        int center = 0, right = 0;
        for (int i = 1; i < n - 1; i++) {
            int mirror = 2 * center - i;
            p[i] = right > i ? Math.min(right - i, p[mirror]) : 0;
            for (; t.charAt(i + 1 + p[i]) == t.charAt(i - 1 - p[i]);) {
                p[i]++;
            }
            if (i + p[i] > right) {
                center = i;
                right = i + p[i];
            }
        }
        int maxCenter = 0;
        int maxLen = 0;
        for (int i = 1; i < n - 1; i++) {
            if (p[i] > maxLen) {
                maxLen = p[i];
                maxCenter = i;
            }
        }
        int start = (maxCenter - 1 - maxLen) / 2;
        return s.substring(start, start + maxLen);
    }

    private String longestPalindrome(String s) {
        if (s == null || s.length() <= 1) {
            return s;
        }
        int length = s.length();
        int [][] flag = new int[length][length];
        for (int i = 0; i <= length - 1; i++) {
            flag[i][i] = 1;
        }
        int max = 0;
        int begin  = 0, end = 0;
        for (int i = 0; i <= length - 2; i++) {
            if (s.charAt(i) == s.charAt(i + 1)) {
                flag[i][i + 1] = 1;
                max = 2;
            }
        }
        for (int len = 3; len <= length; len++) {
            for (int i = 0; i <= length - len; i++) {
                int j = i + len - 1;
                if (s.charAt(i) == s.charAt(j) && flag[i + 1][j - 1] == 1) {
                    flag[i][j] = 1;
                    if (len > max) {
                        max = len;
                        begin = i;
                        end = j;
                    }
                } else {
                    flag[i][j] = 0;
                }
            }
        }
        return s.substring(begin, end + 1);
    }

    private static void doTest(String s, String expect) {
        long startTime = System.currentTimeMillis();
        String val = new P5().longestPalindrome1(s);
        long interval = System.currentTimeMillis() - startTime;
        System.out.println(String.format("%d, %b, expect=%s, actual=%s, s=%s",interval, val.equals(expect), expect, val, s));
        startTime = System.currentTimeMillis();
        val = new P5().longestPalindrome(s);
        interval = System.currentTimeMillis() - startTime;
        System.out.println(String.format("%d, %b, expect=%s, actual=%s, s=%s",interval, val.equals(expect), expect, val, s));
    }

    private static void doPreTest(String s, String expect) {
        String val = new P5().preProcess(s);
        System.out.println(String.format("%b, expect=%s, actual=%s, s=%s", val.equals(expect), expect, val, s));
    }

    private static void test1() {
        String pre = null;
        doPreTest(pre, "^$");
        doPreTest("a", "^#a#$");
        doPreTest("ab", "^#a#b#$");
        doPreTest("aba", "^#a#b#a#$");
        // case 1
        String s = "";
        String expect = "";
        doTest(s, expect);
        // case 2
        s = "a";
        expect = "a";
        doTest(s, expect);
        // case 3
        s = "aab";
        expect = "aa";
        doTest(s, expect);
        // case 4
        s = "aba";
        expect = "aba";
        doTest(s, expect);
        doTest("12345hhhhhhhhhh67890","hhhhhhhhhh");
        StringBuilder builder = new StringBuilder();
        for (int i = 0; i < 5000; i++) {
            builder.append('a');
        }
        String x = builder.toString();
        doTest(x, x);
        doTest("babcbabcbaccba", "abcbabcba");
    }


    public static void main(String[] args) {
        test1();
    }
}
