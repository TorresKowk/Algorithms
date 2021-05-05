package Recursion;

/*
    Recursion 需要掌握的知识点
    1. 表象上：function calls itself
    2. 实质上：Boil down a big problem to smaller ones(size n depends on size n-1 or n-2 or n/2...)
    3. Implementation上：
        a. Base case: smallest problem to solve
        b. Recursive rule: How to make the problem smaller?
        (if we can resolve the same problem but with a smaller size, then what is left to do for the current problem size n?)

     How to analyze time and space complexity of a recursive function?
        TC: the sum of time complexity of ALL NODES in the recursion tree.
        SC: the sum of space complexity of all nodes on a pink path (a path from top to bottom)
 */

public class easy {

    /*
        Question1: Fibonacci Sequence
        0, 1, 1, 2, 3, 5, 8, 13...
        Base case: f(0) = 1, f(1) = 1
        Recursive rule: f(n) = f(n-1) + f(n-2)

        Recursion Tree:
                 f(4)             O(1)
                /    \
              f(3)    f(2)        O(2)
             /  \     /  \
           f(2) f(1)              O(4)
          /   \
        f(1)  f(0)                O(8)   2^n - 1           TC: O(2^n)  SC: O(n)
     */

    public int fibo(int n) {
        //base case
        if (n == 0) return 0;
        else if (n == 1) return 1;

        //recursive rule
        return fibo(n - 1) + fibo(n - 2);
    }

    /*
        Question2: how to calculate a^b

        Base case: f(a, 0) = 1, f(a, 1) = a
        Recursive rule:
            Solution 1 - f(a, b) = f(a, b-1) * a -- TC / SC = O(n)
            Solution 2 - f(a, b) = f(a, b/2) * f(a, b/2)  --- when b is even
                                 = f(a, b/2) * f(a, b/2) * a  --- when b is odd  -- TC / SC = O(logn)
     */

    public int power(int a, int b) {
        //base case
        if (b == 0) return 1;
        else if (b == 1) return a;

        //recursive rule
        int half = power(a, b/2);
        if (b % 2 == 0) return half * half;
        else return half * half * a;
    }

    public static void main(String[] args) {
        easy e = new easy();

        //Question1
        System.out.println(e.fibo(6));

        //Question2
        System.out.println(e.power(3, 4));
    }

}
