package com.company;

/*
    A complete answer will include the following (讲code要点)：
        1. Document your assumptions
        2. Explain your approach and how you intend to solve the problem
        3. Provide code comments where applicable
        4. Explain the big-O run time complexity of your solution. Justify your answer.
        5. Identify any additional data structures you used and justify why you used them.
        6. Only provide your best answer to each part of the question.

 */

public class Main {
    //Shallow Copy vs Deep Copy
    public int[] value = {-5, 12, 0};
    public int[] data;

    public void shallowCopy (int[] value) {
        data = value; //this is ShallowCopy, use are reference to the same address
    }

    public void deepCopy (int[] value) {
        data = new int[value.length];
        for (int i = 0; i < value.length; i++) {
            data[i] = value[i]; //this is DeepCopy, creates(new) a object and copies over the origin object
        }
    }

}
