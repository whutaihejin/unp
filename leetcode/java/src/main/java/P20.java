import java.util.LinkedList;

/**
 * Created by taihejin on 16-6-5.
 */
public class P20 {
    // '(', ')', '{', '}', '[' and ']'
    public static boolean isValid(String s) {
        LinkedList<Character> stack = new LinkedList<Character>();
        boolean flag = true;
        for (int i = 0; i < s.length(); i++) {
            char c = s.charAt(i);
            if (c == '(' || c == '[' || c == '{') {
                stack.push(c);
            } else if (c == ')') {
                if (stack.isEmpty() || stack.pop() != '(') {
                    flag = false;
                    break;
                }
            } else if (c == ']') {
                if (stack.isEmpty() || stack.pop() != '[') {
                    flag = false;
                    break;
                }
            } else if (c == '}') {
                if (stack.isEmpty() || stack.pop() != '{') {
                    flag = false;
                    break;
                }
            }
        }
        return flag && stack.isEmpty();
    }

    public static void doTest(String str, boolean expect) {
        long start = System.currentTimeMillis();
        boolean actual = isValid(str);
        long interval = System.currentTimeMillis() - start;
        System.out.println(String.format("%b, expect=%b, actual=%b, interval=%d, str=%s", actual == expect, expect, actual, interval, str));
    }

    public static void main(String[] args) {
        doTest("", true);
        doTest("(", false);
        doTest(")", false);
        doTest("()", true);
        doTest("[", false);
        doTest("]", false);
        doTest("[]", true);
        doTest("{", false);
        doTest("}", false);
        doTest("{}", true);
        doTest("(){}[]", true);
        doTest("([{}]){([])}[{()}]", true);
        doTest("sdf(saf[{fsad}]){([])}[{sfsa(sfasdf)sdfa}saf]", true);
    }
}
