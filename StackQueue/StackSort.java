package StackQueue;

import java.util.*;

public class StackSort {

    /*
        Question1: How to sort numbers with three (or two) stacks?

        Three Stacks:

        stack1 input  [1 2 3 4 -- store the input
        stack2 buffer [        -- a space to store input.pop(), to store the globalMin
        stack3 output [        -- store the output
        global min

        step1: pop() every elements in stack1 to stack2 buffer, use globalMin to mark the min element
        step2: pop() every elements in buffer to stack1, pop() every globalMin to output
        step3: repeat step123 until stack1 is empty

        ================================================================================================

        Two Stacks:

        stack1 input           [1 2 3 4 -- store the input                      count =
        stack2 buffer & output [                                                global min =

        stack1: used for storing all unsorted elements
        stack2: left part | right part
                left part: to store all sorted elements in previous iterations
                right part: used as a buffer to find the current smallest element

        step1: pop() every elements in stack1 to stack2 buffer, use globalMin and count to mark the min element
        step2:

     */

    public Deque<Integer> sortWithThreeStack(Deque<Integer> input) {
        if (input == null) return null;

        Deque<Integer> buffer = new ArrayDeque<>(); //only use offerFirst() & pollFirst() to implement a stack
        Deque<Integer> output = new ArrayDeque<>();
        while (!input.isEmpty()) {
            int globalMin = input.peekFirst();
            while (!input.isEmpty()) {
                if (input.peekFirst() < globalMin) {
                    globalMin = input.peekFirst();
                }
                buffer.offerFirst(input.pollFirst());
            }
            while (!buffer.isEmpty()) {
                if (buffer.peekFirst() != globalMin) {
                    input.offerFirst(buffer.pollFirst());
                } else {
                    output.offerFirst(buffer.pollFirst());
                }
            }
        }
        return output;
    }

    public Deque<Integer> sortWithTwoStack(Deque<Integer> input) {
        if (input == null) return null;

        Deque<Integer> stack2 = new ArrayDeque<>();
        while (!input.isEmpty()) {
            int globalMin = input.peekFirst();
            int count = 0;
            while (!input.isEmpty()) {
                if (input.peekFirst() < globalMin) {
                    globalMin = input.peekFirst();
                    count = 1;
                } else if (input.peekFirst() == globalMin) {
                    count++;
                }
                stack2.offerFirst(input.pollFirst());
            }
            while (!stack2.isEmpty() && stack2.peekFirst() >= globalMin) {
                /*
                    1. poll all elements that in right part (include globalMin), remain the left part in stack2
                    2. offer those right part elements larger than globalMin back to input
                    3. for all count times globalMin elements, offer back to stack2 (to be the left part)
                 */
                if (stack2.peekFirst() != globalMin) {
                    input.offerFirst(stack2.pollFirst());
                } else {
                    stack2.pollFirst();
                }
            }
            for (int i = 0; i < count; i++) {
                stack2.offerFirst(globalMin);
            }
        }
        return stack2;
    }

    public static void main(String[] args) {
        StackSort c = new StackSort();
        //Question1
        Deque<Integer> input1 = new ArrayDeque<>();
        input1.offerFirst(3);
        input1.offerFirst(4);
        input1.offerFirst(2);
        input1.offerFirst(2);
        Deque<Integer> output1 = c.sortWithThreeStack(input1);
        while (!output1.isEmpty()) {
            System.out.print(output1.pollFirst() + " --> ");
        }
        System.out.println();

        //Question2
        Deque<Integer> input2 = new ArrayDeque<>();
        input2.offerFirst(3);
        input2.offerFirst(4);
        input2.offerFirst(2);
        input2.offerFirst(2);
        Deque<Integer> output2 = c.sortWithTwoStack(input2);
        while (!output2.isEmpty()) {
            System.out.print(output2.pollFirst() + " --> ");
        }
        System.out.println();
    }

}
