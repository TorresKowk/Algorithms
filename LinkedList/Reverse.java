package LinkedList;

public class Reverse {

    /*
        Question1: Reverse a LinkedList iteratively

             Node1 --> Node2 --> Node3 --> ... --> NodeN --> null
             head
        pre  cur       next

        For each steps:
        1. set point next as cur.next (make sure cur is not-null)
           (must set before change cur.next, or we lose the remain LinkedList)
        2. set cur.next to pre

        In place operation: TC: O(n); SC: O(1)
     */

    public ListNode reverseIteratively (ListNode head) {
        if (head == null || head.next == null) return head;

        ListNode dummy = new ListNode(-1);
        dummy.next = head;
        ListNode pre = dummy;
        ListNode cur = head;
        while (cur != null) {
            ListNode next = cur.next;
            cur.next = pre;
            pre = cur;
            cur = next;
        }
        head.next = null; // Remember set dummy to null!!! Or the LinkedList will be a dead cycle
        return pre;
    }

    /*
        Question2: Reverse a LinkedList Recursively

        Node1 --> Node2 --> Node3 --> ... --> NodeN --> null

        Before:                        sub-problem
                 Node1 --> "" Node2 --> Node3 --> ... --> NodeN --> null ""
                  cur         next
        After:
        null <-- Node1 <-- "" Node2 --> Node3 --> ... --> NodeN --> null ""
                  cur         next

        For each recursion stack:
        1. First call smaller problem, add breakpoint at each stack, to the smallest problem (head == null)
        2. Continue each break point from last stack to first stack (backward)
           For each stack:
           1. set head.next.next to head
           2. set head.next to null
           3. return the current stack to newNode (new LinkedList's head)
     */

    public ListNode reverseRecursively (ListNode head) {
        //base case & sanity check
        if (head == null || head.next == null) return head;

        //sub-problem
        ListNode newNode = reverseIteratively(head.next); //break point

        //recursive rule
        head.next.next = head;
        head.next = null;
        return newNode;
    }

    public static void main (String[] args) {
        Reverse r = new Reverse();
        ListNode node1 = new ListNode(1);
        ListNode node2 = new ListNode(2);
        ListNode node3 = new ListNode(3);
        ListNode node4 = new ListNode(4);
        ListNode node5 = new ListNode(5);
        node1.next = node2;
        node2.next = node3;
        node3.next = node4;
        node4.next = node5;

        //Question1
//        ListNode newNode1 = r.reverseIteratively(node1);
//        ListNode cur1 = newNode1;
//        while (cur1 != null) {
//            System.out.println(cur1.value);
//            cur1 = cur1.next;
//        }

        //Question2
        ListNode newNode2 = r.reverseIteratively(node1);
        ListNode cur2 = newNode2;
        while (cur2 != null) {
            System.out.println(cur2.value);
            cur2 = cur2.next;
        }
    }

}
