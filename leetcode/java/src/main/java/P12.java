/**
 * Created by taihejin on 16-6-4.
 */
public class P12 {

    private static final String[][] base = new String[][]{
            {"", "I", "II", "III", "IV", "V", "VI", "VII", "VIII", "IX"},
            {"", "X", "XX", "XXX", "XL", "L", "LX", "LXX", "LXXX", "XC"},
            {"", "C", "CC", "CCC", "CD", "D", "DC", "DCC", "DCCC", "CM"},
            {"", "M", "MM", "MMM"}
    };

    public String intToRoman(int num) {
        StringBuilder builder = new StringBuilder();
        builder.append(base[3][num / 1000 % 10]);
        builder.append(base[2][num / 100 % 10]);
        builder.append(base[1][num / 10 % 10]);
        builder.append(base[0][num % 10]);
        return builder.toString();
    }

    public int romanToInt(String s) {
        int size = s.length();
        int value = 0;
        for (int i = 0; i < size;) {
            char ch = s.charAt(i);
            int index = 0;
            int delta = 1;
            if (ch == 'M') {
                index = 3;
                delta = 1000;
            } else if (ch == 'C' || ch == 'D') {
                index = 2;
                delta = 100;
            } else if (ch == 'X' || ch == 'L') {
                index = 1;
                delta = 10;
            }
            for (int k = index == 3 ? 3 : 9; k > 0; k--) {
                if (s.startsWith(base[index][k], i)) {
                    value += k * delta;
                    i += base[index][k].length();
                    break;
                }
            }
        }
        return value;
    }

    public static void Old(int num, String expect) {
//        String value = new P12().intToRoman(num);
//        System.out.println(String.format("%b, expect=%s, actual=%s, num=%d", value.equals(expect), expect, value, num));
    }

    public static void doTest(int num) {
        P12 p = new P12();
        String roman = p.intToRoman(num);
        int val = p.romanToInt(roman);
        if (val != num) {
            System.out.println(String.format("%b, num=%d, roman=%s, val=%d", val == num, num, roman, val));
        }
    }

    public static void main(String[] args) {
        System.out.println(new P12().romanToInt("CMM"));
        for (int i = 1; i <= 3999; i++) {
            doTest(i);
        }
        Old(1, "I");
        Old(2, "II");
        Old(3, "III");
        Old(4, "IV");
        Old(5, "V");
        Old(6, "VI");
        Old(7, "VII");
        Old(8, "VIII");
        Old(9, "IX");
        Old(10, "X");
        Old(20, "XX");
        Old(30, "XXX");
        Old(40, "XL");
        Old(50, "L");
        Old(60, "LX");
        Old(70, "LXX");
        Old(80, "LXXX");
        Old(90, "XC");
        Old(100, "C");
        Old(200, "CC");
        Old(300, "CCC");
        Old(400, "CD");
        Old(500, "D");
        Old(600, "DC");
        Old(700, "DCC");
        Old(800, "DCCC");
        Old(900, "CM");
        Old(1000, "M");
        Old(2000, "MM");
        Old(3000, "MMM");
        Old(3999, "MMMCMXCIX");
    }
}
