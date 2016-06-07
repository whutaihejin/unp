/**
 * Created by taihejin on 16-6-6.
 */

import org.junit.Assert;
import org.junit.Test;

public class P25 {

    public static class ListNode {
        int val;
        ListNode next;
        ListNode(int x) { val = x; }
        ListNode(int x, ListNode n) { val = x; next = n; }

        @Override
        public String toString() {
            StringBuilder builder = new StringBuilder();
            ListNode p = this;
            while (p != null) {
                builder.append(p.val).append("->");
                p = p.next;
            }
            builder.deleteCharAt(builder.length() - 1);
            builder.deleteCharAt(builder.length() - 1);
            return builder.toString();
        }
    }

    public ListNode reverseKGroup(ListNode head, int k) {
        if (head == null || k == 1) {
            return head;
        }
        ListNode mock = new ListNode(0);
        mock.next = head;
        ListNode dummy = mock;
        for (;;) {
            ListNode sentinel = dummy;
            for (int i = 0; i < k && sentinel != null; i++) {
                sentinel = sentinel.next;
            }
            if (sentinel == null) {
                break;
            }
            ListNode low = dummy.next;
            for (;;) {
                ListNode high = low.next;
                low.next = high.next;
                high.next = dummy.next;
                dummy.next = high;
                if (high == sentinel) {
                    dummy = low;
                    break;
                }
            }
        }
        return mock.next;
    }

    @Test
    public void doTest1() {
        ListNode n6 = new ListNode(6, null);
        ListNode n5 = new ListNode(5, n6);
        ListNode n4 = new ListNode(4, n5);
        ListNode n3 = new ListNode(3, n4);
        ListNode n2 = new ListNode(2, n3);
        ListNode l1 = new ListNode(1, n2);
        System.out.println(reverseKGroup(l1, 3));
    }

    @Test
    public void doTest2() {
        ListNode n7 = new ListNode(7, null);
        ListNode n6 = new ListNode(6, n7);
        ListNode n5 = new ListNode(5, n6);
        ListNode n4 = new ListNode(4, n5);
        ListNode n3 = new ListNode(3, n4);
        ListNode n2 = new ListNode(2, n3);
        ListNode l1 = new ListNode(1, n2);
        System.out.println(reverseKGroup(l1, 3));
    }

    @Test
    public void doTest3() {
        ListNode n7 = new ListNode(7, null);
        ListNode n6 = new ListNode(6, n7);
        ListNode n5 = new ListNode(5, n6);
        ListNode n4 = new ListNode(4, n5);
        ListNode n3 = new ListNode(3, n4);
        ListNode n2 = new ListNode(2, n3);
        ListNode l1 = new ListNode(1, n2);
        System.out.println(reverseKGroup(l1, 1));
    }

    @Test
    public void doTest4() {
        ListNode n7 = new ListNode(7, null);
        ListNode n6 = new ListNode(6, n7);
        ListNode n5 = new ListNode(5, n6);
        ListNode n4 = new ListNode(4, n5);
        ListNode n3 = new ListNode(3, n4);
        ListNode n2 = new ListNode(2, n3);
        ListNode l1 = new ListNode(1, n2);
        System.out.println(reverseKGroup(l1, 2));
    }

    @Test
    public void doTest5() {
        ListNode n7 = new ListNode(7, null);
        ListNode n6 = new ListNode(6, n7);
        ListNode n5 = new ListNode(5, n6);
        ListNode n4 = new ListNode(4, n5);
        ListNode n3 = new ListNode(3, n4);
        ListNode n2 = new ListNode(2, n3);
        ListNode l1 = new ListNode(1, n2);
        ListNode r = reverseKGroup(l1, 4);
        System.out.println(r);
        Assert.assertEquals(r.val, 4);
    }

    public void baseTest(int k) {
        ListNode n7 = new ListNode(7, null);
        ListNode n6 = new ListNode(6, n7);
        ListNode n5 = new ListNode(5, n6);
        ListNode n4 = new ListNode(4, n5);
        ListNode n3 = new ListNode(3, n4);
        ListNode n2 = new ListNode(2, n3);
        ListNode l1 = new ListNode(1, n2);
        ListNode r = reverseKGroup(l1, k);
        System.out.println(r);
        Assert.assertEquals(r.val, k);
    }

    @Test
    public void allTest() {
        for (int i = 1; i <= 7; i++) {
            baseTest(i);
        }
    }

    @Test
    public void excelTest() {
        ListNode n7 = new ListNode(7, null);
        ListNode n6 = new ListNode(6, n7);
        ListNode n5 = new ListNode(5, n6);
        ListNode n4 = new ListNode(4, n5);
        ListNode n3 = new ListNode(3, n4);
        ListNode n2 = new ListNode(2, n3);
        ListNode l1 = new ListNode(1, n2);
        ListNode r = reverseKGroup(l1, 10);
        System.out.println(r);
        Assert.assertEquals(r.val, 1);
    }

}
