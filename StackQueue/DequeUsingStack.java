package StackQueue;

import java.util.*;

public class DequeUsingStack {

    /*
        How to use multiple stacks to implement a deque?
        offerFirst(); offerLast(); pollFirst(); pollLast()

        Solution1: use two stacks to implement a deque

                ] stack1    stack2 [ 5, 6, 7, 8
        stack1: to simulate the left end of the deque
        stack2: to simulate the right end of the deque

        offerFirst(): stack1.push(); offerLast(): stack2.push()
        pollFirst():
            case1: if stack1 is not empty: stack1.pop()
            case2: if stack1 is empty: move all elements from stack2 to stack1, then call stack1.pop()
        pollLast(): similar to pollFirst()

        If client continuous call poll last and first when one is empty, every step costs O(n)
        Don't have Amortized time (hack operation)

        ================================================================================================================

        Solution2: use 3 stacks to improve the time complexity of remove() operation

        We use a buffer stack, say stack3 to buffer the elements, when moving all elements from one side to
        the other side, to make sure the number of elements in left and right part are roughly 50% of all elements.
        In detail, stack3 is used as the buffer to store the top 1/2 elements, so that the bottom 1/2 elements can
        be moved to stack1.

        input:       (Empty)  ] [ 1 2 3 4 5 6 7
        output:       1 2 3   ] [ 4 5 6 7
        Buffer-stack3 [7 6 5 4 (then move back to stack2)

        Amortized Time Complexity:
            step1: we move all elements from stack2 to buffer
                n (pop) + n (push) = 2n
            step2: we move the up 1/2 elements from buffer to stack1
                1/2n (pop) + 1/2n (push) = n
            step3: we move the remaining 1/2 elements from buffer back to stack2
                1/2n (pop) + 1/2n (push) = n

            So, in order to balance both sides, it takes O(4n) for n elements (a pop operation when empty)
            Then lets talk about the worse case: call pop() at an empty side, then continuous call pop() at one side costs O(1), until empty

            Amortized TC: (4n + n/2 * 1) / n/2 = O(11/2) = O(1)
     */

    public class TwoStackDeque {

        public Deque<Integer> stack1 = new ArrayDeque<>();
        public Deque<Integer> stack2 = new ArrayDeque<>();

        public void offerFirst(int value) {
            stack1.offerFirst(value);
        }

        public void offerLast(int value) {
            stack2.offerLast(value);
        }

        public int pollFirst() {
            if (stack1.isEmpty()) {
                while (!stack2.isEmpty()) {
                    stack1.offerFirst(stack2.pollLast());
                }
            }
            return stack1.pollFirst();
        }

        public int pollLast() {
            if (stack2.isEmpty()) {
                while (!stack1.isEmpty()) {
                    stack2.offerFirst(stack1.pollLast());
                }
            }
            return stack2.pollLast();
        }
    }

    public class ThreeStackDeque {

        public Deque<Integer> stack1 = new ArrayDeque<>();
        public Deque<Integer> stack2 = new ArrayDeque<>();
        public Deque<Integer> buffer = new ArrayDeque<>();

        public void offerFirst(int value) {
            stack1.offerFirst(value);
        }

        public void offerLast(int value) {
            stack2.offerLast(value);
        }

        public int pollFirst() {
            if (stack1.isEmpty()) {
                while (!stack2.isEmpty()) {
                    buffer.offerFirst(stack2.pollLast());
                }
                int size = buffer.size();
                while (buffer.size() > size / 2) {
                    stack1.offerFirst(buffer.pollFirst());
                }
                while (!buffer.isEmpty()) {
                    stack2.offerLast(buffer.pollFirst());
                }
            }
            return stack1.pollFirst();
        }

        public int pollLast() {
            if (stack2.isEmpty()) {
                while (!stack1.isEmpty()) {
                    buffer.offerFirst(stack1.pollFirst());
                }
                int size = buffer.size();
                while (buffer.size() > size / 2) {
                    stack2.offerLast(buffer.pollFirst());
                }
                while (!buffer.isEmpty()) {
                    stack1.offerFirst(buffer.pollFirst());
                }
            }
            return stack2.pollLast();
        }

    }

}
