/**
 * Created by taihejin on 16-6-2.
 */
public class P5 {

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
        String val = new P5().longestPalindrome(s);
        System.out.println(String.format("%b, expect=%s, actual=%s, s=%s", val.equals(expect), expect, val, s));
    }

    private static void test1() {
//        // case 1
//        String s = "";
//        String expect = "";
//        doTest(s, expect);
//        // case 2
//        s = "a";
//        expect = "a";
//        doTest(s, expect);
//        // case 3
//        s = "aab";
//        expect = "aa";
//        doTest(s, expect);
//        // case 4
//        s = "aba";
//        expect = "aba";
//        doTest(s, expect);
//        doTest("12345hhhhhhhhhh67890","hhhhhhhhhh");
        StringBuilder builder = new StringBuilder();
        for (int i = 0; i < 1000; i++) {
            builder.append('a');
        }
        long startTime = System.currentTimeMillis();
        String x = builder.toString();
        doTest(x, x);
        System.out.println(System.currentTimeMillis() - startTime);
    }


    public static void main(String[] args) {
        test1();
    }
}
