import org.junit.Assert;
import org.junit.Test;

/**
 * Created by taihejin on 16-6-10.
 */
public class P44 {

    public boolean doMatch(String s, int i, String p, int j) {
        if (i == s.length()) {
            return j == p.length();
        }
        if (j == p.length()) {
            return false;
        }
        char pp = p.charAt(j);
        if (pp == '?') {
            return doMatch(s, i + 1, p, j + 1);
        } else if (pp != '*') {
            return pp == s.charAt(i) && doMatch(s, i + 1, p, j + 1);
        } else {
            for (int k = i; k <= s.length(); k++) {
                if (doMatch(s, k, p, j + 1)) {
                    return true;
                }
            }
            return false;
        }
    }

    public boolean isMatch(String s, String p) {
        if (s == null || s.isEmpty()) {
            boolean ret = p == null || p.isEmpty();
            if (ret) {
                return ret;
            } else {
                for (int i = 0; i < p.length(); i++) {
                    if (p.charAt(i) != '*') {
                        return false;
                    }
                }
                return true;
            }
        }
        if (p == null || p.isEmpty()) {
            return false;
        }
        return doMatch(s, 0, process(p), 0);
    }

    private String process(String p) {
        boolean star = false;
        StringBuilder builder = new StringBuilder();
        for (int i = 0; i < p.length(); i++) {
            char ch = p.charAt(i);
            if (star && ch == '*') {
                continue;
            } else {
                builder.append(ch);
                if (ch == '*') {
                    star = true;
                } else {
                    star = false;
                }
            }
        }
        return builder.toString();
    }

    @Test
    public void test0() {
        Assert.assertEquals("a*b", process("a*****b"));
        Assert.assertEquals("*a*b*c*d*", process("*a*****b**c*d*"));
        Assert.assertEquals("*", process("*****"));
        Assert.assertEquals("*b", process("*****b"));
        Assert.assertEquals("*b*", process("*****b*"));
    }

    @Test
    public void test1() {
        Assert.assertEquals(false, isMatch("aa", "a"));
        Assert.assertEquals(true, isMatch("aa", "aa"));
        Assert.assertEquals(false, isMatch("aaa", "aa"));
        Assert.assertEquals(true, isMatch("aaa", "*"));
        Assert.assertEquals(true, isMatch("aaa", "a*"));
        Assert.assertEquals(true, isMatch("ab", "?*"));
        Assert.assertEquals(false, isMatch("aab", "c*a*b"));
        Assert.assertEquals(false, isMatch("ab", "c*a*b"));
        Assert.assertEquals(true, isMatch("cHELLOaHELLOb", "c*a*b"));
        Assert.assertEquals(true, isMatch("c", "**"));
        Assert.assertEquals(true, isMatch("", ""));
        Assert.assertEquals(true, isMatch("", "*"));
        Assert.assertEquals(true, isMatch("", "**"));
        Assert.assertEquals(true, isMatch("", "****"));
        Assert.assertEquals(true, isMatch("c", "?"));
        Assert.assertEquals(false, isMatch("c", ""));
    }

    @Test
    public void test2() {
        String s = "aaabbbaabaaaaababaabaaabbabbbbbbbbaabababbabbbaaaaba";
        String p = "a*******b";
        Assert.assertEquals(false, isMatch(s, p));
    }

    @Test
    public void test3() {
        String s = "abbabaaabbabbaababbabbbbbabbbabbbabaaaaababababbbabababaabbababaabbbbbbaaaabababbbaabbbbaabbbbababababbaabbaababaabbbababababbbbaaabbbbbabaaaabbababbbbaababaabbababbbbbababbbabaaaaaaaabbbbbaabaaababaaaabb";
        String p = "**aa*****ba*a*bb**aa*ab****a*aaaaaa***a*aaaa**bbabb*b*b**aaaaaaaaa*a********ba*bbb***a*ba*bb*bb**a*b*bb";
        Assert.assertEquals(false, isMatch(s, p));

    }
}
