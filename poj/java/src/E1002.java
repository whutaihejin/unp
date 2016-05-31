import java.util.Arrays;
import java.util.Scanner;

/**
 * Created by taihejin on 16-4-17.
 */
public class E1002 {

    private static char[] map = new char[]{'2', '2', '2', '3', '3', '3', '4', '4', '4', '5', '5', '5', '6', '6', '6', '7', '0', '7', '7', '8', '8', '8', '9', '9', '9', '0'};
                                   //A    B    C    D    E    F    G    H    I    J    K    L    M    N    O    P    Q    R    S    T    U    V    W    X    Y    Z

    public static void main(String[] args) {
        Scanner cin = new Scanner(System.in);
        String phone[] = new String[100000];
        char buffer[] = new char[8];
        int index = 0, i = 0;
        int mount = cin.nextInt();
        for (i = 0; i < mount; i++) {
            String number = cin.next();
            index = 0;
            for (int j = 0; j < number.length(); j++) {
                char ch = number.charAt(j);
                if (ch == '-') {
                    continue;
                }
                if (ch >= 'A' && ch <= 'Z') {
                    ch = map[ch - 'A'];
                }
                buffer[index++] = ch;
                if (index == 3) {
                    buffer[index++] = '-';
                }
            }
            phone[i] = new String(buffer);
        }
        Arrays.sort(phone, 0, mount);
        int counter = 1, flag = 0;
        for (i = 1; i < mount; i++) {
            if (phone[i - 1].equals(phone[i])) {
                counter++;
            } else {
                if (counter > 1) {
                    System.out.printf("%s %d\n", phone[i - 1], counter);
                    flag = 1;
                }
                counter = 1;
            }
        }
        if (counter > 1) {
            System.out.printf("%s %d\n", phone[i - 1], counter);
            flag = 1;
        }
        if (flag == 0) {
            System.out.printf("No duplicates.\n");
        }
    }
}
