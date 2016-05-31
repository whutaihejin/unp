import java.util.Scanner;

/**
 * Created by taihejin on 16-4-17.
 */
public class E1003 {

    public static void main(String[] args) {

        System.out.print(String.format("sdad%d", 2));
        System.out.println("--- separate ---");
        System.out.print(String.format("sdad%d\n", 2));

        Scanner cin = new Scanner(System.in);
        for (;;) {
            double length = cin.nextDouble();
            if (length == 0) {
                break;
            }
            double sum = 0;
            int n = 1;
            for (n = 1; sum < length; n++) {
                sum += (1.0 / (n + 1));
            }
            System.out.printf("%d card(s)\n", n - 1);
        }
    }
}
