/**
 * Created by taihejin on 16-6-5.
 */
public class P21 {

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

    public static ListNode mergeTwoLists(ListNode l1, ListNode l2) {
        ListNode head = null;
        ListNode p = null;
        while (l1 != null && l2 != null) {
            int min = 0;
            if (l1.val < l2.val) {
                min = l1.val;
                l1 = l1.next;
            } else {
                min = l2.val;
                l2 = l2.next;
            }
            if (head == null) {
                head = new ListNode(min);
                p = head;
            } else {
                ListNode q = new ListNode(min);
                p.next = q;
                p = q;
            }
        }
        while (l1 != null) {
            if (head == null) {
                head = new ListNode(l1.val);
                p = head;
            } else {
                ListNode q = new ListNode(l1.val);
                p.next = q;
                p = q;
            }
            l1 = l1.next;
        }
        while (l2 != null) {
            if (head == null) {
                head = new ListNode(l2.val);
                p = head;
            } else {
                ListNode q = new ListNode(l2.val);
                p.next = q;
                p = q;
            }
            l2 = l2.next;
        }
        return head;
    }

    public static void doTest() {
        ListNode n5 = new ListNode(5, null);
        ListNode n4 = new ListNode(4, n5);
        ListNode n3 = new ListNode(3, n4);
        ListNode n2 = new ListNode(2, n3);
        ListNode l1 = new ListNode(1, n2);

        ListNode m5 = new ListNode(10, null);
        ListNode m4 = new ListNode(9, m5);
        ListNode m3 = new ListNode(8, m4);
        ListNode m2 = new ListNode(7, m3);
        ListNode l2 = new ListNode(6, m2);

        System.out.println(l1);
        System.out.println(l2);
        System.out.println(mergeTwoLists(l1, l2));
        System.out.println("######################");

        System.out.println(l1);
        System.out.println("null");
        System.out.println(mergeTwoLists(l1, null));
        System.out.println("######################");

        System.out.println("null");
        System.out.println(l2);
        System.out.println(mergeTwoLists(null, l2));
        System.out.println("######################");

        System.out.println("null");
        System.out.println("null");
        System.out.print(mergeTwoLists(null, null));
        System.out.println("######################");
    }

    public static void main(String[] args) {
        doTest();
    }
}
