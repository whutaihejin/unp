/**
 * Created by taihejin on 16-6-5.
 */

import java.util.*;

public class P22 {

    // [()()()(), (()())(), (()(())), ()()(()), (((()))), (())()(), ()((())), ()(())(), ()(()()), (()()()), ((()())), ((()))(), ((())())]
    // [(((()))), ((()())), ((())()), ((()))(), (()(())), (()()()), (()())(), (())(()), (())()(), ()((())), ()(()()), ()(())(), ()()(()), ()()()()]
    // [(((()))), ((()())), ((())()), ((()))(), (()(())), (()()()), (()())(), (())(()), (())()(), ()((())), ()(()()), ()(())(), ()()(()), ()()()()]
    public static List<String> generateParenthesis(int n) {
        List<String> list = new ArrayList<String>();
        crazy(n, n, "", list);
        return list;
    }

    public static void crazy(int x, int y, String prefix, List<String> list) {
        if (x == y && y == 0) {
            System.out.println(prefix);
            list.add(prefix);
            return;
        }
        if (x >= y) {
            crazy(x - 1, y, prefix + "(", list);
        } else if (x == 0) {
            crazy(x, y - 1, prefix + ")", list);
        } else {
            crazy(x - 1, y, prefix + "(", list);
            crazy(x, y - 1, prefix + ")", list);
        }
    }

    public static void main(String[] args) {
        System.out.println(generateParenthesis(4));
    }
}
