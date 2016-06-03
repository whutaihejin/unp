/**
 * Created by taihejin on 16-6-3.
 */
public class P9 {

    public boolean isPalindrome(int x) {
        if (x < 0) return false;
        int head = 1;
        int tail = 1;
        int num = x;
        for (; num / 10 != 0;) {
            head *= 10;
            num /= 10;
        }
        boolean flag = true;
        for (; head > tail;) {
            if ((x / head) % 10 != (x / tail) % 10) {
                flag = false;
                break;
            }
            head /= 10;
            tail *= 10;
        }
        return flag;
    }

    public static void doTest(int x) {
        boolean val = new P9().isPalindrome(x);
        System.out.println(x + "->" + val);
    }

    public static void main(String[] args) {
        doTest(0);
        doTest(5);
        doTest(10);
        doTest(11);
        doTest(121);
        doTest(12321);
        doTest(123210);
        doTest(5555555);
        doTest(333999333);
        doTest(-1);
        doTest(-111);
        doTest(1000021);
    }
}
