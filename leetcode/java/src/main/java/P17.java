/**
 * Created by taihejin on 16-6-5.
 */
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class P17 {
    //                                            0   1    2      3     4      5      6      7       8      9
    private static final String[] digitCharMap = {"", "", "abc", "def", "ghi", "jkl", "mno", "pqrs", "tuv", "wxyz"};

    public static List<String> letterCombinations(String digits) {
        if (digits == null || digits.isEmpty()) return Arrays.asList();
        char ch = digits.charAt(0);
        String dict = digitCharMap[ch - '0'];
        List<String> list = new ArrayList<String>();
        List<String> child = letterCombinations(digits.substring(1));
        if (child.isEmpty()) {
            for (int i = 0; i < dict.length(); i++) {
                list.add(dict.charAt(i) + "");
            }
        } else if (dict.isEmpty()) {
            return letterCombinations(digits.substring(1));
        } else {
            for (int i = 0; i < dict.length(); i++) {
                for (String suffix : child) {
                    list.add(dict.charAt(i) + suffix);
                }
            }
        }
        return list;
    }

    public static void doTest(String number) {
        System.out.println(String.format("number=%s, result=%s", number, letterCombinations(number).toString()));
    }

    public static void main(String[] args) {
        doTest("");
        doTest("3");
        doTest("23");
        doTest("023");
        doTest("123");
        doTest("230");
        doTest("231");
        doTest("203");
        doTest("213");
        doTest("2103");
        doTest("2113");
        doTest("2003");
        doTest("3");
        doTest("33");
        doTest("11");
        doTest("00");
    }
}
