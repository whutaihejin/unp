/**
 * Created by taihejin on 16-6-3.
 */
public class P6 {

    public static String convert(String s, int numRows) {
        if (numRows == 1) {
            return s;
        }
        StringBuilder builder = new StringBuilder();
        int circle = 2 * numRows - 1;
        for (int row = 1; row <= numRows; row++) {
            if (row == 1 || row == numRows) {
                int interval = 2 * (numRows - 1);
                for (int i = row - 1; i < s.length(); i += interval) {
                    builder.append(s.charAt(i));
                }
            } else {
                int interval = 2 * (numRows - row);
                for (int i = row - 1; i < s.length();) {
                    builder.append(s.charAt(i));
                    i += interval;
                    if (i >= s.length()){
                        break;
                    }
                    builder.append(s.charAt(i));
                    i += (circle - interval - 1);
                }
            }
        }
        return builder.toString();
    }

    public static void doTest(String s, int numRows, String expect) {
        String val = convert(s, numRows);
        System.out.println(String.format("%b, expect=%s, actual=%s, s=%s", val.equals(expect), expect, val, s));
    }

    public static void main(String[] args) {
        String s = "PAYPALISHIRING";
        for (int i = 1; i < s.length() + 2; i++) {
            doTest(s, i, s);
        }
    }


}
