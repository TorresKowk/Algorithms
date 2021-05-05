package DFS;

import java.util.*;

public class Classical {

    /*
        Question1: Print all subsets of a set s = {"a","b","c"}

        Result: "abc"; "ab"; "ac"; "bc"; "a"; "b"; "c"; " "; totally 2^n = 8
        Discussion:
            1. How many levels in the recursion tree? What does it store on each level?
               3 elements --> 3 levels; For each level we are considering one letter (Ture / False)
            2. How many different states should we try in each level?
               Two branches; eg: 1. add A letter 2. not add A letter (for the first level)

                                                { }                             Initialization: {}
                                             /       \
                                          {a}          { }                      First A level
                                         /   \        /   \
                                    {a,b}    {a}    {b}   { }                   First B level
                                   /   \     / \    / \   / \
                             {a,b,c} {a,b}{a,c}{a}{b,c}{b}{c}{ }                First C level

         TC: O(2^n) (totally 2^n nodes); SC: O(n) (longest path from up to down)
     */

    public void findSubset(char[] input) {
        if (input.length == 0) return;
        StringBuilder solutionPrefix = new StringBuilder(input.length);
        findSubset(input, 0, solutionPrefix);
    }

    private void findSubset(char[] input, int index, StringBuilder solutionPrefix) {
        //base case
        if(index == input.length) {
            System.out.println(solutionPrefix);
            return;
        }
        //index represents the current layer
        //for the current layer, we have two branches: add / not add
        solutionPrefix.append(input[index]); //吃
        findSubset(input, index+1, solutionPrefix);
        //Keep in mind: when we use a StringBuilder, we must pair up add vs deletion
        solutionPrefix.deleteCharAt(solutionPrefix.length() - 1); //吐
        findSubset(input, index+1, solutionPrefix);
    }

    /*
        Follow up 1: input = "abcde", you can insert an empty space in between each adjacent letter or not.
                    Can you print out all possible output?
     */

//    public void findSubsetPlus(char[] input) {
//        if (input.length == 0) return;
//        StringBuilder solutionPrefix = new StringBuilder(input.length);
//        findSubsetPlus(input, 0, solutionPrefix);
//    }
//
//    private void findSubsetPlus(char[] input, int index, StringBuilder solutionPrefix) {
//        //base case
//        if (index == input.length - 1) {
//            solutionPrefix.append(input[index]);
//            System.out.println(solutionPrefix);
//            solutionPrefix.deleteCharAt(solutionPrefix.length() - 1);
//            return;
//        }
//
//        solutionPrefix.append(input[index]);
//        solutionPrefix.append('_');
//        findSubsetPlus(input, index+1, solutionPrefix);
//        solutionPrefix.deleteCharAt(solutionPrefix.length() - 1);
//        solutionPrefix.deleteCharAt(solutionPrefix.length() - 1);
//        solutionPrefix.append(input[index]);
//        findSubsetPlus(input, index+1, solutionPrefix);
//    }

    /*
        Question2: fina all VALID permutation using the parenthesis provided.
                   eg: ()()(); ()(()); ((()));... (valid)
                       )()()( (invalid)
        What's mean of valid: for any ), there must exist a left parenthesis to match it!!!
                                                  empty
                                                 /     \
                                               (         )  (invalid)           first level: add "(" or add ")"
                                              /  \      /  \
                                            ((   ()   )(    ))                  second level: add "(" or add ")"
                                           /  \  /  \    ...
        Tips: whenever we add a ")", we have to make sure the number of "(" is larger than the number of ")"
        TC: O(2^2n) (2 branches, 2n levels); SC: O(2n) (2n nodes in the longest path)
     */

    public void findPermutation(int k) { // k means we have k parenthesis pairs (k times "(" and k times ")")
        if (k == 0) return;
        StringBuilder solutionPrefix = new StringBuilder();
        findPermutation(k, 0, 0, solutionPrefix);
    }

    private void findPermutation(int k, int left, int right, StringBuilder solutionPrefix) {
        // left stores the number of left parenthesis "(" added so far.
        // right stores the number of left parenthesis ")" added so far.
        //base case
        if (left == k && right == k) {
            System.out.println(solutionPrefix);
            return;
        }

        if (left < k) {
            solutionPrefix.append('(');
            findPermutation(k, left+1, right, solutionPrefix);
            solutionPrefix.deleteCharAt(solutionPrefix.length() - 1);
        }

        if (right < left && right < k) {
            solutionPrefix.append(')');
            findPermutation(k, left, right+1, solutionPrefix);
            solutionPrefix.deleteCharAt(solutionPrefix.length() - 1);
        }
    }

