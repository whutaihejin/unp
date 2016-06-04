/**
 * Created by taihejin on 16-6-4.
 */
public class P14 {

    public static String longestCommonPrefix(String[] strs) {
        if (strs.length == 0) return "";
        if (strs.length == 1) return strs[0];
        String prefix = strs[0];
        for (int i = 1; i < strs.length; i++) {
            if (strs[i].length() == 0) {
                prefix = "";
                break;
            }
            StringBuilder builder = new StringBuilder();
            for (int k = 0; k < Math.min(prefix.length(), strs[i].length()); k++) {
                if (prefix.charAt(k) == strs[i].charAt(k)) {
                    builder.append(prefix.charAt(k));
                } else {
                    break;
                }
            }
            prefix = builder.toString();
        }
        return prefix;
    }

    public static void main(String[] args) {
        String[] strs = new String[]{"aa", "aab", "ab"};
        System.out.println(longestCommonPrefix(strs));
    }
}
