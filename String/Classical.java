package String;

import java.util.*;

public class Classical {

    /*
        Question3: Char De-duplication
        Remove duplicated and adjacent letters (leave only one letter in each duplicated section) in a string.

        Eg: aabbbbazw --> abazw

        Solution:
        j = 1 (fast) the letter being processed. In other words, all letters to the left side of j (Not Including j)
                     are processed letters.
        i = 1 (slow) all letters to the left-hand side of i (not including i) are all processed letters that should be kept.
        All letters in [i, j-1] are all area that we do not care

        Initialize: j = 1, i = 1
        Case1: a[j] != a[i-1], copy a[i] = a[j], i++, j++
        Case2: a[j] = a[i-1], do not copy, just j++
     */

    public String charDeDuplication(StringBuilder input) {
        if (input == null) return null;
        else if (input.length() <= 1) return input.toString();

        int j = 1, i = 1;
        while (j < input.length()) {
            if (input.charAt(j) != input.charAt(i-1)) {
                input.setCharAt(i++,input.charAt(j));
            } //else if (input.charAt(j) == input.charAt(i-1))
            j++;
        }
        return input.delete(i, input.length()).toString();
    }

    /*
        Question4: Char de-duplication adjacent letters repeatedly

        input: aabbbbazw --> output: zw
        Data structure:
        j: the character that is being processed
        stack: all the processed letters that should be kept

        Initialize: j = 0, stack is empty
        For each step:
        Case1: a[j] != stack.top() --> stack.push, j++
        Case2: a[j] = stack.pop() --> keep moving j until a[j] != stack.top(), stack.pop()
     */

    public String charDeDuplicationRepeatedly(StringBuilder input) {
        if (input == null) return null;
        else if (input.length() <= 1) return input.toString();

        Deque<Character> stack = new ArrayDeque<>(); //only use first()
        int j = 0;

        while (j < input.length()) {
            if (stack.isEmpty() || input.charAt(j) != stack.peekFirst()) {
                stack.offerFirst(input.charAt(j++));
            } else {
                while (input.charAt(j) == stack.peekFirst() && j < input.length()) {
                    j++;
                }
                stack.pollFirst();
            }
        }
        int len = stack.size();
        char[] result = new char[len];
        for (int i = len - 1; i >= 0; i--) {
            result[i] = stack.pollFirst();
        }
        return new String(result, 0, len);
    }

    /*
        Question5: What if we do not use the stack, and we only use a slow-fast two pointers to simulate this stack?

        j: linear scan pointer
        i: all letters to the left of i (not including i) are the processed letters that should be kept
        Initialize: i = 1, j = 1

        For each step
        Case1: a[j] == a[i-1] --> repeatedly j++ until a[j] != a[i-1], i--
        Case2: a[j] != a[i-1] --> then a[i++] = a[j++]
     */

    public String charDeDuplicationRepeatedly1(StringBuilder input) {
        if (input == null) return null;
        else if (input.length() <= 1) return input.toString();

        int j = 1, i = 1;
        while (j < input.length()) {
            if (i == 0 || input.charAt(j) != input.charAt(i-1)) { //注意要判断i是否为0，如果为0会出现OOB
                input.setCharAt(i++, input.charAt(j++));
            } else { // if input.charAt(j) == input.charAt(i-1)
                while (input.charAt(j) == input.charAt(i-1)) {
                    j++;
                }
                i--;
            }
        }
        input.delete(i, input.length());
        return input.toString();
    }

    public static void main(String[] args) {
        Classical c = new Classical();
        //Question3
        StringBuilder input1 = new StringBuilder("aabbbbazw");
        String result3 = c.charDeDuplication(input1);
        System.out.println(result3);

        //Question4
        StringBuilder input4 = new StringBuilder("abbbbazw");
        String result4 = c.charDeDuplicationRepeatedly(input4);
        System.out.println(result4);

        //Question5
        StringBuilder input5 = new StringBuilder("abbbbazw");
        String result5 = c.charDeDuplicationRepeatedly1(input5);
        System.out.println(result5);
    }
}
