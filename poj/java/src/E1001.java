import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Scanner;
import java.util.List;

/**
 * Created by taihejin on 16-4-12.
 */
public class E1001 {

    public static void main(String[] args) {
        List<Integer> list = new ArrayList<Integer>();
        list.add(0);
        Scanner cin = new Scanner(System.in);
        String x = cin.next();
        String y = cin.next();

        int[] product = new int[Math.max(x.length(), y.length()) * 2];
        for (int i = x.length() - 1; i >= 0; i--) {
            int start = product.length - x.length() + i;
            for (int j = y.length() - 1; j >= 0; j--) {
                product[start--] += (x.charAt(i) - '0') * (y.charAt(j) - '0');
            }
        }
        for (int i = product.length - 1; i > 0; i--) {
            if (product[i] > 9) {
                product[i - 1] += product[i] / 10; // + carry
                product[i] %= 10;
            }
        }
        StringBuilder builder = new StringBuilder();
        boolean first = true;
        for (int i = 0; i < product.length; i++) {
            if (first) {
                if (product[i] != 0) {
                    builder.append((char)(product[i] + '0'));
                    first = false;
                }
            } else {
                builder.append((char)(product[i] + '0'));
            }
        }
        BigDecimal decimal = new BigDecimal(x);
        decimal = decimal.multiply(new BigDecimal(y));
        System.out.println(decimal.compareTo(new BigDecimal(builder.toString())));
    }
}