package StackQueue;

public class Stack {

    /*
        1. Stack的移动操作有什么常见特性？
            a. 将Stack1所有元素全部move到Stack2，元素在Stack2的顺序完全reverse
            b. 将Stack1所有元素move到Stack2，然后元素全部（或者部分）move回Stack1，则回到原来Stack1的元素顺序不变，
               amortized TC 分摊到每一个move操作时间往往可以变为 O(1)

        2. 什么问题要往Stack上考虑？
           Answer：从左往右 linear scan 一个 array / string 时，如果要不断回头看左边最新的元素时，往往要用到 Stack
     */

}
