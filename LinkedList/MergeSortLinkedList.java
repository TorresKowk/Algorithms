package LinkedList;

public class MergeSortLinkedList {

    /*
        Question1: MergeSort LinkedList

        Solution:
        1. Split the list into two parts (use findMid method)
        2. Sort each parts
        3. Combine two parts

        TC: O(nlogn) (O(nlogn) in Stack and O(nlogn) in Heap)
        SC: O(logn) logn times stack

        Input:              4 --> 2 --> 6 --> -3 --> 5
                                /                 \
                        4 --> 2 --> 6           -3 --> 5
                            /        \            /   \
                         4 --> 2      6         -3     5
                         /   \
                        4     2

         =================================================================

                           4     2     6          -3     5
                            \   /                   \   /
                           2 --> 4     6           -3 --> 5
                               \     /
                             2 --> 4 --> 6         -3 --> 5
                                   \                  /
       Output:                  -3 --> 2 --> 4 --> 5 --> 6

     */

    public ListNode mergeSortLinkedList (ListNode head) {
        //sanity check & base case
        if (head == null || head.next == null) return head;

        //recursive rule
        ListNode mid = findMid(head);
        ListNode newHead = mid.next;
        mid.next = null; //REMEMBER: cut the mid tail to split the LinkedList into two parts
        ListNode leftHead = mergeSortLinkedList(head);
        ListNode rightHead = mergeSortLinkedList(newHead);
        ListNode result = mergeTwoLinkedList(leftHead, rightHead);
        return result;
    }

    private ListNode findMid (ListNode head) {
        if (head == null || head.next == null) return head;

        ListNode slow = head;
        ListNode fast = head;
        while (slow.next != null && fast.next != null && fast.next.next != null) {
            slow = slow.next;
            fast = fast.next.next;
        }
        return slow;
    }

    private ListNode mergeTwoLinkedList(ListNode head1, ListNode head2) {
        if (head1 == null) return head2;
        else if (head2 == null) return head1;

        ListNode dummy = new ListNode(-1);
        ListNode cur1 = head1;
        ListNode cur2 = head2;
        ListNode curr = dummy;
        while (cur1 != null && cur2 != null) {
            if (cur1.value <= cur2.value) {
                curr.next = cur1;
                curr = curr.next;
                cur1 = cur1.next;
            } else if (cur1.value > cur2.value) {
                curr.next = cur2;
                curr = curr.next;
                cur2 = cur2.next;
            }
        }
        if (cur1 == null) {
            curr.next = cur2;
        } else if (cur2 == null) {
            curr.next = cur1;
        }
        return dummy.next;
    }
    public static void main(String[] args) {
        MergeSortLinkedList merge = new MergeSortLinkedList();
        ListNode node1 = new ListNode(4);
        ListNode node2 = new ListNode(2);
        ListNode node3 = new ListNode(6);
        ListNode node4 = new ListNode(-3);
        ListNode node5 = new ListNode(5);
        node1.next = node2;
        node2.next = node3;
        node3.next = node4;
        node4.next = node5;

        ListNode result = merge.mergeSortLinkedList(node1);
        ListNode cur = result;
        while (cur != null) {
            System.out.println(cur.value);
            cur = cur.next;
        }
    }
}