    /*
        Question3: Print all combinations of coins that can sum up to a total value n
                   eg: total value n = 99 cents
                       coins value types: 25, 10, 5, 1 cent
                       one possible way: 3 * 25 cents + 2 * 10 cents + 0 * 5 cents + 4 * 1 cents
        Recursion Tree: 4 levels, each level we consider how many possible cases for one type of coin can be chosen

                                                     99 cents
                                     /            /        \            \
                           3 * 25 cents   2 * 25 cents  1 * 25 cents    0 * 25 cents              1st level for 25 cents
                       /        |       \
          2 * 10 cents   1 * 10 cents  0 * 10 cents         ... ...                               2nd level for 10 cents

                   ...          ...             ...         ...             ...                   ...

        Data Structure: we use an array[] input to store the type of coins
                        for each level DFS function, we use array[] solution to store the current output, and print it at last
                        and we use index to represent the current input[index] type of coin we are precessing.
                        what's more, we use a variable moneyLeft to store the cents left in the current solution

        TC: O(99^4) (up to 99 branches for one level, 4 level); SC: O(4)
     */

    public void findCoinCombination(int value, int[] input) {
        if (value == 0 || input.length == 0) return;
        int[] solution = new int[input.length];
        findCoinCombination(value, input, solution, 0);
    }

    private void findCoinCombination(int moneyLeft, int[] input, int[] solution, int index) {
        //base case
        if (moneyLeft == 0 && index == input.length) {
            System.out.println(Arrays.toString(solution));
            return;
        } else if (moneyLeft != 0 && index == input.length) return;

        int size = moneyLeft / input[index];
        for (int i = size; i >= 0; i--) {
            solution[index] = i;
            // moneyLeft - i * input[index]: money left in the next level
            findCoinCombination(moneyLeft - i * input[index], input, solution, index+1);
        }
    }

    /*
        Question4: Given a string with no duplicate letters, how to print it out all permutations of the string
                   eg: input: "abc"
                       result: "abc"; "acb"; "bac"; "bca"; "cab"; "cba"

        Three levels of the recursion tree. For each level represents each position index of the output.

                 swap(i, j)                root = {abc}
                                         /      |       \
                                     a(bc)     b(ac)    c(ba)                     L1: 1st position (index = 0)
                                   /    \     /    \      /    \
                                ab(c) ac(b) ba(c) bc(a) ca(b)  cb(a)              L2: 2nd position (index = 1)
                                 |       |   |      |    |      |
                               abc     acb  bac    bca  cab     cba               L3: 3rd position (index = 2)

     */

    public void permutation(char[] input) {
        if (input.length == 0) return;
        permutation(input, 0);
    }

    private void permutation(char[] input, int index) {
        //base case
        if (index == input.length) {
            System.out.println(Arrays.toString(input));
        }

        for (int i = index; i < input.length; i++) {
            swap(input, index, i); // 吃
            permutation(input, index+1);
            swap(input, index, i); // 吐
        }
    }

    private void swap(char[] input, int i, int j) {
        char temp = input[i];
        input[i] = input[j];
        input[j] = temp;
    }
    
    /*
        Question5: input = "abcde", you can insert an empty space in the between each adjacent letter or not.
                   Can you print out all possible output?
     */
    
    public void insertEmptySpace(String input) {
        if (input == null) return;
        boolean[] solution = new boolean[input.length() - 1];
        insertEmptySpace(input, 0, solution);
    }
    
    private void insertEmptySpace(String input, int index, boolean[] solution) {
        //base case
        if (index == solution.length) {
            StringBuilder result = new StringBuilder();
            for (int i = 0; i < input.length() - 1; i++) {
                result.append(input.charAt(i));
                if (solution[i] == true) {
                    result.append("_");
                }
            }
            result.append(input.charAt(input.length() - 1));
            System.out.println(result);
            return;
        }
        solution[index] = true;
        insertEmptySpace(input, index+1, solution);
        solution[index] = false;
        insertEmptySpace(input, index+1, solution);
    }

    public static void main(String[] args) {
        Classical c = new Classical();

        //Question1
//        char[] input1 = {'a', 'b', 'c'};
//        //c.findSubset(input1);
//
//        //Question1.5
////        char[] input11 = {'a', 'b', 'c'};
////        c.findSubsetPlus(input11);
//
//        //Question2
//        c.findPermutation(3);
//
//        //Question3
//        int[] input3 = {25, 10, 5, 1};
//        c.findCoinCombination(99, input3);
//
//        //Question4
//        char[] input4 = {'a', 'b', 'c'};
//        c.permutation(input4);

        //Question5
        String input5 = "abcde";
        c.insertEmptySpace(input5);
    }

}
