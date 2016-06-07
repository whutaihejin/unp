import org.junit.Test;

/**
 * Created by taihejin on 16-6-6.
 */
public class P24 {

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

    public ListNode swapPairs(ListNode head) {
        if (head == null) {
            return head;
        }
        ListNode forward = head;
        ListNode low = head;
        ListNode high = head.next;
        while (high != null) {
            if (forward == head) {
                head = high;
            } else {
                forward.next = high;
            }
            low.next = high.next;
            high.next = low;
            forward = low;
            low = forward.next;
            high = low == null ? null : low.next;
        }
        return head;
    }

    @Test
    public void doTest0() {
        System.out.println(swapPairs(null));
    }

    @Test
    public void doTest1() {
        ListNode n1 = new ListNode(1);
        System.out.println(swapPairs(n1));
    }

    @Test
    public void doTest2() {
        ListNode n2 = new ListNode(2);
        ListNode n1 = new ListNode(1, n2);
        System.out.println(swapPairs(n1));
    }

    @Test
    public void doTest3() {
        ListNode n3 = new ListNode(3, null);
        ListNode n2 = new ListNode(2, n3);
        ListNode l1 = new ListNode(1, n2);
        System.out.println(swapPairs(l1));
    }

    @Test
    public void doTest4() {
        ListNode n4 = new ListNode(4, null);
        ListNode n3 = new ListNode(3, n4);
        ListNode n2 = new ListNode(2, n3);
        ListNode l1 = new ListNode(1, n2);
        System.out.println(swapPairs(l1));
    }

    @Test
    public void doTest5() {
        ListNode n5 = new ListNode(5, null);
        ListNode n4 = new ListNode(4, n5);
        ListNode n3 = new ListNode(3, n4);
        ListNode n2 = new ListNode(2, n3);
        ListNode l1 = new ListNode(1, n2);
        System.out.println(swapPairs(l1));
    }

    @Test
    public void doTest6() {
        ListNode n6 = new ListNode(6, null);
        ListNode n5 = new ListNode(5, n6);
        ListNode n4 = new ListNode(4, n5);
        ListNode n3 = new ListNode(3, n4);
        ListNode n2 = new ListNode(2, n3);
        ListNode l1 = new ListNode(1, n2);
        System.out.println(swapPairs(l1));
    }

}
