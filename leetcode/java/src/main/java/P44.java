import org.junit.Assert;
import org.junit.Test;

/**
 * Created by taihejin on 16-6-10.
 */
public class P44 {


    public boolean isMatch(String s, String p) {
        int i = 0, j = 0;
        int size = s.length(), limit = p.length();
        int star = -1, reset = -1;
        char pc = '.';
        while (i < size) {
            if (j < limit && ((pc = p.charAt(j)) == s.charAt(i) || pc == '?')) {
                i++;
                j++;
                continue;
            }
            if (j < limit && pc == '*') {
                star = j;
                reset = i;
                j++;
                continue;
            }
            if (star != -1) {
                i = reset + 1;
                j = star + 1;
                reset++;
                continue;
            }
            return false;
        }
        while (j < limit && p.charAt(j) == '*') {
            j++;
        }
        if (i == size) {
            return j == limit;
        }
        return false;
    }

    @Test
    public void test0() {
        Assert.assertEquals(true, isMatch("hi", "*?"));
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
        Assert.assertEquals(false, isMatch("aac", "a*b*c"));
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
