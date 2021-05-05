package LinkedList;

public class Classical {

    /*
        Question1: How to find the middle node of a LinkedList?

        Solution: use fast-slow pointer
        Tips: It's better to set the front-middle ListNode as the Middle node if the LinkedList has even ListNodes
              Because in some hard problem we can use the front-middle ListNode to cut the LinkedList

        Node1 --> Node2 --> Node3 --> Node4 --> Node5 --> Node6 --> null
                            slow
                                                fast
     */

    public ListNode findMid (ListNode head) {
        if (head == null || head.next == null) return head;

        ListNode slow = head;
        ListNode fast = head;
        while (slow.next != null && fast.next != null && fast.next.next != null) {
            slow = slow.next;
            fast = fast.next.next;
        }
        return slow;
    }

    /*
        Question2: Insert a node in a sorted LinkedList

        1 --> 3 --> 6 --> 9 --> null

        Case1: target < 1
        Case2: target >=1 && target <= 9
        Case3: target > 9
     */

    public ListNode insert(ListNode head, ListNode target) {
        if (head == null) return head;

        int val = target.value;
        ListNode cur = head;

        //case1
        if (val < cur.value) {
            target.next = head;
            return target;
        }

        //case2 & 3
        while (cur != null && cur.value < val && cur.next != null) {
            cur = cur.next;
        }
        if (cur.value < val && cur.next == null) {
            cur.next = target;
            target.next = null;
        } else {
            target.next = cur.next;
            cur.next = target;
        }
        return head;
    }

    /*
        Question3: How to merge two sorted LinkedList into one long sorted LinkedList

        1 --> 3 --> 5 --> null
        2 --> 4 --> 6 --> 8 --> null

        merge: 1 --> 2 --> 3 --> 4 --> 5 --> 6 --> 8 -->null

        Tips: We can use dummy head as newHead so we don't need to carry which ListNode should be newHead
     */

    public ListNode mergeTwoLinkedList(ListNode head1, ListNode head2) {
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

    /*
        Question4: How to convert a LinkedList in the following way:

        FROM: node1 --> node2 --> node3 --> node4 --> ... --> nodeN --> null
        TO:   node1 --> nodeN --> node2 --> node(N-1) --> ...

        Solution:
        1. Find the middle node, cut them into two LinkedList

            node1 --> node2 --> ... --> node50
            node51 --> node52 --> ... --> node100

        2. reverse the 2nd LinkedList
            node100 --> node99 --> ... node50

        3. Merge the 1st and 2.5nd LinkedList (one by one)
     */

    /*
        Question5: Partition LinkedList

        Given a value x and a LinkedList, partition it such that all nodes less than x are listed before
        the node larger or equal than x. (Keep the original relative order of the nodes in each partitions)

        Input:  1 --> 6 --> 3 --> 2 --> 5 --> 2 --> null        target x = 4
        result: 1 --> 3 --> 2 --> 2 -->|6 --> 5 --> null

        Solution:
        1. iterate over the LinkedList, and
            - add all nodes smaller than (or equal to) the target to a smallHead LinkedList
            - add all nodes larger than the target to a largeHead LinkedList

        2. concatenate the two LinkedList

        3. DON'T FORGET!!! Disconnect the new tail's original next to NULL (or the result will has dead loop)
     */

    public ListNode partition(ListNode head, int target) {
        if (head == null) return null;

        ListNode dummy1 = new ListNode(-1);
        ListNode dummy2 = new ListNode(-1);
        ListNode cur1 = dummy1;
        ListNode cur2 = dummy2;
        ListNode cur = head;
        while (cur != null) {
            if (cur.value <= target) {
                cur1.next = cur;
                cur1 = cur1.next;
            } else if (cur.value > target) {
                cur2.next = cur;
                cur2 = cur2.next;
            }
            cur = cur.next;
        }
        //concatenate
        cur1.next = dummy2.next;
        cur2.next = null; //IMPORTANT!!! Make sure the new tail's next is cut!
        return dummy1.next;
    }

    public static void main (String[] args) {
        Classical c = new Classical();
        ListNode node1 = new ListNode(1);
        ListNode node2 = new ListNode(2);
        ListNode node3 = new ListNode(3);
        ListNode node4 = new ListNode(4);
        ListNode node5 = new ListNode(5);
        ListNode node6 = new ListNode(6);
        node1.next = node2;
        node2.next = node3;
        node3.next = node4;
        node4.next = node5;
        node5.next = node6;

        //Question1
        ListNode result1 = c.findMid(node1);
        System.out.println(result1.value);

        //Question2
        ListNode newNode2 = new ListNode(5);
        ListNode result2 = c.insert(node1, newNode2);
        ListNode cur2 = result2;
        while (cur2 != null) {
            System.out.print(cur2.value + " --> ");
            cur2 = cur2.next;
        }
        System.out.println();

        //Question3
        ListNode node31 = new ListNode(1);
        ListNode node32 = new ListNode(2);
        ListNode node33 = new ListNode(3);
        ListNode node34 = new ListNode(4);
        ListNode node35 = new ListNode(5);
        ListNode node36 = new ListNode(6);
        ListNode node37 = new ListNode(7);
        node31.next = node33;
        node33.next = node35;
        node35.next = node37;
        node32.next = node34;
        node34.next = node36;
        ListNode result3 = c.mergeTwoLinkedList(node31, node32);
        ListNode cur3 = result3;
        while (cur3 != null) {
            System.out.print(cur3.value + " --> ");
            cur3 = cur3.next;
        }
        System.out.println();

        //Question4
        ListNode node41 = new ListNode(1);
        ListNode node42 = new ListNode(6);
        ListNode node43 = new ListNode(3);
        ListNode node44 = new ListNode(2);
        ListNode node45 = new ListNode(5);
        ListNode node46 = new ListNode(2);
        node41.next = node42;
        node42.next = node43;
        node43.next = node44;
        node44.next = node45;
        node45.next = node46;
        ListNode result4 = c.partition(node41, 4);
        ListNode cur4 = result4;
        while (cur4 != null) {
            System.out.print(cur4.value + " --> ");
            cur4 = cur4.next;
        }
        System.out.println();
    }

}
