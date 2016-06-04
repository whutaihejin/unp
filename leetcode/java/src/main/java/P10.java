/**
 * Created by taihejin on 16-6-4.
 */
public class P10 {

    public static void doMatch(String str, String pattern) {
        System.out.println(String.format("%b, str=%s, pattern=%s", str.matches(pattern), str, pattern));
    }

    public static void main(String[] args) {
        doMatch("abcccbba", ".*bc*");
        doMatch("aaa", "a");
        doMatch("aa", "aa");
        doMatch("aaa", "a*");
    }
}
