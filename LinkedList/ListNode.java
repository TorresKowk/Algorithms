package LinkedList;

public class ListNode {

    /*
        KEY POINTS:
        1. When you want to dereference a ListNode, make sure it is not a NULL pointer

            p.value             "." is an action that is called "de-reference, make sure p is not-null
            p.next.value        make sure p is not null && p.next is not null

        2. Never ever lose the control of the head pointer of the LinkedList
           Because once we lose the head, we can not find the LinkedList again

        3. What is DummyHead? When to use is?

            - When we want to build a LinkedList from scratch (initially zero nodes), in order to
              avoid null pointer dereference, we need to use a dummy head.

            ListNode dummy = null; && ListNode dummy = new ListNode(-1);

        4. When should we maintain a tail pointer, besides the header?

           - When we want to append a new node to the end of the LinkedList.
             So we don't need to iterate the whole LinkedList each time we want to operate the tail part.

        5. When to use Iterative way vs Recursive way
            a. In real work environment, consider using iterative way first to avoid call stack overflow.
            b. In interview environment
                1. For tree related problems, usually recursion way is more preferred.
                2. For other problem, if their time complexity are the same, you can use either way.
                3. If their TC are different, like Fibonacci recursion, DP iterative, pick the smaller TC way.
     */

    public int value;
    public ListNode next;

    public ListNode(int value) {
        this.value = value;
    }

}
