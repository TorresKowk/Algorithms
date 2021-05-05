package StackQueue;

import java.util.*;

public class TwoStackQueue {

    /*
        Question1: How to implement a queue by using two stacks?

        queue   <-- 5 6 7 8 <--
        stack1  [           <--
        stack2  [ 8 7 6 5    -->

        stack1: is the only stack to store new elements when adding a new element into the queue
        stack2: is the only stack to pop old element out of the queue
            case1: if stack2 is not empty, then just pop                                    -- O(1)
            case2: if stack2 is empty, push all elements in stack1 to stack2, then call pop -- O(n)

        Time Complexity:

        offer(): O(1)
        poll(): worst case O(n)
            Amortized time complexity:
            n = 1000; stack1 [1000, 999, ..., 1 ; stack2 [
            1st call poll(): n * stack1.pop() + n * stack2.push() + 1 = 2n + 1
            2nd call poll(): 1
            3rd call poll(): 1
            ...
            1000th call poll(): 1

            Amortized time of poll() = [(2n + 1) + 1 * (n - 1)] / n = 3n / n = O(3) = O(1)
     */

    //use offerFirst() & pollFirst() & peekFirst to implement a stack
    private Deque<Integer> stack1 = new ArrayDeque<>();
    private Deque<Integer> stack2 = new ArrayDeque<>();

    public void offer(int val) {
        stack1.offerLast(val);
    }

    public int poll() {
        if (stack2.peekFirst() == null) {
            while (!stack1.isEmpty()) {
                stack2.offerFirst(stack1.pollFirst());
            }
        }
        return stack2.pollFirst();
    }

    public int peek() {
        if (stack2.peekFirst() == null) {
            while (!stack1.isEmpty()) {
                stack2.offerFirst(stack1.pollFirst());
            }
        }
        return stack2.peekFirst();
    }

}
