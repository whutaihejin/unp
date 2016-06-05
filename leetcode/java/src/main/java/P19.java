/**
 * Created by taihejin on 16-6-5.
 */

public class P19 {

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

    public static ListNode removeNthFromEnd(ListNode head, int n) {
        ListNode forward = head;
        ListNode follow = null;
        // n step
        for (int i = 0; i < n && forward != null; i++) {
            forward = forward.next;
        }
        // to tail with follow
        for (; forward != null;) {
            forward = forward.next;
            follow = follow == null ? head : follow.next;
        }
        // remove not correct yet!
        if (follow != null) {
            ListNode q = follow.next;
            follow.next = q.next;
        } else {
            head = head.next;
        }
        return head;
    }

    public static void doTest(int n) {
        ListNode n5 = new ListNode(5, null);
        ListNode n4 = new ListNode(4, n5);
        ListNode n3 = new ListNode(3, n4);
        ListNode n2 = new ListNode(2, n3);
        ListNode head = new ListNode(1, n2);
        System.out.println(head.toString());
        long start = System.currentTimeMillis();
        head = removeNthFromEnd(head, n);
        long interval = System.currentTimeMillis() - start;
        System.out.println(head.toString());
        System.out.println(String.format("n=%d, time=%d", n, interval));
    }

    public static void main(String[] args) {
        for (int i = 1; i <= 5; i++) {
            doTest(i);
        }
    }

}